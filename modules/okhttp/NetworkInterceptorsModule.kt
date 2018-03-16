package com.alex.supernotes.di.modules.okhttp

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
class NetworkInterceptorsModule {

    @IntoSet
    @Provides
    @Singleton
    @OkHttpNetworkInterceptors
    fun provideOkHttpNetworkInterceptors(): Interceptor {
        return StethoInterceptor()
    }
}
