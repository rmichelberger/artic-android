package com.example.artic

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object ArtListView : Screen("artList")
    object ArtDetailView : Screen("artDetail?id={id}")
}

fun Screen.ArtDetailView.route(id: Int) = "artDetail?id=$id"
val Screen.ArtDetailView.arguments: List<NamedNavArgument>
    get() = listOf(navArgument("id") { type = NavType.IntType })
fun Screen.ArtDetailView.parseArguments(navBackStackEntry: NavBackStackEntry): Int? =
    navBackStackEntry.arguments?.getInt("id")
