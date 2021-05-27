package com.mes.shiestywave

import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mes.shiestywave.ui.theme.ShiestyWaveTheme
import com.mes.shiestywave.ui.view.ArtistScreen
import com.mes.shiestywave.ui.view.SongScreen
import com.mes.shiestywave.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalComposeApi
@Composable
fun ShiestyWaveApp(homeViewModel: HomeViewModel) {
    ShiestyWaveTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            val navController = rememberNavController()
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = false)
            }
            NavHost(navController = navController, startDestination = "songs") {
                composable("songs") {
                    SongScreen(
                    homeViewModel = homeViewModel, navController = navController)
                }
                composable("artists") {
                    ArtistScreen(homeViewModel, navController = navController)
                }
            }
        }
    }
}

// val navController = rememberNavController()
// val coroutineScope = rememberCoroutineScope()
// This top level scaffold contains the app drawer, which needs to be accessible
// from multiple screens. An event to open the drawer is passed down to each
// screen that needs it.
// val scaffoldState = rememberScaffoldState()

// val navBackStackEntry by navController.currentBackStackEntryAsState()
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
