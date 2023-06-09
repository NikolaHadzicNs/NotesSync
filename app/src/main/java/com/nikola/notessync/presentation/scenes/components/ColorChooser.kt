package com.nikola.notessync.presentation.scenes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nikola.notessync.domain.model.Note

@Composable
fun ColorChooser(
    selectedColor: Int,
    selectedFontColor: Int,
    selectNoteColor: (Int) -> Unit,
    selectFontColor: (Int) -> Unit
) {
    var selectedNoteColorIndex by remember {
        mutableStateOf(selectedColor)
    }

    var selectedFontColorIndex by remember {
        mutableStateOf(selectedFontColor)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(text = "Background")
        LazyRow(
            Modifier
                .fillMaxWidth()
                .selectableGroup()
        ) {
            items(Note.noteColors.size) {
                Box(modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .border(1.dp, color = Color.Black, CircleShape)
                    .background(color = Note.noteColors[it])
                    .clickable {
                        selectedNoteColorIndex = it
                        selectNoteColor(selectedNoteColorIndex)
                    })
                Spacer(modifier = Modifier.width(24.dp))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Font color")
        LazyRow(Modifier.fillMaxWidth()) {
            items(Note.fontColors.size) {
                Box(modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .border(1.dp, color = Color.Black, CircleShape)
                    .background(color = Note.fontColors[it])
                    .clickable {
                        selectedFontColorIndex = it
                        selectFontColor(selectedFontColorIndex)
                    })
                Spacer(modifier = Modifier.width(24.dp))
            }
        }
    }
}