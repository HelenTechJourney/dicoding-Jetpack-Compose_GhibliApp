package com.example.ghibliapp.ui.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Favorite : Screen("favorite")
    object DetailMovie : Screen("home/{movieId}") {
        fun createRoute(movieId: Long) = "home/$movieId"
    }
}