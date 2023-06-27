package com.nikola.notessync.presentation.scenes.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nikola.notessync.domain.model.Note

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NoteCard(note: Note, clicked: () -> Unit, longClicked: () -> Unit, selected: Boolean = false) {
    var alpha by remember {
        mutableStateOf(1f)
    }
    if (selected) {
        alpha = 0.3f
    } else {
        alpha = 1f
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .alpha(alpha),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .combinedClickable(
                onClick = {
                    clicked()
                },
                onLongClick = {
                    longClicked()
                }
            )) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = note.title,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(8.dp),
                text = note.content
            )
        }
    }
}