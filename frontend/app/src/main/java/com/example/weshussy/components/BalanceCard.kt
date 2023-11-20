package com.example.weshussy.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weshussy.R

@Composable
// PLACEHOLDER for balance
fun BalanceCard(
    balance: String,
    total: String,
    onNotificationClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f), // takes up the remaining space
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = balance, // PLACEHOLDER
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "Your balance",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bell),
                        contentDescription = "Notification",
                        modifier = Modifier.size(35.dp) // Adjust the size of the icon itself
                    )
                }
            }
            Divider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = total, // PLACEHOLDER
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}
