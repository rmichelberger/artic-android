package com.example.artic.di

import com.example.artic.art.UseCase
import com.example.artic.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun providesUseCase(repository: Repository): UseCase = UseCase(repository)
}