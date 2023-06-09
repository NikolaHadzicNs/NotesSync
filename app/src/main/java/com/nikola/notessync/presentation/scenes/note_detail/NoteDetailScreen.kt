package com.nikola.notessync.presentation.scenes.note_detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nikola.notessync.domain.model.Note
import com.nikola.notessync.presentation.scenes.components.ColorChooser
import com.nikola.notessync.presentation.ui.theme.NotesSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    navController: NavController,
    noteId: String?,
    viewModel: NoteDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state
    var settingsVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        viewModel.getNote(noteId?.toInt())
    }

    Column(
        modifier = Modifier
            .background(Note.noteColors[state.value.note.noteColor])
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.End
    ) {

        IconButton(onClick = {
            settingsVisible = !settingsVisible
        }) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
        }

        if (settingsVisible) {
            ColorChooser(selectedColor = state.value.note.noteColor,
                selectedFontColor = state.value.note.fontColor,
                selectNoteColor = {
                    viewModel.onEvent(NoteDetailEvent.ChangeBackground(it))
                },
                selectFontColor = {
                    viewModel.onEvent(NoteDetailEvent.ChangeFontColor(it))
                })
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.note.title,
            onValueChange = { viewModel.onEvent(NoteDetailEvent.UpdateTitle(it)) },
            placeholder = {
                Text(text = state.value.titlePlaceHolder)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                textColor = Note.fontColors[state.value.note.fontColor]
            ),
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = state.value.note.content,
            onValueChange = { viewModel.onEvent(NoteDetailEvent.UpdateContent(it)) },
            placeholder = {
                Text(text = state.value.contentPlaceHolder)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                textColor = Note.fontColors[state.value.note.fontColor]
            ),
            textStyle = TextStyle(fontSize = 14.sp)
        )
    }

    BackHandler(true) {
        viewModel.onEvent(NoteDetailEvent.AddNote)
        navController.navigateUp()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NotesSyncTheme {
        NoteDetailScreen(rememberNavController(), "2")
    }
}