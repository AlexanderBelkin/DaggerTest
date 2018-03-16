package com.alex.supernotes.di.component

import android.app.Application
import com.alexnikola.supernotes.MyApplication
import com.alexnikola.supernotes.di.builder.ActivityBuilder
import com.alexnikola.supernotes.di.modules.app.AppModule
import com.alexnikola.supernotes.di.modules.ui.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    ViewModelModule::class])
interface AppComponent: AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(instance: MyApplication)
}