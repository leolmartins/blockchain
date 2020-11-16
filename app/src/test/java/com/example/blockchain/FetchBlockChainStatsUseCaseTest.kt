package com.example.blockchain

import com.example.blockchain.shared.model.BlockChainStats
import com.example.blockchain.shared.model.BlockChainStatsResponse
import com.example.blockchain.shared.model.SharedPreferencesKeys
import com.example.blockchain.shared.services.StatsService
import com.example.blockchain.shared.sessionmanager.SessionManager
import com.example.blockchain.shared.usecases.FetchBlockChainStatsUseCase
import com.example.blockchain.shared.usecases.FetchBlockChainStatsUseCaseImpl
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

/**
 * @author Leonardo Martins on 16/11/20
 */
class FetchBlockChainStatsUseCaseTest {

    private val sessionManager = mockk<SessionManager>()
    private val statsService = mockk<StatsService>()

    private lateinit var useCase: FetchBlockChainStatsUseCase

    @Before
    fun setupTest() {
        useCase = FetchBlockChainStatsUseCaseImpl(sessionManager, statsService)
    }

    @Test
    fun `should return block chain stats when successfully fetching block chain stats`() {
        val response = BlockChainStatsResponse(500.0)
        val stats = BlockChainStats(500.0)

        every { statsService.fetchBlockChainStats() } returns Single.just(response)
        every { sessionManager.save(any(), any<BlockChainStats>()) } just runs

        useCase.fetchBlockChainStats().test().assertResult(stats)

        verify(exactly = 1) {
            statsService.fetchBlockChainStats()
            sessionManager.save(SharedPreferencesKeys.STATS_KEY, stats)
        }
        confirmVerified(sessionManager, statsService)
    }
}
