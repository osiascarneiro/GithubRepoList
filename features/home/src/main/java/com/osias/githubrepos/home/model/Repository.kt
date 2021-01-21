package com.osias.githubrepos.home.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("private")
    val private: Boolean,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("description")
    val description: String?,
    @SerializedName("git_url")
    val url: String,
    @SerializedName("stargazers_count")
    val starCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int
): Parcelable {

    fun toEntity(): RepositoryEntity {
        return RepositoryEntity(
            id = this.id,
            name = this.name,
            fullName = this.fullName,
            isPrivate = this.private,
            ownerLogin = this.owner.login,
            description = this.description,
            url = this.url,
            starCount = this.starCount,
            forksCount = this.forksCount,
            page = 0
        )
    }

}