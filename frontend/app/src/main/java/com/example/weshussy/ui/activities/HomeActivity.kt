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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weshussy.R
import com.example.weshussy.activities.GroupCreateActivity
import com.example.weshussy.components.BalanceCard
import com.example.weshussy.ui.theme.WeShussyTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                HomeView()
            }
        }
    }
}

@Composable
fun HomeView() {
    var showDialog by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
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
                Spacer(modifier = Modifier.height(32.dp))

                BalanceCard(
                    groupName = "Group 1",
                    balance = "$150",
                    total = "$1000",

                    onCardClick = {
                        context.startActivity(Intent(context, GroupInfoActivity::class.java))
                    },
                    onNotificationClick = {
                        showDialog = true
                    }

                )

                if (showDialog) {
                    NotificationDialog(
                        onDismissRequest = { showDialog = false },
                        onConfirmSend = {
                            showDialog = false
                            showSnackbar = true
                        }
                    )
                }

                BalanceCard(
                    groupName = "Group 2",
                    balance = "$250",
                    total = "$1000",
                    onCardClick = {
                        context.startActivity(Intent(context, GroupInfoActivity::class.java))
                    },
                    onNotificationClick = {
                        showDialog = true
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

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
                duration = SnackbarDuration.Short
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