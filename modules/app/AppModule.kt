package com.alex.supernotes.di.modules.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.alexnikola.supernotes.AppConstants
import com.alexnikola.supernotes.data.NotesRepository
import com.alexnikola.supernotes.data.NotesRepositoryImpl
import com.alexnikola.supernotes.data.prefs.PrefsManager
import com.alexnikola.supernotes.data.prefs.PrefsManagerImpl
import com.alexnikola.supernotes.di.modules.db.DataBaseModule
import com.alexnikola.supernotes.di.modules.net.NetModule
import com.alexnikola.supernotes.di.modules.okhttp.AppInterceptorsModule
import com.alexnikola.supernotes.di.modules.okhttp.NetworkInterceptorsModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataBaseModule::class, NetModule::class])
class AppModule {

    @Provides
    @Singleton
    @AppContextQualifier
    fun provideApplicationContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideSharedPrefs(@AppContextQualifier appContext: Context) : SharedPreferences {
        return appContext.getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePrefsManager(prefsManager: PrefsManagerImpl): PrefsManager = prefsManager

    @Provides
    @Singleton
    fun provideDataManager(repository: NotesRepositoryImpl): NotesRepository = repository
}