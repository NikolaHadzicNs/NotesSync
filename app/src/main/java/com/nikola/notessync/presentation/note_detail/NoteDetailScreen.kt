package com.nikola.notessync.presentation.note_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nikola.notessync.data.MockRepo
import com.nikola.notessync.presentation.ui.theme.NotesSyncTheme

@Composable
fun NoteDetailScreen(navController: NavController, noteId: String?) {
    var title by remember {
        mutableStateOf(MockRepo.notes[noteId?.toInt() ?: 0].title)
    }

    var content by remember {
        mutableStateOf(MockRepo.notes[noteId?.toInt() ?: 0].content)
    }

    noteId?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            BasicTextField(value = title, onValueChange = { title = it })

            Spacer(modifier = Modifier.height(24.dp))

            BasicTextField(value = content, onValueChange = { content = it })
        }
    } ?: run {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Wrong id")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NotesSyncTheme {
        NoteDetailScreen(rememberNavController(), "2")
    }
}