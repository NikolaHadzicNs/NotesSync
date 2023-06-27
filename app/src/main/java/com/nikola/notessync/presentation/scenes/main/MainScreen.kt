package com.nikola.notessync.presentation.scenes.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nikola.notessync.presentation.navigation.Screen
import com.nikola.notessync.presentation.scenes.components.NoteCard
import com.nikola.notessync.presentation.ui.theme.NotesSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier.padding(12.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (state.selectedNotes.isNotEmpty()) {
                    state.selectedNotes.forEach {
                        viewModel.onEvent(MainEvent.DeleteNote(it))
                    }
                } else {
                    navController.navigate(Screen.NoteDetailScreen.route + "/${-1}")
                }
            }) {

                if (state.selectedNotes.isNotEmpty()) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete notes")
                } else {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { contentPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = state.search,
                onValueChange = { viewModel.onEvent(MainEvent.SearchNote(it)) },
                placeholder = {
                    Text(text = "Search")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
                },
                trailingIcon = if (state.search.isNotEmpty()) {
                    {
                        IconButton(
                            onClick =
                            {
                                viewModel.onEvent(MainEvent.SearchNote(""))
                            }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear icon"
                            )
                        }
                    }
                } else {
                    null
                }
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp)
            ) {
                items(state.notes.size) { i ->
                    val note = state.notes[i]
                    val selected = state.selectedNotes.contains(note)
                    NoteCard(
                        note = note,
                        clicked = {
                            if (viewModel.state.value.selectedNotes.isEmpty()) {
                                navController.navigate(Screen.NoteDetailScreen.route + "/${state.notes[i].id}")
                            } else {
                                if (selected) {
                                    viewModel.onEvent(MainEvent.UnselectNote(note))
                                } else {
                                    viewModel.onEvent(MainEvent.SelectNote(note))
                                }
                            }
                        },
                        longClicked = {
                            //todo set selectionState on
                            if (viewModel.state.value.selectedNotes.contains(note)) {
                                viewModel.onEvent(MainEvent.ClearSelectedNotes)
                            } else {
                                viewModel.onEvent(MainEvent.SelectNote(note))
                            }
                        },
                        selected
                    )

                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NotesSyncTheme {
        MainScreen(rememberNavController())
    }
}