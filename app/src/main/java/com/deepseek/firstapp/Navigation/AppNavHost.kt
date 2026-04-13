package com.deepseek.firstapp.Navigation

import android.graphics.pdf.content.PdfPageGotoLinkContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deepseek.firstapp.Splashscreen.SplashScreen
import com.deepseek.firstapp.screens.login.LoginScreen
import com.deepseek.firstapp.screens.product.AddProductScreen
import com.deepseek.firstapp.screens.register.RegisterScreen


@Composable
fun AppNavHost(
    modifier: Modifier= Modifier,
    navController: NavHostController =rememberNavController(),
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
        composable(ROUTE_ADDPRODUCT){
            AddProductScreen(navController)
        }
    }
}