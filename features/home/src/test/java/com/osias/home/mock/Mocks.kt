package com.osias.home.mock

import androidx.paging.PagingSource
import com.osias.githubrepos.home.model.OwnerEntity
import com.osias.githubrepos.home.model.RepositoryAndOwner
import com.osias.githubrepos.home.model.RepositoryEntity

object Mocks {

    fun creteSuccessPager(itens: List<RepositoryAndOwner>): PagingSource<Int, RepositoryAndOwner> {
        return MockSuccessPagingSource(itens)
    }

    fun createErrorPager(): PagingSource<Int, RepositoryAndOwner> {
        return MockErrorPagingSource()
    }

    fun createRepositores(): List<RepositoryAndOwner> {
        val itens = arrayListOf<RepositoryAndOwner>()
        for(i in 0..8) {
            itens.add(
                RepositoryAndOwner(
                    repository = RepositoryEntity(
                        id = "$i",
                        name = "$i",
                        fullName = "$i, $i",
                        isPrivate = false,
                        ownerLogin = "",
                        description = "",
                        url = "repository.com",
                        starCount = 0,
                        forksCount = 0,
                        page = 1
                    ),
                    owner = OwnerEntity(
                        login = "owner_login",
                        avatarUrl = "owner.com",
                        type = "personal"
                    )
                )
            )
        }
        return itens
    }

    private class MockSuccessPagingSource(val itens: List<RepositoryAndOwner>): PagingSource<Int, RepositoryAndOwner>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryAndOwner> {
            return LoadResult.Page(
                data = itens,
                prevKey = null,
                nextKey = null
            )
        }
    }

    private class MockErrorPagingSource(): PagingSource<Int, RepositoryAndOwner>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryAndOwner> {
            return LoadResult.Error<Int, RepositoryAndOwner>(
                throwable = Throwable("There was an error")
            )
        }

    }

}