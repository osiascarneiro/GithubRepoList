package com.osias.home.data

import androidx.paging.PagingSource
import androidx.paging.liveData
import androidx.room.withTransaction
import cards.core.test.util.getOrAwaitValue
import com.osias.githubrepos.home.data.FetchGithubRepoListRepository
import com.osias.githubrepos.home.data.FetchGithubRepoListRepositoryInterface
import com.osias.githubrepos.home.data.api.FetchGithubRepoList
import com.osias.githubrepos.home.data.db.GithubDb
import com.osias.githubrepos.home.model.RepositoryAndOwner
import com.osias.githubrepos.testCore.base.BaseTest
import com.osias.githubrepos.testCore.rule.MainCoroutineRule
import com.osias.githubrepos.testCore.util.collectData
import com.osias.home.R
import com.osias.home.mock.Mocks
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FetchGithubRepoListRepositoryTest: BaseTest() {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var sut: FetchGithubRepoListRepository
    private val service = mockk<FetchGithubRepoList>()
    private val db = mockk<GithubDb>()
    private val callback: () -> Unit = {}
    private val transactionLambda = slot<suspend () -> R>()
    val pager: PagingSource<Int, RepositoryAndOwner> = mockk()

    @Before
    fun setup() {
        sut = FetchGithubRepoListRepository(db, service)
        mockkStatic("androidx.room.RoomDatabaseKt")
        coEvery {
            db.withTransaction(capture(transactionLambda))
        } coAnswers { transactionLambda.captured.invoke() }

        every { pager.registerInvalidatedCallback(any()) } returns callback()
        every { pager.invalid } returns false
        every { pager.keyReuseSupported } returns true

        coEvery { db.repositories().deleteByPage(any()) } returns callback()
        coEvery { db.owners().deleteList(any()) } returns callback()
        coEvery { db.repositories().insertAll(any()) } returns callback()
        coEvery { db.owners().insertAll(any()) } returns callback()
        every { db.repositories().getRepositoriesAndOwners() } returns pager
    }

    @Test
    fun `Should return success`() {
        //Given
        coEvery { pager.load(any()) } returns PagingSource.LoadResult.Page(data = Mocks.createRepositores(), prevKey = null, nextKey = null)
        coEvery { service.getRepositories(page = any(), pageSize = FetchGithubRepoListRepositoryInterface.PAGE_SIZE) } returns Mocks.createApiRepositoriesSuccess()
        //When
        val repos = sut.getRepositories()
        //Then
        val value = repos.liveData.getOrAwaitValue()
        val testes = runBlocking { value.collectData() }
        assert(testes.size == 11)
    }

    @Test
    fun `Should return nothing on error`() {
        //Given
        coEvery { pager.load(any()) } returns PagingSource.LoadResult.Error(Throwable("There was an error"))
        coEvery { service.getRepositories(page = any(), pageSize = FetchGithubRepoListRepositoryInterface.PAGE_SIZE) } returns Mocks.createApiRepositoriesFailure()
        //When
        val repos = sut.getRepositories()
        //Then
        val value = repos.liveData.getOrAwaitValue()
        val testes = runBlocking { value.collectData() }
        assert(testes.isEmpty())
    }

}