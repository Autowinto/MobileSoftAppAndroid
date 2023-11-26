package com.example.weshussy.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weshussy.R
import com.example.weshussy.api.data.Group
import com.example.weshussy.components.BalanceCard
import com.example.weshussy.ui.UserSession
import com.example.weshussy.ui.theme.WeShussyTheme
import com.example.weshussy.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    val viewModel =  HomeViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                HomeView(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun HomeView(viewModel: HomeViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val groups  = remember { mutableStateListOf<Group>() }
    val coroutineScope = rememberCoroutineScope()
    val currentUser = UserSession.getUser()?: return


    coroutineScope.launch {
        val groupsData = viewModel.getGroupsForUser(currentUser.id)
        groups.addAll(groupsData)
        println("GROUPS HERE")
        println(groupsData)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.padding(top = 16.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .clickable {
                            context.startActivity(Intent(context, ProfileActivity::class.java))
                        }
                        .align(Alignment.CenterHorizontally)
                )

                Column(modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp)
                    .verticalScroll(rememberScrollState())) {
                    if (groups.size > 0) {

                    groups.forEach { group ->
                        BalanceCard(
                            groupName = group.name,
                            balance = "$200",
                            total = "$" + group.totalExpenses.toString(),
                            onCardClick = {
                                context.startActivity(Intent(context, GroupInfoActivity::class.java))
                            },
                            onNotificationClick = {
                                showDialog = true
                            }
                        )
                    }
                    }
                }
                Button(
                    onClick = { context.startActivity(Intent(context, GroupCreateActivity::class.java)) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Create new group")
                    Text("Create new group")
                }



            }
            if (showDialog) {
                NotificationDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmSend = {
                        showDialog = false
                        showSnackbar = true
                    }
                )
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }

    if (showSnackbar) {
        LaunchedEffect(key1 = showSnackbar) {
            snackbarHostState.showSnackbar(
                message = "Notification sent!",
                actionLabel = "OK",
                duration = SnackbarDuration.Long
            )
            showSnackbar = false
        }
    }
}

@Composable
fun NotificationDialog(onDismissRequest: () -> Unit, onConfirmSend: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = { Text("Send out notification of missing payments?") },
        confirmButton = {
            TextButton(onClick = onConfirmSend) {
                Text("Send")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun GroupsList(groups: List<Group>) {

}