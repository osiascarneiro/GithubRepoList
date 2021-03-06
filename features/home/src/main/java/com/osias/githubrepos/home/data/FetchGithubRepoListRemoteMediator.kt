package com.osias.githubrepos.home.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.osias.githubrepos.core.data.safeAPICall
import com.osias.githubrepos.core.model.RequestState
import com.osias.githubrepos.home.data.api.FetchGithubRepoList
import com.osias.githubrepos.home.data.db.GithubDb
import com.osias.githubrepos.home.model.*

@ExperimentalPagingApi
class FetchGithubRepoListRemoteMediator(
    private val db: GithubDb,
    private val api: FetchGithubRepoList
): RemoteMediator<Int, RepositoryAndOwner>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepositoryAndOwner>
    ): MediatorResult {
        val page = when(loadType) {
            LoadType.REFRESH -> null
            LoadType.APPEND -> state.lastItemOrNull()?.repository?.page?.plus(1) ?: 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
        } ?: return MediatorResult.Success(endOfPaginationReached = false)

        val apiResult = safeAPICall { api.getRepositories(page = page, pageSize = FetchGithubRepoListRepositoryInterface.PAGE_SIZE) }

        return when(apiResult) {
            is RequestState.Success -> {
                processApiResult(apiResult.result, page)
                MediatorResult.Success(endOfPaginationReached = false)
            }
            is RequestState.Failure -> { MediatorResult.Error(Throwable("Não foi possível carregar os dados")) }
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

    private fun buildRepositoryEntityList(listRepos: List<Repository>, pageParam: Int): List<RepositoryEntity> {
        return listRepos.map {
            it.toEntity().apply { page = pageParam }
        }
    }

    private fun buildOwnersEntityList(listRepos: List<Repository>): List<OwnerEntity> {
        return listRepos.map { it.owner.toEntity() }.distinctBy { it.login }
    }
}