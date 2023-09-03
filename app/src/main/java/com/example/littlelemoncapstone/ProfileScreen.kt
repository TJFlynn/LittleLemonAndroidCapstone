package com.example.littlelemoncapstone

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.littlelemoncapstone.ui.theme.Karla
import com.example.littlelemoncapstone.ui.theme.LittleLemonColor
import com.example.littlelemoncapstone.ui.theme.Markazi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(sharedPreferences :SharedPreferences, navController: NavController)
{
    val firstName = sharedPreferences.getString("firstName", "").toString()
    val lastName = sharedPreferences.getString("lastName", "").toString()
    val email = sharedPreferences.getString("email", "").toString()

    Column(
        modifier = Modifier
            .padding(0.dp, 10.dp, 0.dp, 10.dp)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(0.dp, 10.dp, 0.dp, 10.dp)
        )
        Text(text = "Profile Information:",
            fontFamily = Markazi,
            fontSize =  24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(10.dp)
        )
        Text(text = firstName,
            fontFamily = Karla,
            fontSize =  16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(10.dp)
        )
        Text(text = lastName,
            fontFamily = Karla,
            fontSize =  16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(10.dp)
        )
        Text(text = email,
            fontFamily = Karla,
            fontSize =  16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(10.dp)
        )
        Button(onClick =
        {
            with (sharedPreferences.edit()) {
                putString("firstName", "")
                putString("lastName", "")
                putString("email", "")
                putBoolean("isLoggedIn", false)
                apply()
            }
            navController.navigate(Onboarding.route)
        },
            colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.yellow, contentColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
            Text("Logout")
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
*/