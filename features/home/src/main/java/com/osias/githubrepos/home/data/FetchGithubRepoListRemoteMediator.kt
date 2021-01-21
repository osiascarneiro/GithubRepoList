package com.osias.githubrepos.home.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.osias.githubrepos.core.data.safeAPICall
import com.osias.githubrepos.core.model.RequestState
import com.osias.githubrepos.home.data.api.FetchGithubRepoList
import com.osias.githubrepos.home.data.db.GithubDb
import com.osias.githubrepos.home.model.OwnerEntity
import com.osias.githubrepos.home.model.RepositoriesList
import com.osias.githubrepos.home.model.Repository
import com.osias.githubrepos.home.model.RepositoryEntity

@ExperimentalPagingApi
class FetchGithubRepoListRemoteMediator(
    private val db: GithubDb,
    private val api: FetchGithubRepoList
): RemoteMediator<Int, RepositoryEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepositoryEntity>
    ): MediatorResult {
        val page = when(loadType) {
            LoadType.REFRESH -> null
            LoadType.APPEND -> state.lastItemOrNull()?.page?.plus(1) ?: 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
        }

        val apiResult = safeAPICall { api.getRepositories(page = page ?: 1, pageSize = FetchGithubRepoListRepositoryInterface.PAGE_SIZE) }

        return when(apiResult) {
            is RequestState.Success -> {
                processApiResult(apiResult.result, page ?: 1)
                MediatorResult.Success(endOfPaginationReached = false)
            }
            is RequestState.Failure -> {
                MediatorResult.Error(Throwable("Não foi possível carregar os dados"))
            }
            else -> { MediatorResult.Success(endOfPaginationReached = false) }
        }
    }

    private suspend fun processApiResult(result: RepositoriesList, page: Int) {
        val repositoryEntityList = buildRepositoryEntityList(result.repos, page)
        val ownerEntityList = buildOwnersEntityList(result.repos)
        db.withTransaction {
            db.repositories().deleteByPage(page)
            db.owners().deleteList(ownerEntityList)
            db.repositories().insertAll(repositoryEntityList)
            db.owners().insertAll(ownerEntityList)
        }
    }

    private suspend fun buildRepositoryEntityList(listRepos: List<Repository>, pageParam: Int): List<RepositoryEntity> {
        return listRepos.map {
            it.toEntity().apply { page = pageParam }
        }
    }

    private suspend fun buildOwnersEntityList(listRepos: List<Repository>): List<OwnerEntity> {
        return listRepos.map { it.owner.toEntity() }.distinctBy { it.login }
    }
}