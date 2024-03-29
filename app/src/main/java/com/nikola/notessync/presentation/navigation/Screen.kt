package com.nikola.notessync.presentation.navigation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_route")
    object NoteDetailScreen: Screen("detail_route")
}
