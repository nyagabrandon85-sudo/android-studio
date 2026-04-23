package com.deepseek.firstapp.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.deepseek.firstapp.Navigation.ROUTE_DASHBOARD
import com.deepseek.firstapp.Navigation.ROUTE_LOGIN
import com.deepseek.firstapp.Navigation.ROUTE_REGISTER
import com.deepseek.firstapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class AuthViewModel(var navController: NavHostController,var context: Context) {
    var mAuth = FirebaseAuth.getInstance()

    //register function
    fun signup(fullname: String, email: String, password: String, confirmpass: String) {
        //validation
        if (email.isBlank() || password.isBlank() || confirmpass.isBlank()) {
            Toast.makeText(context, "Email and password cannot be  blank", Toast.LENGTH_LONG).show()
            return
        } else if (password != confirmpass) {
            Toast.makeText(context, "password do not match", Toast.LENGTH_LONG).show()
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userdata =
                            User(fullname, email, password, mAuth.currentUser!!.uid, "user")
                        //realtime db
                        val regRef = FirebaseDatabase.getInstance().getReference()
                            .child("Users/" + mAuth.currentUser!!.uid)
                        regRef.setValue(userdata).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "user registered successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_LOGIN)
                            } else {
                                Toast.makeText(
                                    context,
                                    "${it.exception!!.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_REGISTER)
                            }
                        }
                    } else {
                        navController.navigate(ROUTE_REGISTER)
                    }
                }
        }
    }
    //login function
    fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "successfully login in", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_DASHBOARD)
            } else {
                Toast.makeText(context, "error logging in", Toast.LENGTH_SHORT).show()
            }
        }

    }
    //logout function
    fun logout(){
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
        {popUpTo(0)}
    }
    //get current user
    fun getCurrentUserName(onResult: (String) -> Unit) {
        val userId = mAuth.currentUser?.uid

        if (userId != null) {
            val ref = FirebaseDatabase.getInstance()
                .getReference("Users/$userId")

            ref.get().addOnSuccessListener { snapshot ->
                val name = snapshot.child("fullname").value.toString()
                onResult(name)
            }.addOnFailureListener {
                onResult("User")
            }
        } else {
            onResult("User")
        }
    }
    //get the whole user profile
    fun getUserData(onResult: (User?) -> Unit) {
        val userId = mAuth.currentUser?.uid

        if (userId != null) {
            val ref = FirebaseDatabase.getInstance()
                .getReference("Users/$userId")

            ref.get().addOnSuccessListener { snapshot ->
                val user = snapshot.getValue(User::class.java)
                onResult(user)
            }.addOnFailureListener {
                onResult(null)
            }
        } else {
            onResult(null)
        }
    }

}

