package com.example.littlelemoncapstone

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost

@Composable
fun Navigation(navController : NavHostController, sharedPreferences: SharedPreferences,  database : AppDatabase) {
    val isLoggedIn = MutableLiveData<Boolean>()
    isLoggedIn.value= sharedPreferences.getBoolean("isLoggedIn", false)

    val startDest = if (isLoggedIn.value == true) Home.route else Onboarding.route
            
    NavHost(navController = navController,
        startDestination = startDest)
    {
        composable(Home.route) {
            HomeScreen(navController, database)
        }
        composable(Onboarding.route) {
            OnboardingScreen(sharedPreferences, navController)
        }
        composable(Profile.route) {
            ProfileScreen(sharedPreferences, navController)
        }
    }
}
