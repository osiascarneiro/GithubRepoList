package com.osias.githubrepos.home.data.api

import com.osias.githubrepos.home.model.RepositoriesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchGithubRepoList {

    @GET("repositories")
    suspend fun getRepositories(
        @Query("q") language: String = "language:kotlin",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Response<RepositoriesList>

}