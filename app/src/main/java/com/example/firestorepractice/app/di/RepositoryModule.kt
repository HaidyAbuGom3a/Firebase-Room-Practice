package com.example.firestorepractice.app.di

import com.example.firestorepractice.data.repository.AuthRepositoryImp
import com.example.firestorepractice.data.repository.RepositoryImp
import com.example.firestorepractice.data.source.remote.FirebaseDataSourceImp
import com.example.firestorepractice.domain.repository.AuthRepository
import com.example.firestorepractice.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {
    @Singleton
    @Provides
    fun bindAuthGoogleRepository(firebaseDataSource: FirebaseDataSourceImp): AuthRepository{
        return AuthRepositoryImp(firebaseDataSource)
    }

    @Singleton
    @Provides
    fun bindRepository(firebaseDataSource: FirebaseDataSourceImp): Repository{
        return RepositoryImp(firebaseDataSource)
    }
}