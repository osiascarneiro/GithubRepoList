package com.osias.home.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import cards.core.test.util.getOrAwaitValue
import com.osias.githubrepos.home.data.FetchGithubRepoListRepositoryInterface
import com.osias.githubrepos.home.model.RepositoryAndOwner
import com.osias.githubrepos.home.viewmodel.HomeViewModel
import com.osias.githubrepos.testCore.base.BaseTest
import com.osias.githubrepos.testCore.rule.MainCoroutineRule
import com.osias.githubrepos.testCore.util.collectData
import com.osias.home.mock.Mocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTests: BaseTest() {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var sut: HomeViewModel
    private val repository = mockk<FetchGithubRepoListRepositoryInterface>()
    val pager: PagingSource<Int, RepositoryAndOwner> = mockk()

    @Before
    fun setUp() {
        every { repository.getRepositories() } returns Pager(PagingConfig(FetchGithubRepoListRepositoryInterface.PAGE_SIZE)) { pager }
        val callback: () -> Unit = {}
        every { pager.registerInvalidatedCallback(any()) } returns callback()
        every { pager.invalid } returns false
        every { pager.keyReuseSupported } returns true
        sut = HomeViewModel(repository)
    }

    @Test
    fun `Should return success`() {
        //Given
        coEvery { pager.load(any()) } returns PagingSource.LoadResult.Page(data = Mocks.createRepositores(), prevKey = null, nextKey = null)
        //When
        val value = sut.repos.getOrAwaitValue()
        //Then
        //How assert PagingData without adapter?
        val testes = runBlocking { value.collectData() }
        assert(testes.size == 11)
    }

}