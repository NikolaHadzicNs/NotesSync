package com.nikola.notessync.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikola.notessync.presentation.ui.theme.NoteBlue
import com.nikola.notessync.presentation.ui.theme.NoteGreen
import com.nikola.notessync.presentation.ui.theme.NoteRed
import com.nikola.notessync.presentation.ui.theme.NoteYellow

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var title: String,
    var content: String,
    var date: Long,
    var fontStyle: Int,
    var theme: Int
) {
    companion object {
        val noteColors = listOf(NoteRed, NoteBlue, NoteYellow, NoteGreen)
    }
}