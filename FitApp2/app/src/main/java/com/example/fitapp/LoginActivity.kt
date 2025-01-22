package com.example.fitapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.fitapp.presentation.ui.theme.FitAppTheme
import com.example.fitapp.presentation.ui.theme.screen.AuthScreen
import com.example.fitapp.presentation.ui.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint

class LoginActivity  ():ComponentActivity()
{
    private val viewModel: LoginViewModel by viewModels()
    private val PERMISSION_REQUEST_CODE = 101
    private val activityRecognitionPermission = android.Manifest.permission.ACTIVITY_RECOGNITION

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        if (isPermissionGranted(activityRecognitionPermission)) {
            Toast.makeText(
                this,
                "Activity Recognition Permission already granted!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            // Request the permission
            requestPermission(activityRecognitionPermission)
        }
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.isLoggedIn.value.collect {
                if(it){
                    val sendIntent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(sendIntent)
                    finish()
                } else {
                withContext(Dispatchers.Main) {
                    setContent{
                        FitAppTheme {
                            Scaffold(Modifier.fillMaxSize()) { padding ->
                                AuthScreen(onClick())
                            }
                        }
                    }

                }
            } }
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
