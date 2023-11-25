package com.example.weshussy.ui.activities

import TopNavBar
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.weshussy.ui.activities.GroupInfoActivity
import com.example.weshussy.ui.theme.WeShussyTheme


class ExpenseAddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                ExpenseAddScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseAddScreen() {
    val context = LocalContext.current
    var expenseAmount by remember { mutableStateOf("") }
    var expenseDescription by remember { mutableStateOf("") }
    var selectedPayer by remember { mutableStateOf("") }
    val groupMembers = listOf("Group Member 1", "Group Member 2", "Group Member 3", "Group Member 4")
    val selectedMembers = remember { mutableStateMapOf<String, Boolean>() }
    groupMembers.forEach { member ->
        selectedMembers.putIfAbsent(member, true)
    }

    Scaffold(
        topBar = {
            TopNavBar(
                title = "Add expense",
                onBackClick = {
                    val intent = Intent(context, GroupInfoActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    context.startActivity(intent)
                },
                showEdit = false
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)) {
            OutlinedTextField(
                value = expenseAmount,
                onValueChange = { expenseAmount = it },
                label = { Text("Expense") },
                leadingIcon = { Text("$") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = expenseDescription,
                onValueChange = { expenseDescription = it },
                label = { Text("Expense description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            DropdownMenuPayer(
                payerList = groupMembers,
                selectedPayer = selectedPayer,
                onPayerSelected = { selectedPayer = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
            GroupMembersSelection(groupMembers, selectedMembers)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val intent = Intent(context, GroupInfoActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Create")
            }
        }
    }
}

@Composable
fun DropdownMenuPayer(
    payerList: List<String>,
    selectedPayer: String,
    onPayerSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = if (selectedPayer.isNotEmpty()) selectedPayer else "Select Payer",
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .padding(16.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            payerList.forEach { payer ->
                    DropdownMenuItem(
                        onClick = { onPayerSelected(payer)
                            expanded = false },
                        text = { Text("Item text here") }
                    )
            }
        }
    }
}


@Composable
fun GroupMembersSelection(
    members: List<String>,
    selectedMembers: MutableMap<String, Boolean>
) {
    Column {
        members.forEach { member ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedMembers[member] = !(selectedMembers[member] ?: false) }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(member, modifier = Modifier.weight(1f))
                Checkbox(
                    checked = selectedMembers[member] ?: false,
                    onCheckedChange = { selectedMembers[member] = it }
                )
            }
        }
    }
}
