package com.example.littlelemoncapstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemoncapstone.ui.theme.Karla
import com.example.littlelemoncapstone.ui.theme.LittleLemonColor
import com.example.littlelemoncapstone.ui.theme.Markazi


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController : NavController,  database : AppDatabase)
{
    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
    var orderMenuItems = remember { mutableStateOf(false) }
    var menuItems = if(orderMenuItems.value) databaseMenuItems.sortedBy{it.title} else databaseMenuItems
    var searchPhrase = remember { mutableStateOf("") }
    var filter = remember { mutableStateOf("") }
    Column()
    {
        Row(
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .height(75.dp)
                    .padding(0.dp, 10.dp, 0.dp, 10.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile",
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .height(75.dp)
                    .padding(0.dp, 10.dp, 0.dp, 10.dp)
                    .weight(1f)
                    .clickable { navController.navigate(Profile.route) }
            )
        }
        Box(
            modifier = Modifier
                .background(LittleLemonColor.green)
                .fillMaxWidth()
        )
        {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(
                    text = "Little Lemon",
                    fontFamily = Markazi,
                    fontSize = 36.sp,
                    color = LittleLemonColor.yellow,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(.6f)
                )
                Text(
                    text = "Chicago",
                    fontFamily = Markazi,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = LittleLemonColor.cloud,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(.5f)
                )
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    fontFamily = Karla,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = LittleLemonColor.cloud,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(.5f)
                )
                TextField(
                    value = searchPhrase.value,
                    onValueChange = { text -> searchPhrase.value = text },
                    placeholder = { Text("Search menu") },
                    modifier = Modifier
                        .fillMaxWidth(.75f)
                        .padding(10.dp, 5.dp, 10.dp, 5.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "hero image",
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .padding(0.dp, 10.dp, 0.dp, 10.dp)
                    .align(Alignment.TopEnd)
            )
        }
        if (searchPhrase.value.isNotEmpty()) {
            menuItems = menuItems.filter { it.title.contains(searchPhrase.value, true) }
        }
        if (filter.value.isNotEmpty()) {
            menuItems = menuItems.filter { it.category.contains(filter.value, true) }
        }
        Text(text = "Order for delivery!",
            fontFamily = Markazi,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(10.dp))
        LazyRow {
            item {

                Button(
                    onClick = { filter.value = "" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LittleLemonColor.yellow,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Text("All")
                }
            }
            item {

                Button(
                    onClick = { filter.value = "starters" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LittleLemonColor.yellow,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Text("Starters")
                }
            }
            item {

                Button(
                    onClick = { filter.value = "mains" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LittleLemonColor.yellow,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Text("Main")
                }
            }
            item {

                Button(
                    onClick = { filter.value = "desserts" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LittleLemonColor.yellow,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Text("Desserts")
                }
            }
        }

        MenuItemsList(items = menuItems)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp, 10.dp, 5.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(modifier = Modifier.fillMaxWidth(.6f)) {
                        Text(text = menuItem.title,
                            fontFamily = Markazi,
                            fontWeight = FontWeight.Bold,
                            fontSize =  24.sp,)
                        Text(text = menuItem.description,
                            fontFamily = Karla,
                            fontWeight = FontWeight.Normal,
                            fontSize =  16.sp,)
                        Text(text = "$%.2f".format(menuItem.price),
                            fontFamily = Karla,
                            fontWeight = FontWeight.Bold,
                            fontSize =  16.sp,
                            modifier = Modifier.padding(5.dp))
                    }
                    GlideImage(model = menuItem.image, contentDescription = menuItem.title)
                }
                Divider(color = Color.Black, modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp))
            }
        )
    }
}