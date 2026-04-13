package com.deepseek.firstapp.screens.Dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deepseek.firstapp.Navigation.ROUTE_ADDPRODUCT
import com.deepseek.firstapp.data.AuthViewModel
import com.deepseek.firstapp.screens.Homescreen.HomeCard
import com.google.rpc.context.AttributeContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController){
    Scaffold(
        //top bar

        topBar = {
            TopAppBar(
                title = {Text("Welcome to firstapp")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFD0BCFF),
                    titleContentColor = Color.Blue
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "icon"
                        )
                    }//LOGOUT
                    IconButton(onClick = {
                        myauth.logout()
                    }) {
                        Icon(
                            Icons.Default.ExitToApp,
                            contentDescription = "close icon")
                    }
                }
            )
        },
        //bottom bar
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFD0BCFF),
                contentColor = Color.Blue
            ) {
                Text("bottom bar")
            }
            NavigationBar() {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Home, contentDescription = "home icon")
                    },
                    label = {Text("Home")}
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Person, contentDescription = "person icon")
                    },
                    label = {Text("Profile")}
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Settings, contentDescription = "settings icon")
                    },
                    label = {Text("Settings")}
                )

            }
        },
        //floating action button
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "add icon")
            }
        }


    ) { innerpadding->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            val context= LocalContext.current
            val myauth= AuthViewModel(navController,context)
            var username by remember { mutableStateOf("Loading") }
            LaunchedEffect(Unit) {
                myauth.getCurrentUsername{
                    username=it
                }
            }
            Text(text="Welcome, $username")
            Spacer(modifier = Modifier.height(16.dp))
            Row(){
                HomeCard("ADD PRODUCT", background = Color.Blue, onClick = {navController.navigate(
                    ROUTE_ADDPRODUCT
                )})
                HomeCard("UPDATE PRODUCT", background = Color.Blue, onClick = {})
            }
            Row() {
                HomeCard("DELETE PRODUCT", background = Color.Blue, onClick = {})
                HomeCard("PRODUCT LIST", background = Color.Gray, onClick = {})
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun dashboardscreenpreview(){
    DashboardScreen(rememberNavController())
}
