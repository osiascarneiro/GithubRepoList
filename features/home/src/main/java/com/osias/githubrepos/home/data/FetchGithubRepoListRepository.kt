package com.osias.githubrepos.home.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.osias.githubrepos.home.data.api.FetchGithubRepoList
import com.osias.githubrepos.home.data.db.GithubDb
import com.osias.githubrepos.home.model.RepositoryEntity

class FetchGithubRepoListRepository(
    private val db: GithubDb,
    private val api: FetchGithubRepoList
): FetchGithubRepoListRepositoryInterface {

    @OptIn(ExperimentalPagingApi::class)
    override fun getRepositories(): LiveData<PagingData<RepositoryEntity>> = Pager(
        config = PagingConfig(FetchGithubRepoListRepositoryInterface.PAGE_SIZE),
        remoteMediator = FetchGithubRepoListRemoteMediator(db, api)
    ) {
        db.repositories().getRepositories()
    }.liveData

}