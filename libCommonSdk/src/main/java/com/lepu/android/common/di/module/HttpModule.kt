package com.lepu.android.common.di.module

import com.lepu.android.common.http.api.ApiDemo
import com.lepu.android.common.http.api.ApiIXZ
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HttpModule {

    @Singleton
    @Provides
    fun provideApiIXZ(): ApiIXZ {
        return ApiIXZ.create()
    }

    @Singleton
    @Provides
    fun provideApiDemo(): ApiDemo {
        return ApiDemo.create()
    }


}