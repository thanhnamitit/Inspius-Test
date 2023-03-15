package com.thanhnamitit.test.di.platform

import com.thanhnamitit.test.data.MatchNotifier
import com.thanhnamitit.test.platform.MatchNotifierImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class PlatformModule {
    @Binds
    abstract fun bindMatchNotifier(input: MatchNotifierImpl): MatchNotifier
}