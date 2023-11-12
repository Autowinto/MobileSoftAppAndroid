package com.example.weshussy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.weshussy.ui.theme.WeShussyTheme

class HistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HistoryView()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryView() {
    val context = LocalContext.current
    BaseActivity(slotContent = {

        ExpenseListing()
        ExpenseListing()
        ExpenseListing()
        ExpenseListing()
        ExpenseListing()
        ExpenseListing()
        ExpenseListing()
        ExpenseListing()
        ExpenseListing()

    })

}

@Preview(showBackground = true)
@Composable
fun ExpenseListing() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(text = "Participant ")
        Text(text = "Amount")
    }
}