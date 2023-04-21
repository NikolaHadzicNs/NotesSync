package com.nikola.notessync.data

import androidx.compose.runtime.mutableStateListOf

object MockRepo {
    val notes = mutableStateListOf<Note>()

    init {
        for (i in 0..10) {
            notes.add(Note("Title $i", "Content is new and refreshed $i"))
        }
    }
}

data class Note(
    val title: String,
    val content: String
)