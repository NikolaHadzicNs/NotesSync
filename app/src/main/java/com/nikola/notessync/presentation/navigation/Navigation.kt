package com.nikola.notessync.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nikola.notessync.presentation.scenes.main.MainScreen
import com.nikola.notessync.presentation.scenes.note_detail.NoteDetailScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(route = Screen.NoteDetailScreen.route + "/{id}") {
            val id = it.arguments?.getString("id")
            NoteDetailScreen(navController = navController, noteId = id)
        }
    }
}