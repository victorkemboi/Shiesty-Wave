package com.mes.shiestywave

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mes.shiestywave.ui.theme.ShiestyWaveTheme
import com.mes.shiestywave.ui.view.HomeScreen
import com.mes.shiestywave.ui.viewmodel.HomeViewModel

@ExperimentalComposeApi
@Composable
fun ShiestyWaveApp(homeViewModel: HomeViewModel) {
    ShiestyWaveTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = false)
            }

            val navController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            // This top level scaffold contains the app drawer, which needs to be accessible
            // from multiple screens. An event to open the drawer is passed down to each
            // screen that needs it.
            val scaffoldState = rememberScaffoldState()

            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentRoute = navBackStackEntry?.destination?.route ?: MainDestinations.HOME_ROUTE
//            Scaffold(
//                scaffoldState = scaffoldState,
//                drawerContent = {
//                    AppDrawer(
//                        currentRoute = currentRoute,
//                        navigateToHome = { navController.navigate(MainDestinations.HOME_ROUTE) },
//                        navigateToInterests = { navController.navigate(MainDestinations.INTERESTS_ROUTE) },
//                        closeDrawer = { coroutineScope.launch { scaffoldState.drawerState.close() } }
//                    )
//                }
//            ) {
//                JetnewsNavGraph(
//                    appContainer = appContainer,
//                    navController = navController,
//                    scaffoldState = scaffoldState
//                )
//            }
            HomeScreen(homeViewModel)
        }
    }
}
