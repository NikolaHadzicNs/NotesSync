package com.nikola.notessync.presentation.scenes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikola.notessync.presentation.ui.theme.AddButtonBackgroundColor
import com.nikola.notessync.presentation.ui.theme.AddButtonContentColor
import com.nikola.notessync.presentation.ui.theme.NotesSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteButton(clicked: () -> Unit) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = AddButtonBackgroundColor,
            contentColor = AddButtonContentColor
        ),
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
                color = Color.DarkGray,
                fontSize = 18.sp
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
fun AddButtonPreview() {
    NotesSyncTheme {
        AddNoteButton {

        }
    }
}