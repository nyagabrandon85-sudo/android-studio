package com.deepseek.firstapp.screens.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.deepseek.firstapp.Navigation.ROUTE_PROFILE
import com.deepseek.firstapp.data.ProductViewModel
import com.deepseek.firstapp.models.Product

@Composable
fun UserDashboard(navController: NavHostController) {
    val context = LocalContext.current
    val productViewModel = ProductViewModel(navController, context)
    val products = remember { mutableStateListOf<Product>() }
    val product = remember { mutableStateOf(Product("", "", "", "", "")) }

    LaunchedEffect(Unit) {
        productViewModel.allProducts(product, products)
    }

    Scaffold(

        //  BOTTOM NAV
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = true,
                    onClick = { /* Already on dashboard */ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(ROUTE_PROFILE)
                    },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
//                        navController.navigate("cart")
                    },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
                    label = { Text("Cart") }
                )
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Text(
                text = "User Dashboard",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Featured Products",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(products) { product ->
                    ProductCard(product)
                }
            }
        }
    }
}
@Composable
fun ProductCard(product: Product) {

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(250.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column(modifier = Modifier.padding(10.dp)) {

            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = product.name)
            Text(text = "Ksh ${product.price}")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun UserDashboardPreview(){
    UserDashboard(rememberNavController())
}