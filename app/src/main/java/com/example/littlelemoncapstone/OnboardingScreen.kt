package com.example.littlelemoncapstone

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemoncapstone.ui.theme.Karla
import com.example.littlelemoncapstone.ui.theme.LittleLemonColor
import com.example.littlelemoncapstone.ui.theme.Markazi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(sharedPreferences: SharedPreferences, navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(0.dp, 10.dp, 0.dp, 10.dp)
            .fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(0.dp, 10.dp, 0.dp, 10.dp))
        Text(text = "Let's get to know you",
            fontFamily = Markazi,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize =  30.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .background(LittleLemonColor.green)
                .fillMaxWidth()
                .padding(0.dp, 25.dp, 0.dp, 25.dp)
        )
        Text(text = "Personal Information",
            fontFamily = Markazi,
            fontSize =  24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(10.dp)
        )
        Text(text = error,
            fontFamily = Markazi,
            color = Color.Red,
            fontSize =  24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(10.dp)
        )
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = {
                Text("First Name",
                    fontFamily = Karla,
                    fontSize =  16.sp,
                    fontWeight = FontWeight.Normal,)
                    },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = {
                Text("Last Name",
                    fontFamily = Karla,
                    fontSize =  16.sp,
                    fontWeight = FontWeight.Normal,)
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text("email",
                    fontFamily = Karla,
                    fontSize =  16.sp,
                    fontWeight = FontWeight.Normal,)
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Button(onClick =
        {
            if (firstName.isBlank() || lastName.isBlank() || email.isBlank())
            {
                error = "Registration Unsuccessful : Please enter all data"
            }
            else
            {
                error = ""
                with (sharedPreferences.edit()) {
                    putString("firstName", firstName)
                    putString("lastName", lastName)
                    putString("email", email)
                    putBoolean("isLoggedIn", true)
                    apply()
                }
                navController.navigate(Home.route)
            }
        },
            colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.yellow, contentColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
            Text("Register")
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    OnboardingScreen()
}

 */