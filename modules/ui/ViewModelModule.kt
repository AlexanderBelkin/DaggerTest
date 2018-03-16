package com.alex.supernotes.di.modules.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.alexnikola.supernotes.di.ViewModelFactory

import com.alexnikola.supernotes.di.ViewModelKey
import com.alexnikola.supernotes.ui.main.MainViewModel
import com.alexnikola.supernotes.ui.notes_list.NotesListViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindUserViewModel(userViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotesListViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: NotesListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}