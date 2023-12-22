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
import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.api.interfaces.ExpenseApi
import com.example.weshussy.ui.UserSession
import com.example.weshussy.ui.theme.WeShussyTheme
import com.example.weshussy.ui.viewmodels.GroupInfoViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf



class ExpenseAddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val groupId = intent.getStringExtra("groupId")
        println("GROUP ID")
        println(groupId)
        setContent {
            WeShussyTheme {
                ExpenseAddScreen(groupId = groupId.toString())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseAddScreen(groupId: String) {
    val user = UserSession.getUser() ?: return
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val expenseAmount = remember { mutableStateOf("") }
    val expenseDescription = remember { mutableStateOf("") }
    val selectedPayer =  remember { mutableStateOf("") }
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
                    val intent = Intent(context, ExpenseActivity::class.java).apply {
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
                value = expenseAmount.value,
                onValueChange = { expenseAmount.value = it },
                label = { Text("Expense") },
                leadingIcon = { Text("$") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = expenseDescription.value,
                onValueChange = { expenseDescription.value = it },
                label = { Text("Expense description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            DropdownMenuPayer(
                payerList = groupMembers,
                selectedPayer = selectedPayer.value,
                onPayerSelected = { selectedPayer.value = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
            GroupMembersSelection(groupMembers, selectedMembers)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    println("Coroutine aooooga")
                    coroutineScope.launch {
                    val response = RetrofitClient().expenseApi.createExpense(
                        ExpenseApi.ExpenseCreateRequestBody(
                            userId = user.id,
                            groupId = groupId,
                            amount = expenseAmount.value,
                            name = expenseDescription.value,
                        )
                    )
                    println("AAOOOOOOOGA")
                    println(response)
                    if (response.isSuccessful) {
                        Intent(
                            context,
                            ExpenseActivity::class.java
                        ).also { context.startActivity(it) }
                    }
                } },
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
                        text = { Text(payer) }
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
