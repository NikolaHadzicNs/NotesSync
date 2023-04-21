package com.nikola.notessync.presentation.main

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nikola.notessync.data.MockRepo
import com.nikola.notessync.data.Note

@Composable
fun MainScreen() {
    LazyVerticalGrid(
        modifier = Modifier.padding(8.dp),
        columns = GridCells.Adaptive(minSize = 100.dp)
    ) {
        items(MockRepo.notes.size) { i ->
            NoteCard(note = MockRepo.notes[i])
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(note: Note) {
    Card(
        modifier = Modifier.aspectRatio(1f).padding(8.dp)
        ,onClick = {

    }) {
        Text(text = note.title)

        Text(text = note.content)
    }
}