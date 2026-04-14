package com.deepseek.firstapp.screens.Splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepseek.firstapp.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.deepseek.firstapp.Navigation.ROUTE_LOGIN
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController){
    LaunchedEffect(true) {
        delay(2000) //2 second delay
        navController.navigate(ROUTE_LOGIN)

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFFB39DDB)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter= painterResource(id=R.drawable.blackgoose),
                contentDescription = "logo",
                modifier= Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier= Modifier.height(30.dp))
            Text(text="welcome to my app",
                fontSize = 28.sp,
                color=Color.Red)

        }

    }

}

@Preview(showBackground = true)
@Composable
fun splashpreview(){
    SplashScreen(rememberNavController())
}