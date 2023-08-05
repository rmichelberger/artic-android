package com.example.artic

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.artic.art.detail.ArtDetailView
import com.example.artic.art.list.ArtListView

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ArtListView.route) {
        composable(Screen.ArtListView.route) {
            ArtListView { id ->
                navController.navigate(
                    Screen.ArtDetailView.route(id)
                )
            }
        }
        composable(
            Screen.ArtDetailView.route, arguments = Screen.ArtDetailView.arguments
        ) { navBackStackEntry ->
            Screen.ArtDetailView.parseArguments(navBackStackEntry = navBackStackEntry)?.let {
                ArtDetailView(id = it) {
                    navController.popBackStack(
                        Screen.ArtListView.route, false
                    )
                }
            }
        }
    }
}