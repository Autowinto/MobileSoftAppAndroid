package com.example.weshussy

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

class ExpenseDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                ExpenseDetailsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDetailsScreen() {
    val context = LocalContext.current // Obtain the context
    val total = "$$$" // Replace with actual total
    val expenseDescription = "Expense description" // Replace with actual description
    val payer = "Payer name" // Replace with actual payer name
    val membersSharing = listOf("Group Member 1", "Group Member 2", "Group Member 4") // Replace with actual list

    Scaffold(
        topBar = {
            TopNavBar(
                title = "Expense",
                onBackClick = {
                    val intent = Intent(context, GroupInfoActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                    context.startActivity(intent)},
                showEdit = true,
                onEditClick = { context.startActivity(Intent(context, ExpenseEditActivity::class.java)) }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Spacer(modifier = Modifier.height(16.dp))

            // Display the total amount for the expense
            OutlinedTextField(
                value = total,
                onValueChange = {},
                label = { Text("Total") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            // Display the expense description
            OutlinedTextField(
                value = expenseDescription,
                onValueChange = {},
                label = { Text("Expense Description") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            // Display who paid for the expense
            OutlinedTextField(
                value = payer,
                onValueChange = {},
                label = { Text("Payer") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // List who is sharing the expense
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                membersSharing.forEach { member ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(member, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    }

