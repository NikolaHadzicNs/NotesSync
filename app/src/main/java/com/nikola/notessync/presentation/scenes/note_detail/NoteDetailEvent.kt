package com.nikola.notessync.presentation.scenes.note_detail

import com.nikola.notessync.domain.model.Note

sealed class NoteDetailEvent {
    data class DeleteNote(val note: Note): NoteDetailEvent()
    data class AddNote(val note: Note): NoteDetailEvent()
}