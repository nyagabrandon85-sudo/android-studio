package com.deepseek.firstapp.data

import android.content.Context
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel (var navController: NavController,var context: Context){
    var mAuth= FirebaseAuth.getInstance()

}