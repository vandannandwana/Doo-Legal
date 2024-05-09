package com.example.dolegal.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val firestoreDatabase:CollectionReference,val auth: FirebaseAuth):ViewModel(){


    fun checkUser()= auth.currentUser != null

    fun login(email: String,password: String){

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){

                Log.d("Vandan_Login","Login Successfull")

            }else{
                Log.d("Vandan_Login",task.exception?.localizedMessage.toString())
            }
        }

    }

    suspend fun addUser(name:String,email:String, password:String){

        val user = hashMapOf(
            "name" to name,
            "email" to email,
            "password" to password
        )

        firestoreDatabase.add(user).await()

    }
    fun signup(email: String, password: String){

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task->

            if(task.isSuccessful){

                Log.d("Vandan_Signup","Signup Successfull")

            }else{
                Log.d("Vandan_Signup",task.exception?.localizedMessage.toString())
            }

        }

    }


}