package com.deepseek.firstapp.screens.demo



import androidx.annotation.DrawableRes
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.deepseek.firstapp.R

data class Product(
    val name: String,
    val price: String,
    @DrawableRes  val image: Int
)
val productList = listOf(
    Product("Shoes", "1500", R.drawable.shoes),
    Product("Wine", "3000", R.drawable.wine),
    Product("Mansion", "375,000,000", R.drawable.mansion),
    Product("Background", "2500", R.drawable.background)
)

@Composable
fun democarousel(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Demo  carousel",
            color=Color.Blue,
            fontSize = 38.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Featured Products",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        // CAROUSEL (LazyRow)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(productList) { product ->//loop through each product

                ProductCard(product)//displays each product as a card
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
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            // Image
            Image(
                painter = painterResource(product.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            // Name
            Text(text = product.name)
            // Price
            Text(text = "Ksh ${product.price}")
        }
    }
}

@Preview(showBackground =true)
@Composable
fun CarouselPreview(){
    democarousel(rememberNavController())
}