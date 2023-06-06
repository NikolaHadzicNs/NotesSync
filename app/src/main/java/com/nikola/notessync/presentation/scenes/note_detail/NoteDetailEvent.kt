package com.nikola.notessync.presentation.scenes.note_detail

import androidx.compose.ui.graphics.Color
import com.nikola.notessync.domain.model.Note

sealed class NoteDetailEvent {
    data class DeleteNote(val note: Note): NoteDetailEvent()
    data class UpdateTitle(val title: String) : NoteDetailEvent()

    data class UpdateContent(val content: String) : NoteDetailEvent()
    data class ChangeBackground(val color: Int) : NoteDetailEvent()

    data class ChangeFontColor(val color: Int) : NoteDetailEvent()

    object AddNote : NoteDetailEvent()
}