package com.osias.githubrepos.home.model

import androidx.room.Embedded
import androidx.room.Relation

data class RepositoryAndOwner(
    @Embedded val repository: RepositoryEntity,
    @Relation(parentColumn = "ownerLogin", entityColumn = "login")
    val owner: OwnerEntity
)