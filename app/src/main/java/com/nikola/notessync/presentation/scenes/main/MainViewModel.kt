package com.nikola.notessync.presentation.scenes.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikola.notessync.domain.model.Note
import com.nikola.notessync.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    private var getNotesJob: Job? = null

    init {
        getNotes("")
    }

    fun onEvent(event: MainEvent) {
        when(event) {
            is MainEvent.ClickNote -> {

            }
            is MainEvent.DeleteNote -> {
                val list = mutableListOf<Note>()
                _state.value = state.value.copy(selectedNotes = list)
                viewModelScope.launch {
                    noteUseCases.deleteNoteUseCase(event.note)
                }
            }
            is MainEvent.SearchNote -> {
                _state.value = state.value.copy(search = event.search)
                getNotes(event.search)
            }

            is MainEvent.SelectNote -> {
                val list = mutableListOf<Note>()
                list.addAll(state.value.selectedNotes)
                list.add(event.note)
                _state.value = state.value.copy(selectedNotes = list)
            }

            MainEvent.ClearSelectedNotes -> {
                val list = mutableListOf<Note>()
                _state.value = state.value.copy(selectedNotes = list)
            }

            is MainEvent.UnselectNote -> {
                val list = mutableListOf<Note>()
                list.addAll(state.value.selectedNotes)
                list.remove(event.note)
                _state.value = state.value.copy(selectedNotes = list)
            }
        }
    }

    private fun getNotes(search: String) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotesUseCase(search)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    search = search
                    )
            }
            .launchIn(viewModelScope)
    }

}