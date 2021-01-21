package com.osias.githubrepos.home.viewmodel

import androidx.lifecycle.ViewModel
import com.osias.githubrepos.home.data.FetchGithubRepoListRepositoryInterface

class HomeViewModel(
    private val repository: FetchGithubRepoListRepositoryInterface
): ViewModel() {

    val repos = repository.getRepositories()

}