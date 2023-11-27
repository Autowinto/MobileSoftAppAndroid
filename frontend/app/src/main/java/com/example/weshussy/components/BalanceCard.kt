package com.example.weshussy.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weshussy.R

@Composable
fun BalanceCard(
    groupName: String,
    groupDescription: String,
    balance: String,
    total: String,
    onCardClick: () -> Unit, // This lambda will be called when the card is clicked
    onNotificationClick: () -> Unit,
    showNotificationIcon: Boolean = true // New parameter to control the visibility of the notification icon
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onCardClick), // Make the card clickable
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
                        text = groupName,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = groupDescription,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(text = "")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
//                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Your balance",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(text = balance)
                    }

                }
                if (showNotificationIcon) {
                    IconButton(
                        onClick = onNotificationClick,
                        modifier = Modifier
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bell),
                            contentDescription = "Notification",
                            modifier = Modifier.size(35.dp)
                        )
                    }
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
                    text = total,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}
