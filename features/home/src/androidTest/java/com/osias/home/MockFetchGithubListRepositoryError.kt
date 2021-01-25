package com.osias.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.osias.githubrepos.home.data.FetchGithubRepoListRepositoryInterface
import com.osias.githubrepos.home.model.RepositoryAndOwner
import com.osias.mock.Mocks

class MockFetchGithubListRepositoryError: FetchGithubRepoListRepositoryInterface {

    override fun getRepositories(): Pager<Int, RepositoryAndOwner> = Pager(
            config = PagingConfig(FetchGithubRepoListRepositoryInterface.PAGE_SIZE)
    ) {
        Mocks.createErrorPager()
    }

}