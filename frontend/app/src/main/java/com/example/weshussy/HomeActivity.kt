package com.example.weshussy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .clickable {
                        context.startActivity(Intent(context, ProfileActivity::class.java))
                    }
            )
            Spacer(modifier = Modifier.height(32.dp))
            // PLACEHOLDER for balance and total
            BalanceCard(
                balance = "10.00 DKK",
                total = "150.00 DKK",
                onNotificationClick = { /* TODO: Handle notification click */ }
            )
            Spacer(modifier = Modifier.height(16.dp))
            GroupInfoCard()
            Spacer(modifier = Modifier.height(16.dp))
            GroupInfoCard()
        }
    }
}

@Composable
fun GroupInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Group Name", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Description", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeShussyTheme {
        HomeView()
    }
}
