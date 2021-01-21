package com.osias.githubrepos.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "owner")
data class OwnerEntity(
    @PrimaryKey
    var login: String,
    var avatarUrl: String,
    var type: String
)