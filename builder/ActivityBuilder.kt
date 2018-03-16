package com.alex.supernotes.di.builder

import com.alexnikola.supernotes.di.modules.ui.MainActivityModule
import com.alexnikola.supernotes.ui.main.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentBuilder::class, MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}