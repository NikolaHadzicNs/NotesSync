package com.nikola.notessync.domain.use_case

import com.nikola.notessync.domain.model.Note
import com.nikola.notessync.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.insertNote(note)
    }
}