package com.nikola.notessync.presentation.scenes.note_detail

import com.nikola.notessync.domain.model.Note

sealed class NoteDetailEvent {
    data class DeleteNote(val note: Note): NoteDetailEvent()
    data class UpdateTitle(val title: String) : NoteDetailEvent()

    data class UpdateContent(val content: String) : NoteDetailEvent()

    object AddNote : NoteDetailEvent()
}