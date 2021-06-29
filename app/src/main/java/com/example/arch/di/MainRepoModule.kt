package com.example.arch.di

import com.example.arch.repo.FakeMainRepo
import com.example.arch.repo.MainRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModule {

    @Binds
    abstract fun bindMainRepo(fakeMainRepo: FakeMainRepo): MainRepo

//    @Provides
//    fun provideMainRepo(): MainRepo{ return FakeMainRepo() }

}
