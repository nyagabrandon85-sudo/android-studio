package com.deepseek.firstapp.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deepseek.firstapp.Navigation.ROUTE_REGISTER
import com.deepseek.firstapp.R
import com.deepseek.firstapp.data.AuthViewModel
import java.util.concurrent.locks.Lock

@Composable
fun LoginScreen(navController: NavController){
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text="LOGIN",
            color=Color.Magenta,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text="Already have an account? LOGIN",
            color = Color.Blue,
            fontSize = 24.sp,
        )
            Spacer(modifier = Modifier.height(20.dp))
        Image(
                painter = painterResource(id=R.drawable.company),
                contentDescription = "logo",
            modifier= Modifier
                .size(200.dp)
                .clip(CircleShape)

            )
        Spacer(modifier=Modifier.height(20.dp))
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        OutlinedTextField(
            value = email,
            onValueChange = {email=it},
            label = {Text("Email Address")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "email icon"
                )
            },
            modifier = Modifier.fillMaxWidth()

        )
        OutlinedTextField(
            value=password,
            onValueChange = {password=it},
            label={Text("Password")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "password icon"
                )

            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
        )
       Spacer(modifier= Modifier.height(20.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor=Color.Blue,
            )
        ){
            Text("LOGIN", fontSize =24.sp)
        }
        TextButton(onClick={navController.navigate(ROUTE_REGISTER)}){
            Text("Don't have an account? Register")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun loginscreenpreview(){
 LoginScreen(rememberNavController())
}
