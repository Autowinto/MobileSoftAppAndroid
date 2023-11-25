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
import com.example.weshussy.ui.activities.HomeActivity
import com.example.weshussy.ui.theme.WeShussyTheme
import com.example.weshussy.ui.viewmodels.GroupCreateViewModel
import kotlinx.coroutines.launch

class GroupCreateActivity : ComponentActivity() {
    private val viewModel = GroupCreateViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                GroupCreateScreen(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupCreateScreen(viewModel: GroupCreateViewModel) {
    val groupName = remember { mutableStateOf(TextFieldValue()) }
    val groupDescription = remember { mutableStateOf(TextFieldValue()) }
    val memberNameToAdd = remember { mutableStateOf(TextFieldValue()) }
    val groupMembers = remember { mutableStateListOf("member 1", "member 2") } // Example members
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current // Obtain the context

        TopNavBar(
            title = "Create group",
            onBackClick = {
                // Intent to go back to HomeActivity with the task stack cleared
                val intent = Intent(context, HomeActivity::class.java).apply {
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
                MemberCreateItem(
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
            onClick = { coroutineScope.launch {
                println("Coroutine triggered")
                viewModel.createGroup(name = groupName.toString(), userId = "1")
            } },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text("Create")
        }
    }
}

@Composable
fun MemberCreateItem(name: String, onRemoveClick: () -> Unit) {
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