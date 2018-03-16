package com.alex.supernotes.di.modules.okhttp

import com.alexnikola.supernotes.data.network.NetworkCheckInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class AppInterceptorsModule {

    @IntoSet
    @Provides
    @Singleton
    @OkHttpInterceptors
    fun provideLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor
    }

    @IntoSet
    @Provides
    @Singleton
    @OkHttpInterceptors
    fun provideNetworkCheckInterceptor(interceptor: NetworkCheckInterceptor): Interceptor {
        return interceptor
    }
}
