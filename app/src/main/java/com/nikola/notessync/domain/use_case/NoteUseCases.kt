package com.nikola.notessync.domain.use_case

data class NoteUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val getNoteById: GetNoteByIdUseCase
)
