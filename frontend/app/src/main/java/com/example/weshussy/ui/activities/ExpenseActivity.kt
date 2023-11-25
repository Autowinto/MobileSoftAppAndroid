package com.example.weshussy.ui.activities

import TopNavBar
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.weshussy.activities.ExpenseAddActivity
import com.example.weshussy.activities.ExpenseDetailsActivity
import com.example.weshussy.activities.GroupSettingsActivity
import com.example.weshussy.components.BalanceCard
import com.example.weshussy.ui.theme.WeShussyTheme

class ExpenseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                GroupInfoScreen()
            }
        }
    }
}


@Composable
fun GroupInfoScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current // Obtain the context

        TopNavBar(
            title = "Group info",
            onBackClick = {
                // Intent to go back to HomeActivity with the task stack cleared
                val intent = Intent(context, HomeActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                context.startActivity(intent) // Start the HomeActivity
            },
            showEdit = true,
            onEditClick = {
                // Intent to navigate to GroupSettingsActivity
                context.startActivity(Intent(context, GroupSettingsActivity::class.java))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TODO: Replace placeholder balance and total with actual data
        BalanceCard(
            groupName = "My Group",
            balance = "$250",
            total = "$1000",
            onCardClick = { /* ... */ },
            onNotificationClick = { /* ... */ },
            showNotificationIcon = false // Hide the notification icon
        )


        Spacer(modifier = Modifier.height(16.dp))

        // State for managing selected tab
        var selectedTabIndex by remember { mutableStateOf(0) }
        // Tabs for navigating between Overview, Expenses, and Pay
        TabRow(selectedTabIndex = selectedTabIndex) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                text = { Text("Overview") }
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                text = { Text("Expenses") }
            )
            Tab(
                selected = selectedTabIndex == 2,
                onClick = { selectedTabIndex = 2 },
                text = { Text("Pay") }
            )
        }

        // Display content based on selected tab
        when (selectedTabIndex) {
            0 -> OverviewContent()
            1 -> ExpensesContent()
            2 -> PayContent()
        }
    }
}

@Composable
fun OverviewContent() {
    val context = LocalContext.current //
    Box(modifier = Modifier.fillMaxSize()) {
        // The LazyColumn for displaying a list of members or items
        LazyColumn {
            items(10) { index -> // Replace this with a dynamic list of data
                MemberInfo(
                    name = "Member Name $index", // TODO: Replace with actual member name
                    balance = "Balance: $$$" // TODO: Replace with actual balance
                )
            }
        }

        // The FAB is positioned inside the Box, at the bottom end
        FloatingActionButton(
            onClick = { context.startActivity(Intent(context, ExpenseAddActivity::class.java)) },
            // Align the FAB at the bottom end of the Box
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    }
}



@Composable
fun ExpensesContent() {
    // Display a list of expenses
    // TODO: Replace with actual data
    val context = LocalContext.current // Obtain the context
    LazyColumn {
        items(10) { index ->
            ExpenseItem(

                name = "Expense Name/Description $index", // TODO: Replace with actual expense name/description
                onDetailsClick = {  context.startActivity(Intent(context, ExpenseDetailsActivity::class.java)) }
            )
        }
    }
}

@Composable
fun PayContent() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // TODO: Replace placeholder with the actual amount due
        Text("Amount Due: $$$")

        Spacer(modifier = Modifier.height(8.dp))

        // TODO: Implement payment functionality
        Button(onClick = { /* TODO: Implement pay action */ }) {
            Text("Swipe to pay")
        }
    }
}

@Composable
fun MemberInfo(name: String, balance: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = balance,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ExpenseItem(name: String, onDetailsClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(name)
        Button(onClick = onDetailsClick) {
            Text("Details")
        }
    }
}


