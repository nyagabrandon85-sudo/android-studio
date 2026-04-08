package com.deepseek.firstapp.Splashscreen

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepseek.firstapp.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deepseek.firstapp.Navigation.ROUTE_LOGIN
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (navController: NavController){
    LaunchedEffect(true) {
        delay(2000)
        navController.navigate(ROUTE_LOGIN){
            popUpTo(0)// clears back streak
        }
    }
    Box(
        modofier= Modifier
            .fillMaxSize()
            .background(color=Color.DarkGray),
        contentAlignment= Alignment.Center
    ){
        Column(horizontalAlignment=Alignment.CenterHorizontally){
            Image(
                painter = painterResource(id=R.drawable.blackgoose),
                contentDescription = "logo",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text="Welcome to firstapp",
                fontSize=25.sp
            )


        }

    }
}



@Preview(showBackground = true)
@Composable
fun SplashPreview(){
    SplashScreen(rememberNavController())
}