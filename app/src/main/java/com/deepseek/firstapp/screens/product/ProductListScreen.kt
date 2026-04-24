package com.deepseek.firstapp.screens.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.deepseek.firstapp.Navigation.ROUTE_UPDATEPRODUCT
import com.deepseek.firstapp.data.ProductViewModel
import com.deepseek.firstapp.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title={Text("Product lIST")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFD0BCFF)
                )
            )
        }
    ) {
            innerpadding ->
        var context= LocalContext.current
        var myproductviewmodel= ProductViewModel(navController, context)

        val product= remember { mutableStateOf(Product("","","","","")) }
        val products=remember { mutableStateListOf<Product>()}

        //fetch products
        LaunchedEffect(Unit) {
            myproductviewmodel.allProducts(product,products)

        }
        LazyColumn(modifier = Modifier.padding(innerpadding)) {
            items(products){
                    item ->
                Card(
                    modifier = Modifier.padding(10.dp)
                        .fillMaxWidth(),
                ) {
                    //image preview

                    AsyncImage(
                        model=item.imageUrl,
                        contentDescription = "product",
                        contentScale = ContentScale.Crop,
                        modifier= Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(text = "Price: KES ${item.price}")
                    Text(text =item.description)

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        //  Delete
                        Text(
                            text = "Delete",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .clickable {
                                    myproductviewmodel.deleteProduct(item.id)
                                }
                        )
                        // ✏ Update
                        Text(
                            text = "Update",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate("$ROUTE_UPDATEPRODUCT/${item.id}")
                                }
                        )
                    }
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun ProductListPreview(){
    ProductListScreen(rememberNavController())
}


