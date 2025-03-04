package com.coinscope.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.coinscope.CoinsRoute
import com.coinscope.ExchangesRoute
import com.coinscope.R
import com.coinscope.SearchRoute
import com.coinscope.design.resources.Grey

data class TopLevelRoute<T : Any>(val name: String, val route: T, @DrawableRes val icon: Int)

val topLevelRoutes = listOf(
    TopLevelRoute("Coins", CoinsRoute, R.drawable.ic_home),
    TopLevelRoute("Search", SearchRoute, R.drawable.ic_search),
    TopLevelRoute("Exchanges", ExchangesRoute, R.drawable.ic_exchanges)
)

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String?) {
    BottomAppBar {
        topLevelRoutes.forEach { item ->
            NavigationBarItem(
                alwaysShowLabel = false,
                selected = currentRoute?.contains(item.route::class.simpleName.orEmpty()) == true,
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.name
                    )
                },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

fun shouldShowBottomNav(currentRoute: String?): Boolean {
    return topLevelRoutes.any {
        currentRoute?.contains(it.route::class.simpleName.orEmpty()) == true
    }
}