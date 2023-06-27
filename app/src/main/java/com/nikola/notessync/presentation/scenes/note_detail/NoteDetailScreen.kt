package com.nikola.notessync.presentation.scenes.note_detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nikola.notessync.presentation.ui.theme.NotesSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    navController: NavController,
    noteId: String?,
    viewModel: NoteDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.getNote(noteId?.toInt())
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.End
    ) {

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
                textColor = MaterialTheme.colorScheme.onBackground
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
                textColor = MaterialTheme.colorScheme.onBackground
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