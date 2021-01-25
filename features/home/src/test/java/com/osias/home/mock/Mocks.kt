package com.osias.home.mock

import com.osias.githubrepos.home.model.*
import okhttp3.ResponseBody
import retrofit2.Response

object Mocks {

    fun createRepositores(): ArrayList<RepositoryAndOwner> {
        val itens = arrayListOf<RepositoryAndOwner>()
        for(i in 0..10) {
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

    fun createApiRepositoriesSuccess(): Response<RepositoriesList> {
        val itens = arrayListOf<Repository>()
        for(i in 0..8) {
            itens.add(
                Repository(
                    id = "$i",
                    name = "$i",
                    fullName = "$i, $i",
                    private = false,
                    owner = Owner(
                        login = "owner_login",
                        avatarUrl = "owner.com",
                        type = "personal"
                    ),
                    description = "",
                    url = "repository.com",
                    starCount = 0,
                    forksCount = 0
                )
            )
        }
        val list = RepositoriesList(
            total = 9,
            repos = itens
        )
        return Response.success(list)
    }

    fun createApiRepositoriesFailure(): Response<RepositoriesList> {
        return Response.error(404, ResponseBody.create(null, "Não foi possível acessar o recurso"))
    }

}