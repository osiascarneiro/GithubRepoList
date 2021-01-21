package com.osias.githubrepos.home.data

import androidx.paging.Pager
import com.osias.githubrepos.home.model.RepositoryEntity

interface FetchGithubRepoListRepositoryInterface {

    companion object {
        const val PAGE_SIZE = 10
    }

    fun getRepositories(): Pager<Int, RepositoryEntity>

}