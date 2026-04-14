package com.deepseek.firstapp.screens.product

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.deepseek.firstapp.R

//AddProduct
//scaffold-topbar
//Text-addproduct
//textfield-name, price, description, imagepicker
//button-addproduct
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ADD PRODUCT") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan
                )
            )
        }
    )
    {innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var productName by remember { mutableStateOf("") }
            var price by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }
            var imageUri by remember { mutableStateOf<Uri?>(null) }
            //image picker launcher
            val imagePickerLauncher= rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ){
                uri: Uri? ->
                imageUri=uri
            }
            Text(
                text = "ADD PRODUCT",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color=Color.Magenta
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = productName,
                onValueChange = {productName=it},
                label = {Text("Enter product name")},
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = price,
                onValueChange = {price=it},
                label = {Text("Enter product price")},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = description,
                onValueChange = {description=it},
                label = {Text("Enter product description")},
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(20.dp))
            //product image picker+preview
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size(140.dp)
                    .clickable{imagePickerLauncher.launch("image/*")}
            ) {
                AsyncImage(
                    model=imageUri?:R.drawable.background,
                    contentDescription = "product",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(140.dp)

                )


            }
            OutlinedButton(onClick = {imagePickerLauncher.launch("image/*")}) {
                Text("Choose image")
            }



            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
             Text("ADD PRODUCT")
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun productpreview(){
    AddProductScreen(rememberNavController())
}
