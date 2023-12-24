package com.mohamedfathidev.accuweather.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mohamedfathidev.accuweather.R
import com.mohamedfathidev.accuweather.ui.theme.AccuWeatherAppTheme
import com.mohamedfathidev.accuweather.util.NavigationItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccuWeatherAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val topBarTitle = remember {
        mutableStateOf(NavigationItem.CurrentWeather.title)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(topBarTitle.value) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Navigation(navController = navController)
            }
        },
        bottomBar = {
            BottomNavigationBar(navController) {
                topBarTitle.value = it
            }
        }
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onValueChange: (String) -> Unit
) {
    val items = listOf(
        NavigationItem.CurrentWeather,
        NavigationItem.Forecasting
    )
    BottomNavigation(
        contentColor = MaterialTheme.colors.onBackground
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                selectedContentColor = colorResource(id = R.color.teal_200),
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                        onValueChange(item.title)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}

@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onBackground
            )
        },
        contentColor = MaterialTheme.colors.onBackground
    )
}