package com.nikola.notessync.presentation.scenes.note_detail

import com.nikola.notessync.domain.model.Note

data class NoteDetailState(
    val note: Note = Note(null, "", "", 0),
    var titlePlaceHolder: String = "",
    var contentPlaceHolder: String = ""
)
