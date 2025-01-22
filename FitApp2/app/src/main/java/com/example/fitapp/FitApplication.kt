package com.example.fitapp

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize FirebaseApp here
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this); // Ensure Firebase is initialized
        }
    }

}