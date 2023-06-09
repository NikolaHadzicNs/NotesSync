package com.nikola.notessync.presentation.scenes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nikola.notessync.domain.model.Note

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
        )
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