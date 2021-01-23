package com.osias.githubrepos.home.di

import com.osias.githubrepos.core.di.RetrofitBuilder
import com.osias.githubrepos.core.utils.ImageLoader
import com.osias.githubrepos.home.data.FetchGithubRepoListRepository
import com.osias.githubrepos.home.data.FetchGithubRepoListRepositoryInterface
import com.osias.githubrepos.home.data.api.FetchGithubRepoList
import com.osias.githubrepos.home.data.db.GithubDb
import com.osias.githubrepos.home.utils.GlideImageLoader
import com.osias.githubrepos.home.view.adapter.HomeRepositoryAdapter
import com.osias.githubrepos.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    single { RetrofitBuilder.buildRetrofitService(FetchGithubRepoList::class.java) }
    single { GithubDb.create(get()) }
    factory<ImageLoader> { GlideImageLoader() }
    factory { HomeRepositoryAdapter(get()) }
    factory<FetchGithubRepoListRepositoryInterface> { FetchGithubRepoListRepository(get(), get()) }
    viewModel { HomeViewModel(get()) }
}