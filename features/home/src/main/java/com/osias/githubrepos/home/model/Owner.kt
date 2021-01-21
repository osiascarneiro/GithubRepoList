package com.osias.githubrepos.home.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("type")
    val type: String
): Parcelable {

    fun toEntity(): OwnerEntity {
        return OwnerEntity(
            login = this.login,
            avatarUrl = this.avatarUrl,
            type = this.type
        )
    }

}