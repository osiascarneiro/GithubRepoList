package com.osias.home.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import cards.core.test.util.getOrAwaitValue
import com.osias.githubrepos.home.data.FetchGithubRepoListRepositoryInterface
import com.osias.githubrepos.home.viewmodel.HomeViewModel
import com.osias.githubrepos.testCore.base.BaseTest
import com.osias.home.mock.Mocks
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class HomeViewModelTests: BaseTest() {

    lateinit var sut: HomeViewModel
    private val repository = mockk<FetchGithubRepoListRepositoryInterface>()

    @Before
    fun setUp() {
        sut = HomeViewModel(repository)
    }

    @Test
    fun `Should return success`() {
        //Given
        val itens = Mocks.createRepositores()
        val successPager = Mocks.creteSuccessPager(itens)
        every { repository.getRepositories() } returns  Pager(
            config = PagingConfig(FetchGithubRepoListRepositoryInterface.PAGE_SIZE)
        ) {
            successPager
        }
        //When
        val value = sut.repos.getOrAwaitValue()
        //Then
        //How assert PagingData without adapter?
        value.map {  }
    }

}