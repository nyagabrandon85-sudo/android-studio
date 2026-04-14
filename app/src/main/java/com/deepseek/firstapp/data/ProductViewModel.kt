package com.deepseek.firstapp.data

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavHostController

class ProductViewModel(var navController: NavHostController, var context: Context){
    var cloudinaryUrl="https://api.cloudinary.com/v1_1/dscyndwlo/image/upload"
    val uploadPreset="products"
    //upload product
    //update product
    //delete product
    //fetch product
}