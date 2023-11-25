package com.example.weshussy.activities

import TopNavBar
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.weshussy.ui.theme.WeShussyTheme


class ExpenseEditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                ExpenseEditScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseEditScreen() {
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
                title = "Edit expense",
                onBackClick = {
                    val intent = Intent(context, ExpenseDetailsActivity::class.java).apply {
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
                    val intent = Intent(context, ExpenseDetailsActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Save")
            }
        }
    }
}
