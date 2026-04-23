package com.deepseek.firstapp.screens.product

import com.deepseek.firstapp.data.ProductViewModel
import com.deepseek.firstapp.models.Product




import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.deepseek.firstapp.Navigation.ROUTE_LISTPRODUCT


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductScreen(
    navController: NavHostController,
    productId: String
) {

    val context = LocalContext.current
    val productViewModel = ProductViewModel(navController, context)

    // 🔹 STATES
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf<String?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    //  FETCH PRODUCT DATA
    LaunchedEffect(productId) {
        productViewModel.databaseReference.child(productId).get()
            .addOnSuccessListener { snapshot ->

                val product = snapshot.getValue(Product::class.java)

                product?.let {
                    name = it.name
                    price = it.price
                    description = it.description
                    imageUrl = it.imageUrl
                }
            }
    }

    // IMAGE PICKER
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) imageUri = uri
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Update Product", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFD0BCFF)
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //  NAME
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Product Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // PRICE
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // DESCRIPTION
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            //  IMAGE PREVIEW (OLD OR NEW)
            val imageSource: Any? = imageUri ?: imageUrl

            if (imageSource != null) {
                AsyncImage(
                    model = imageSource,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            // CHANGE IMAGE
            Button(onClick = {
                imagePickerLauncher.launch("image/*")
            }) {
                Text("Change Image")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // UPDATE BUTTON
            Button(
                onClick = {
                    productViewModel.updateProduct(
                        productId = productId,
                        name = name,
                        price = price,
                        description = description,
                        imageUri = imageUri,      // new image (optional)
                        //oldImageUrl = imageUrl    // fallback if no new image
                    )

                    navController.navigate(ROUTE_LISTPRODUCT)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Product")
            }
        }
    }
}

