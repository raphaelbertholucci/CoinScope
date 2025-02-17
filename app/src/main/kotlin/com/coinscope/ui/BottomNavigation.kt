package com.coinscope.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.coinscope.R
import com.coinscope.design.resources.DarkGrey
import com.coinscope.design.resources.Dimens
import com.coinscope.design.resources.Grey
import com.coinscope.design.resources.White

sealed class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    data object Assets : BottomNavItem("assets", "Coins", R.drawable.ic_home)
    data object Search : BottomNavItem("search", "Search", R.drawable.ic_search)
    data object Exchanges : BottomNavItem("exchanges", "Exchanges", R.drawable.ic_exchanges)

    companion object {
        val items = listOf(Assets, Search, Exchanges)
    }
}

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String?) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 8.dp
    ) {
        BottomNavItem.items.forEach { item ->
            NavigationBarItem(
                alwaysShowLabel = false,
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (currentRoute == item.route) MaterialTheme.colorScheme.onBackground else Grey
                    )
                }
            )
        }
    }
}