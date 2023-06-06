package com.nikola.notessync.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikola.notessync.presentation.ui.theme.FontBlack
import com.nikola.notessync.presentation.ui.theme.FontBlue
import com.nikola.notessync.presentation.ui.theme.FontGreen
import com.nikola.notessync.presentation.ui.theme.FontRed
import com.nikola.notessync.presentation.ui.theme.FontWhite
import com.nikola.notessync.presentation.ui.theme.FontYellow
import com.nikola.notessync.presentation.ui.theme.NoteBlue
import com.nikola.notessync.presentation.ui.theme.NoteGreen
import com.nikola.notessync.presentation.ui.theme.NoteRed
import com.nikola.notessync.presentation.ui.theme.NoteWhite
import com.nikola.notessync.presentation.ui.theme.NoteYellow

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var title: String,
    var content: String,
    var date: Long,
    var fontColor: Int,
    var noteColor: Int
) {
    companion object {
        val noteColors = listOf(NoteWhite, NoteRed, NoteBlue, NoteYellow, NoteGreen)
        val fontColors = listOf(FontBlack ,FontWhite, FontRed, FontBlue, FontYellow, FontGreen)
    }
}