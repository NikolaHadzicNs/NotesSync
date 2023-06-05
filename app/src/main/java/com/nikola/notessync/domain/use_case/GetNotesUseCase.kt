package com.nikola.notessync.domain.use_case

import com.nikola.notessync.domain.model.Note
import com.nikola.notessync.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(search: String = ""): Flow<List<Note>> {
        if (search.isEmpty()) {
            return repository.getNotes()
        } else {
            return repository.getNotes().map { notes ->
                notes.filter {
                    it.title.lowercase().contains(search, true) or
                    it.content.lowercase().contains(search, true)
                }.sortedBy { it.date }
            }
        }
    }
}