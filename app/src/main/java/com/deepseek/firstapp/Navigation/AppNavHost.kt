package com.deepseek.firstapp.Navigation

import android.graphics.pdf.content.PdfPageGotoLinkContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.deepseek.firstapp.screens.login.LoginScreen
import com.deepseek.firstapp.screens.register.RegisterScreen


@Composable
fun AppNavHost(
    modifier: Modifier= Modifier,
    navController:NavHostController=rememberNavController(),
    startDestination: String=ROUTE_SPLASH
){
    NavHost(
        navController=navController,
        startDestination=startDestination,
        modifier=modifier

    ){
        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_REGISTER){
            RegisterScreen(navController)
        }
        composable(ROUTE_SPLASH){
            SplashScreen(navController)
        }
    }
}