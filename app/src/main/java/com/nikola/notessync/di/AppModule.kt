package com.nikola.notessync.di

import android.app.Application
import androidx.room.Room
import com.nikola.notessync.data.local_data.NoteDatabase
import com.nikola.notessync.data.local_data.NoteDatabase.Companion.NOTES_DB
import com.nikola.notessync.data.repository.NoteRepositoryImpl
import com.nikola.notessync.domain.repository.NoteRepository
import com.nikola.notessync.domain.use_case.AddNoteUseCase
import com.nikola.notessync.domain.use_case.DeleteNoteUseCase
import com.nikola.notessync.domain.use_case.GetNotesUseCase
import com.nikola.notessync.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NOTES_DB
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            GetNotesUseCase(repository),
            DeleteNoteUseCase(repository),
            AddNoteUseCase(repository)
        )
    }

}