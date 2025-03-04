package com.coinscope.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.coinscope.CoinDetailsRoute
import com.coinscope.CoinsRoute
import com.coinscope.ExchangesRoute
import com.coinscope.SearchRoute
import com.coinscope.design.resources.CoinScopeTheme
import com.coinscope.ui.coins.CoinsScreen
import com.coinscope.ui.details.CoinDetailsScreen
import com.coinscope.ui.exchanges.ExchangesScreen
import com.coinscope.ui.search.SearchScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CoinScopeTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedVisibility(visible = shouldShowBottomNav(currentDestination)) {
                            BottomNavBar(navController, currentDestination)
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = CoinsRoute
                        ) {
                            composable<CoinsRoute> {
                                CoinsScreen { coin ->
                                    coin.id?.let { navController.navigate(CoinDetailsRoute(it)) }
                                }
                            }
                            composable<SearchRoute> {
                                SearchScreen { id ->
                                    id?.let { navController.navigate(CoinDetailsRoute(it)) }
                                }
                            }
                            composable<ExchangesRoute> { ExchangesScreen() }
                            composable<CoinDetailsRoute> { backStackEntry ->
                                val id: String = backStackEntry.toRoute<CoinDetailsRoute>().id
                                CoinDetailsScreen(id = id) {
                                    navController.navigateUp()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoinScopeTheme {
        Greeting("Android")
    }
}