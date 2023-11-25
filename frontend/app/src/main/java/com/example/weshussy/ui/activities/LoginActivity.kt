package com.example.weshussy.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weshussy.R
import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.api.interfaces.UserApi.UserLoginRequestBody
import com.example.weshussy.ui.UserSession
import com.example.weshussy.ui.theme.WeShussyTheme
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeShussyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo), // Replace 'logo' with the actual resource name of your logo
            contentDescription = "Logo",
            modifier = Modifier
                .height(250.dp)
                .width(250.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login Text
        Text(text = "LOGIN", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Email Input
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Password Input
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(onClick = {
            coroutineScope.launch {
                    val response = RetrofitClient().userApi.loginUser(UserLoginRequestBody(email, password))
                    if (response.isSuccessful) {
                        println("USER HERE")
                        println(response.body())
                        UserSession.setUser(response.body()!!)
                        Intent(
                            context,
                            HomeActivity::class.java
                        ).also { context.startActivity(it) }
                    } else {
                        println(response.errorBody())
                    }

            }

        }) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Register Button
        Button(onClick = {
            Intent(
                context,
                RegisterActivity::class.java
            ).also { context.startActivity(it) }
        }) {
            Text(text = "Register")
        }
    }
}
