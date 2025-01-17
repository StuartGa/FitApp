package com.example.fitapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fitapp.presentation.ui.theme.FitAppTheme
import com.example.fitapp.presentation.ui.theme.screen.AuthScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity:ComponentActivity() {


    private val PERMISSION_REQUEST_CODE = 101
    private val activityRecognitionPermission = android.Manifest.permission.ACTIVITY_RECOGNITION

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        if (isPermissionGranted(activityRecognitionPermission)) {
            // Permission granted, proceed with activity recognition tasks
            Toast.makeText(
                this,
                "Activity Recognition Permission already granted!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            // Request the permission
            requestPermission(activityRecognitionPermission)
        }



        setContent{
            FitAppTheme {
            Scaffold(Modifier.fillMaxSize()) { padding ->
                AuthScreen(onClick())
            }
                }
        }




    }


    private fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    // Request permission
    private fun requestPermission(permission: String) {
        ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSION_REQUEST_CODE)
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can proceed with the feature
                Toast.makeText(this, "Activity Recognition Permission granted!", Toast.LENGTH_SHORT).show()
            } else {
                // Permission denied, handle appropriately
                Toast.makeText(this, "Activity Recognition Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }



// Handle the result of the permission request


    fun onClick() = {}



}
