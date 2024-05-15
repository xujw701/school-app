package com.william.schoolapp.di

import android.content.Context
import com.google.gson.Gson
import com.william.schoolapp.data.ResInterceptor
import com.william.schoolapp.di.RetrofitInstanceNames.RETROFIT_FOR_DATA_STORE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    private val resourceId = "4b292323-9fcc-41f8-814b-3c7b19cf14b3"
    private val dataStoreBaseUrl = "https://catalogue.data.govt.nz/api/3/action/"

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ResInterceptor(resourceId))
            .build()
    }

    @Provides
    @Named(RETROFIT_FOR_DATA_STORE)
    @Singleton
    fun provideRetrofitForDataStoreApi(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(dataStoreBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}

internal object RetrofitInstanceNames {
    const val RETROFIT_FOR_DATA_STORE = "retrofitForDataStore"
}