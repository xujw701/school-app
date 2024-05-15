package com.william.schoolapp.di

import com.william.schoolapp.data.api.DataStoreApi
import com.william.schoolapp.data.repository.SchoolRepository
import com.william.schoolapp.data.repository.SchoolRepositoryImpl
import com.william.schoolapp.di.RetrofitInstanceNames.RETROFIT_FOR_DATA_STORE
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SchoolModule {

    @Binds
    abstract fun bindSchoolRepository(repository: SchoolRepositoryImpl): SchoolRepository

    companion object {

        @Provides
        fun provideAccountApi(@Named(RETROFIT_FOR_DATA_STORE) retrofit: Retrofit): DataStoreApi {
            return retrofit.create(DataStoreApi::class.java)
        }
    }
}