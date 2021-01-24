package com.osias.di

import com.osias.githubrepos.core.utils.ImageLoader
import com.osias.githubrepos.home.data.FetchGithubRepoListRepositoryInterface
import com.osias.githubrepos.home.utils.GlideImageLoader
import com.osias.githubrepos.home.view.adapter.HomeRepositoryAdapter
import com.osias.githubrepos.home.viewmodel.HomeViewModel
import com.osias.home.MockFetchGithubListRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mockModule = module {
    viewModel { HomeViewModel(get()) }
    factory<FetchGithubRepoListRepositoryInterface> { MockFetchGithubListRepository() }
    factory { HomeRepositoryAdapter(get()) }
    factory<ImageLoader> { GlideImageLoader() }
}