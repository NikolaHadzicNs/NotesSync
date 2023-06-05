package com.nikola.notessync.domain.use_case

import com.nikola.notessync.domain.model.Note
import com.nikola.notessync.domain.repository.NoteRepository

class GetNoteByIdUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}