package com.example.weshussy.ui.activities

import TopNavBar
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.ui.activities.HomeActivity
import com.example.weshussy.ui.theme.WeShussyTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.weshussy.api.interfaces.GroupApi
import com.example.weshussy.ui.UserSession
import com.example.weshussy.ui.viewmodels.GroupInfoViewModel
import kotlinx.coroutines.coroutineScope


class GroupSettingsActivity : ComponentActivity() {
    private val viewModel = GroupInfoViewModel();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val groupId = getIntent().getStringExtra("groupId")?: return
        viewModel.setGroupId(groupId)

        setContent {
            WeShussyTheme {
                GroupSettingsScreen(viewModel);
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupSettingsScreen(viewModel: GroupInfoViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val groupId = remember { mutableStateOf("") }
    val groupName = remember { mutableStateOf("") }
    val groupDescription = remember { mutableStateOf("") }
    val memberNameToAdd = remember { mutableStateOf("") }
    val groupOwnerId = remember { mutableStateOf("") }
    val groupMembers = remember { mutableStateListOf("member 1", "member 2", "member 3", "member 3") } // Example members
    val currentUser = UserSession.getUser()?: return


    coroutineScope.launch {
        val response = RetrofitClient().groupApi.getGroupById(viewModel.getGroupId())
//        val response = viewModel.;
//        response.add
        if (response.isSuccessful) {
            val body = response.body()
            if (body == null) return@launch
            groupId.value = body.id;
            groupName.value = body.name;
            groupDescription.value = body.description.toString();
            groupOwnerId.value = body.ownerId;
        }

        println("WHAT IS THIS " + groupName)
//        println(groupExpenses)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current // Obtain the context

        TopNavBar(
            title = "Group Settings",
            onBackClick = {
                // Intent to go back to HomeActivity with the task stack cleared
                val intent = Intent(context, ExpenseActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                context.startActivity(intent) // Start the HomeActivity
            },
            showEdit = false,

        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = groupName.value,
                    onValueChange = { groupName.value = it },
                    label = { Text("Group Name") }
                )
                OutlinedTextField(
                    value = groupDescription.value,
                    onValueChange = { groupDescription.value = it },
                    label = { Text("Description") }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = memberNameToAdd.value,
            onValueChange = { memberNameToAdd.value = it },
            label = { Text("Invite member") },
            trailingIcon = {
                IconButton(onClick = { /* TODO: Add member logic */ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add member")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(groupMembers) { member ->
                MemberSettingsItem(
                    name = member,
                    onRemoveClick = {
                        // TODO: Implement member removal logic
                        groupMembers.remove(member)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                val response = RetrofitClient().groupApi.updateGroup(
                    GroupApi.GroupUpdateRequestBody(
                        id = groupId.value,
                        name = groupName.value,
                        userId = currentUser.id,
                        description = groupDescription.value
                    )
                )
                if (response.isSuccessful) {
                    Intent(
                        context,
                        ExpenseActivity::class.java
                    ).also { context.startActivity(it) }
                } }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text("Save")
        }
    }
}

@Composable
fun MemberSettingsItem(name: String, onRemoveClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name, modifier = Modifier.weight(1f))
        IconButton(onClick = onRemoveClick) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove member")
        }
    }
}