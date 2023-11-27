package com.example.weshussy.ui.activities

import TopNavBar
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.weshussy.R
import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.api.interfaces.UserApi
import com.example.weshussy.ui.UserSession
import com.example.weshussy.ui.theme.WeShussyTheme
import kotlinx.coroutines.launch

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                ProfileSettingsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettingsScreen() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val user = UserSession.getUser() ?: return

    // Realistically, this should always be true

    var firstName by remember { mutableStateOf(user.firstName) }
    var lastName by remember { mutableStateOf(user.lastName) }
    var phoneNumber by remember { mutableStateOf(user.phoneNmb) }
    var email by remember { mutableStateOf(user.email) }
    var notificationsEnabled by remember { mutableStateOf(user.enableNotifs) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopNavBar(
            title = "Account Settings",
            onBackClick = { context.startActivity(Intent(context, HomeActivity::class.java)) },
            showEdit = false,
        )

        // Profile picture
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input fields
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone number") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* Dismiss keyboard */ }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Notifications toggle
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End // Align content to the end (right)
        ) {
            Text("Enable notifications")

            // Spacer to push the switch to the right
            Spacer(modifier = Modifier.weight(1f))

            Switch(
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )
        }

        Spacer(modifier = Modifier.height(130.dp))

        // Save button
        Button(
            onClick = {
                coroutineScope.launch {
                    val response = RetrofitClient().userApi.updateUser(
                        UserApi.UpdateUserRequestBody(
                            user.id,
                            firstName,
                            lastName,
                            email,
                            phoneNumber,
                            notificationsEnabled
                        )
                    )
                    println(response)
                    if (response.isSuccessful) {
                        println("USER HERE")
                        println(response.body())
                        Intent(
                            context,
                            HomeActivity::class.java
                        ).also { context.startActivity(it) }
                    } else {
                        println(response.errorBody())
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ){
            Text("Save")
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Logout button
        Button(
            onClick = {
                UserSession.clearUser()
                context.startActivity(Intent(context, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
        ) {
            Text("Logout")
        }
    }
}