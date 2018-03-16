package com.alex.supernotes.di.modules.net

import com.alexnikola.supernotes.AppConstants
import com.alexnikola.supernotes.data.network.ApiManager
import com.alexnikola.supernotes.data.network.FakeApiManagerImpl
import com.alexnikola.supernotes.di.modules.okhttp.AppInterceptorsModule
import com.alexnikola.supernotes.di.modules.okhttp.NetworkInterceptorsModule
import com.alexnikola.supernotes.di.modules.okhttp.OkHttpInterceptors
import com.alexnikola.supernotes.di.modules.okhttp.OkHttpNetworkInterceptors
import com.alexnikola.supernotes.schedulers.AppSchedulerProvider
import com.alexnikola.supernotes.schedulers.SchedulerProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module(includes = [AppInterceptorsModule::class, NetworkInterceptorsModule::class])
class NetModule {

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    @Singleton
    fun provideRxAdapterFactory(): RxJava2CallAdapterFactory
            = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(@OkHttpNetworkInterceptors netInterceptors: Set<@JvmSuppressWildcards Interceptor>,
                            @OkHttpInterceptors appInterceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()

        for (interceptor in appInterceptors) {
            okHttpBuilder.addInterceptor(interceptor)
        }

        for (networkInterceptor in netInterceptors) {
            okHttpBuilder.addNetworkInterceptor(networkInterceptor)
        }

        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(converterFactory : GsonConverterFactory,
                        rxAdapter: RxJava2CallAdapterFactory,
                        okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(rxAdapter)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiManager(retrofit: Retrofit): ApiManager = retrofit.create(ApiManager::class.java)

    @Provides
    @Singleton
    fun provideApiManager(schedulerProvider: SchedulerProvider): ApiManager = FakeApiManagerImpl(schedulerProvider)
}
