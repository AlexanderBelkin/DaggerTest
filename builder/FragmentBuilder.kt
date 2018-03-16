package com.alex.supernotes.di.builder

import com.alexnikola.supernotes.di.modules.ui.NotesListFragmentModule
import com.alexnikola.supernotes.ui.notes_list.NoteListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [NotesListFragmentModule::class])
    internal abstract fun provideNoteListFragmentFactory(): NoteListFragment
}
