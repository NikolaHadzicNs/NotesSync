package com.nikola.notessync.presentation.scenes.note_detail

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.nikola.notessync.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    val ocr by lazy {
        TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    }

    private val _state = mutableStateOf(NoteDetailState())
    val state: State<NoteDetailState> = _state


    fun onEvent(event: NoteDetailEvent) {
        when (event) {
            is NoteDetailEvent.AddNote -> {
                viewModelScope.launch {
                    if (state.value.note.title.isNotEmpty() || state.value.note.content.isNotEmpty())
                        noteUseCases.addNoteUseCase(state.value.note)
                }
            }

            is NoteDetailEvent.DeleteNote -> {

            }

            is NoteDetailEvent.UpdateContent -> {
                _state.value =
                    state.value.copy(note = state.value.note.copy(content = event.content))
            }

            is NoteDetailEvent.UpdateTitle -> {
                _state.value = state.value.copy(note = state.value.note.copy(title = event.title))
            }
        }
    }

    fun getTextFromImage(btm: Bitmap) {
        val image = InputImage.fromBitmap(btm, 0)
        ocr.process(image)
            .addOnSuccessListener {
                val text = state.value.note.content
                val note = state.value.note.copy(content = text + it.text)
                _state.value = state.value.copy(note = note)
            }
            .addOnFailureListener {

            }
    }

    fun getNote(id: Int?) {
        viewModelScope.launch {
            id?.let { id ->
                noteUseCases.getNoteById(id)?.let {
                    _state.value = state.value.copy(note = it)
                } ?: run {
                    _state.value = state.value.copy(
                        titlePlaceHolder = "Enter title",
                        contentPlaceHolder = "Enter content"
                    )
                }
            } ?: run {
                _state.value = state.value.copy(
                    titlePlaceHolder = "Enter title",
                    contentPlaceHolder = "Enter content"
                )
            }
        }
    }
}