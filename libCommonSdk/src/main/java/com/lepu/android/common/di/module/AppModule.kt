package com.lepu.android.common.di.module

import android.content.Context
import com.lepu.android.common.db.AppDatabase
import com.lepu.android.common.db.dao.PersonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context):AppDatabase{
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun providePersonDao(appDatabase: AppDatabase):PersonDao{
        return appDatabase.personDao()
    }

}