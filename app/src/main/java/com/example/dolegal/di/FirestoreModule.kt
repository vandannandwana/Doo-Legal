package com.example.dolegal.di

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dolegal.presentation.models.Users
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirestoreModule {

    @Provides
    @Singleton
    fun providesFirestoreDatabase(): CollectionReference {
        return Firebase.firestore.collection("users")
    }

    @Provides
    @Singleton
    fun providesFirebaseAuth():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

}