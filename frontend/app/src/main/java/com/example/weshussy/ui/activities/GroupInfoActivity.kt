package com.example.weshussy.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.R
import com.example.weshussy.api.data.Group
import com.example.weshussy.api.interfaces.GroupApi
import com.example.weshussy.api.interfaces.UserApi
import com.example.weshussy.components.BalanceCard
import com.example.weshussy.ui.UserSession
import com.example.weshussy.ui.theme.WeShussyTheme
import com.example.weshussy.ui.viewmodels.GroupInfoViewModel
import kotlinx.coroutines.launch

class GroupInfoActivity : ComponentActivity() {
    private val viewModel = GroupInfoViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val groupId = getIntent().getStringExtra("groupId")?: return
        viewModel.setGroupId(groupId)
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
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current;

//    val groups = remember { mutableStateListOf<Group>() }
    var group = remember { mutableStateOf("") }
    var group1 = "";

    var groupId = remember { mutableStateOf("") }
    var groupName = remember { mutableStateOf("") }
    var groupDescription = remember { mutableStateOf("") }
    var groupExpenses = remember { mutableStateOf(0) }
    var groupOwnerId = remember { mutableStateOf("") }


    coroutineScope.launch {
        val response = RetrofitClient().groupApi.getGroupById(viewModel.getGroupId())
//        val response = viewModel.;
//        response.add
        if (response.isSuccessful) {
            val body = response.body()
            if (body == null) return@launch
            group.value
            groupId.value = body.id;
            groupName.value = body.name;
            groupDescription.value = body.description.toString();
            groupExpenses.value = body.totalExpenses
            groupOwnerId.value = body.ownerId;
        }

        println("WHAT IS THIS " + groupName)
//        println(groupExpenses)
    }

    println("POST_COROUTINE" + groupName)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BalanceCard(
            groupName = groupName.value,
            groupDescription = "",
            balance = "${groupExpenses.value}",
            total = "${groupExpenses.value}",
            onCardClick = {
                val intent = Intent(context, GroupSettingsActivity::class.java)
                intent.putExtra("groupId", groupId.value)
                context.startActivity(intent)},
            onNotificationClick = { /*TODO*/ })
    }
}