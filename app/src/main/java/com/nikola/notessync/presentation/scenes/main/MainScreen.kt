package com.nikola.notessync.presentation.scenes.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nikola.notessync.domain.model.Note
import com.nikola.notessync.presentation.navigation.Screen
import com.nikola.notessync.presentation.ui.theme.NotesSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = state.search,
            onValueChange = { viewModel.onEvent(MainEvent.SearchNote(it)) },
            placeholder = {
                Text(text = "Search")
            })

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp)
        ) {
            items(state.notes.size + 1) { i ->
                if (i == 0) {
                    AddNote {
                        navController.navigate(Screen.NoteDetailScreen.route + "/${-1}")
                    }
                } else {
                    NoteCard(
                        note = state.notes[i - 1]
                    ) {
                        navController.navigate(Screen.NoteDetailScreen.route + "/${state.notes[i - 1].id}")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(note: Note, clicked: () -> Unit) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(8.dp),
        onClick = {
            clicked()
        },
        colors = CardDefaults.cardColors(
            containerColor = Note.noteColors[note.noteColor],
            contentColor = Note.fontColors[note.fontColor]
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp), text = note.title
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = note.content
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNote(clicked: () -> Unit) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(8.dp),
        onClick = {
            clicked()
        }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp), text = "Add note",
                color = Color.DarkGray
            )

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add note",
                modifier = Modifier.size(60.dp)
            )
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