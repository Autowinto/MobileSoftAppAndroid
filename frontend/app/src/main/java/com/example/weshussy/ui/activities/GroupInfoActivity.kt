package com.example.weshussy.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.weshussy.ui.theme.WeShussyTheme
import com.example.weshussy.ui.viewmodels.GroupInfoViewModel
import kotlinx.coroutines.launch

class GroupInfoActivity : ComponentActivity() {
    private val viewModel = GroupInfoViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GroupScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun GroupScreen(viewModel: GroupInfoViewModel) {
    Text(text = "Test")
    val coroutineScope = rememberCoroutineScope()
    Button(onClick = { coroutineScope.launch { viewModel.createGroup(groupName = "Test Group", userId = "321321") } }) {
        Text(text = "CREATE GROUP TEST")

    }
}