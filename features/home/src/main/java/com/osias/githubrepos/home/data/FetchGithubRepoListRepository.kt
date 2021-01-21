package com.osias.githubrepos.home.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.osias.githubrepos.home.data.api.FetchGithubRepoList
import com.osias.githubrepos.home.data.db.GithubDb
import com.osias.githubrepos.home.model.RepositoryEntity

class FetchGithubRepoListRepository(
    private val db: GithubDb,
    private val api: FetchGithubRepoList
): FetchGithubRepoListRepositoryInterface {

    @OptIn(ExperimentalPagingApi::class)
    override fun getRepositories(): Pager<Int, RepositoryEntity> = Pager(
        config = PagingConfig(FetchGithubRepoListRepositoryInterface.PAGE_SIZE),
        remoteMediator = FetchGithubRepoListRemoteMediator(db, api)
    ) {
        db.repositories().getRepositories()
    }

}