package com.example.android.networkconnect.di


import com.example.android.networkconnect.characterlist.CharacterRepository
import com.example.android.networkconnect.characterlist.CharacterTasks
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CharacterTaskModule {

    @Binds
    abstract fun bindCharacterTasks(
        characterRepository: CharacterRepository
    ) : CharacterTasks
}