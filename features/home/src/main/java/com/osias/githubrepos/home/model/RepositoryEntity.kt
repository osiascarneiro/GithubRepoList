package com.osias.githubrepos.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class RepositoryEntity(
    @PrimaryKey
    var id: String,
    var name: String,
    var fullName: String,
    var isPrivate: Boolean,
    var ownerLogin: String,
    var description: String?,
    var url: String,
    var starCount: Int,
    var forksCount: Int,
    var page: Int
)