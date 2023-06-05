package com.nikola.notessync.presentation.scenes.main

import com.nikola.notessync.domain.model.Note

data class MainState(
    val notes: List<Note> = emptyList(),
    val search: String = ""
)