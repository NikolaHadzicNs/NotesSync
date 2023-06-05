package com.nikola.notessync.presentation.scenes.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                viewModelScope.launch {
                    noteUseCases.deleteNoteUseCase(event.note)
                }
            }
            is MainEvent.SearchNote -> {
                getNotes(event.search)
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