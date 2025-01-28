package com.example.wearos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.wearos.ui.theme.FitAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
class WearOSActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PermissionHandler(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionHandler(modifier: Modifier = Modifier) {
    val permissionState = rememberPermissionState(android.Manifest.permission.BODY_SENSORS)

    // If the permission is not granted, request it in a side-effect
    if (!permissionState.status.isGranted) {
        LaunchedEffect(Unit) {
            permissionState.launchPermissionRequest()
        }
    }

    if (permissionState.status.isGranted) {
        // Show content if permission is granted
        Text(
            text = "Permission Granted",
            modifier = modifier
        )
    } else {
        // Show a fallback message or UI if permission is not granted
        Text(
            text = "Permission Required",
            modifier = modifier
        )
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FitAppTheme {
        Greeting("Android")
    }
}
