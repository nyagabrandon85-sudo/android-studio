package com.deepseek.firstapp.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

        ) {
        Text(
            "REGISTER",
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            color = Color.Magenta,
            fontWeight = FontWeight.Bold

        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Don't have an account? Register here",
            color = Color.Blue
        )

        var fullname by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmation by remember{mutableStateOf("")}


    Spacer(modifier = Modifier.height(30.dp))
   OutlinedTextField(
       value=fullname,
       onValueChange = {fullname=it},
       label = {Text("Full name")},
       singleLine = true,
       leadingIcon = {
           Icon(
               imageVector= Icons.Default.Person,
               contentDescription = "person icon",
   )}
   )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value=email,
            onValueChange = {email=it},
            label = {Text("Email")},
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector= Icons.Default.Email,
                    contentDescription = "email icon",
                )},
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value=password,
            onValueChange = {password=it},
            label = {Text("Password")},
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector= Icons.Default.Lock,
                    contentDescription = "password icon",
                )},
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value=confirmation,
            onValueChange = {confirmation=it},
            label = {Text(" Confirm Password")},
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector= Icons.Default.Lock,
                    contentDescription = "password icon",
                )},
        )
        Spacer(modifier = Modifier.height(90.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor=Color.Blue,
            )
        ){
            Text("REGISTER", fontSize =24.sp)
        }
        TextButton(onClick={}){
            Text("Already have an account? Login in")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun registerpreview() {
    RegisterScreen()
}
