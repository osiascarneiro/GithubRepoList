package com.osias.githubrepos.home.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.osias.githubrepos.home.model.RepositoryEntity

interface FetchGithubRepoListRepositoryInterface {

    companion object {
        const val PAGE_SIZE = 10
    }

    fun getRepositories(): LiveData<PagingData<RepositoryEntity>>

}