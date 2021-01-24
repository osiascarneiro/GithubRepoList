package com.osias.home.data

import androidx.paging.PagingData
import androidx.paging.liveData
import androidx.paging.map
import cards.core.test.util.getOrAwaitValue
import com.osias.githubrepos.home.data.FetchGithubRepoListRepository
import com.osias.githubrepos.home.data.api.FetchGithubRepoList
import com.osias.githubrepos.home.data.db.GithubDb
import com.osias.githubrepos.home.utils.GlideImageLoader
import com.osias.githubrepos.home.view.adapter.HomeRepositoryAdapter
import com.osias.githubrepos.testCore.base.BaseTest
import com.osias.githubrepos.testCore.rule.MainCoroutineRule
import com.osias.home.mock.Mocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.Executors

class FetchGithubRepoListRepositoryTest: BaseTest() {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var sut: FetchGithubRepoListRepository
    private val service = mockk<FetchGithubRepoList>()
    private val db = mockk<GithubDb>()

    @Before
    fun setup() {
        sut = FetchGithubRepoListRepository(db, service)
    }

    @Test
    fun `Should return success`() {
        //Given
        val itens = Mocks.createRepositores()
        val successPager = Mocks.creteSuccessPager(itens)
        coEvery { db.repositories().getRepositoriesAndOwners() } returns successPager
        //When
        val repos = sut.getRepositories()
        //Then
        //How assert PagingData without adapter?
        val data = PagingData.from(itens)
        val value = repos.liveData.getOrAwaitValue()
        assert(data == value)

    }

}