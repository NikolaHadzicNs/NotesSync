package com.nikola.notessync.domain.use_case

import com.nikola.notessync.domain.model.Note
import com.nikola.notessync.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}