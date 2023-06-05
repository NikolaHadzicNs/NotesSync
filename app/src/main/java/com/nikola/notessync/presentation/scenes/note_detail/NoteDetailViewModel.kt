package com.nikola.notessync.presentation.scenes.note_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikola.notessync.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {


    fun onEvent(event: NoteDetailEvent) {
        when(event) {
            is NoteDetailEvent.AddNote -> {
                viewModelScope.launch {
                    noteUseCases.addNoteUseCase(event.note)
                }
            }
            is NoteDetailEvent.DeleteNote -> {

            }
        }
    }
}