package com.nikola.notessync.presentation.scenes.main

import com.nikola.notessync.domain.model.Note

sealed class MainEvent {
    data class ClickNote(val note: Note): MainEvent()
    data class DeleteNote(val note: Note): MainEvent()
    data class SearchNote(val search: String): MainEvent()

    data class SelectNote(val note: Note): MainEvent()

    object ClearSelectedNotes : MainEvent()

    data class UnselectNote(val note: Note): MainEvent()
}

