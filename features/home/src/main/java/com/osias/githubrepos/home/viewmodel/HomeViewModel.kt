package com.osias.githubrepos.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.osias.githubrepos.home.data.FetchGithubRepoListRepositoryInterface

class HomeViewModel(
    private val repository: FetchGithubRepoListRepositoryInterface
): ViewModel() {

    val repos = repository.getRepositories()
                    .liveData.cachedIn(viewModelScope)

}