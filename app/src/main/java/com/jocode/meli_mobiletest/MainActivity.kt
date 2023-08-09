package com.jocode.meli_mobiletest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jocode.meli_mobiletest.navigation.AppNavGraph
import com.jocode.navigation.Screens
import com.jocode.ui.theme.MeliMobileTestTheme
import com.jocode.ui.theme.statusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeliMobileTestTheme(dynamicColor = false) {

                val systemUiController = rememberSystemUiController()
                val navController = rememberNavController()
                systemUiController.setStatusBarColor(color = MaterialTheme.colorScheme.statusBarColor)

                AppNavGraph(
                    startDestination = Screens.Search.route,
                    navController = navController
                )
            }
        }
    }
}