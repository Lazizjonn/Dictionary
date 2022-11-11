package uz.gita.dictionary_slp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dictionary_slp.domain.AppRepository
import uz.gita.dictionary_slp.domain.impl.AppRepositoryImpl
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface RepositoryModule {

    @[Singleton Binds]
    fun getAppRepository(impl: AppRepositoryImpl): AppRepository
}