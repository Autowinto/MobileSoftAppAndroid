package com.example.weshussy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weshussy.ui.theme.WeShussyTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileView()
                }
            }
        }
    }
}
@Preview
@Composable
fun ProfileView() {
    var spaceVal = 12
    BaseActivity(slotContent = {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Update Profile")
        }
        Spacer(modifier = Modifier.height(spaceVal.dp))
        FirstNameTextField()
        Spacer(modifier = Modifier.height(spaceVal.dp))
        LastNameTextField()
        Spacer(modifier = Modifier.height(spaceVal.dp))
        PhoneTextField()
        Spacer(modifier = Modifier.height(spaceVal.dp))
        EmailTextField()
        Spacer(modifier = Modifier.height(spaceVal.dp))
        PasswordTextField()
        Spacer(modifier = Modifier.height(spaceVal.dp))
        NotificationSwitch()
        Spacer(modifier = Modifier.height(spaceVal.dp))
        UpdateProfile()
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstNameTextField() {
    var firstName by rememberSaveable { mutableStateOf("") }

    TextField(
        value = firstName,
        onValueChange = { firstName = it },
        label = { Text("Enter First Name") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastNameTextField() {
    var lastName by rememberSaveable { mutableStateOf("") }

    TextField(
        value = lastName,
        onValueChange = { lastName = it },
        label = { Text("Enter Last Name") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneTextField() {
    var phone by rememberSaveable { mutableStateOf("") }

    TextField(
        value = phone,
        onValueChange = { phone = it },
        label = { Text("Enter Phone Number") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField() {
    var email by rememberSaveable { mutableStateOf("") }

    TextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Enter Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField() {
    var password by rememberSaveable { mutableStateOf("") }

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Enter password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
@Preview
fun NotificationSwitch() {
    var notificationsEnabled by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Notifications")
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = notificationsEnabled,
            onCheckedChange = { notificationsEnabled = it }
        )
    }
}
@Composable
fun UpdateProfile() {
    Button(
        onClick = { /* Handle update action */ },
    ) {
        Text("Update")
    }
}


