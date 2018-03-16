package com.alex.supernotes.di.modules.db

import android.arch.persistence.room.Room
import android.content.Context
import com.alexnikola.supernotes.data.db.MyDatabase
import com.alexnikola.supernotes.di.modules.app.AppContextQualifier
import com.alexnikola.supernotes.di.qualifier.DbNameQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    @DbNameQualifier
    fun provideDbName(): String = "database_name.db"

    @Provides
    @Singleton
    fun provideDb(@AppContextQualifier appContext: Context, @DbNameQualifier dbName: String): MyDatabase {
        return Room.databaseBuilder(appContext, MyDatabase::class.java, dbName).build()
    }
}
