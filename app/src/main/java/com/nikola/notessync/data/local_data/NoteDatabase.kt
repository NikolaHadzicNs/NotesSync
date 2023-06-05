package com.nikola.notessync.data.local_data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikola.notessync.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val NOTES_DB = "notes_db"
    }
}