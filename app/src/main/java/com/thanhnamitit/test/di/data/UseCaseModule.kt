package com.thanhnamitit.test.di.data

import com.thanhnamitit.test.data.usecase.*
import com.thanhnamitit.test.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    @Binds
    abstract fun bindGetParticipatingTeam(input: GetParticipatingTeamsImpl): GetParticipatingTeamsUseCase

    @Binds
    abstract fun bindGetMatchesOfTeam(input: GetMatchesOfTeamImpl): GetMatchesOfTeamUseCase

    @Binds
    abstract fun bindGetAllMatches(input: GetAllMatchesImpl): GetAllMatchesUseCase

    @Binds
    abstract fun bindGetNotifyMatchFlow(input: GetNotifyMatchFlowImpl): GetNotifyMatchFlowUseCase

    @Binds
    abstract fun bindGetNotifyMatch(input: GetNotifyMatchImpl): GetNotifyMatchUseCase

    @Binds
    abstract fun bindRequestNotifyMatch(input: RequestNotifyMatchImpl): RequestNotifyMatchUseCase

    @Binds
    abstract fun bindCancelNotifyMatch(input: CancelNotifyMatchImpl): CancelNotifyMatchUseCase
}