package com.osias.githubrepos.home.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoriesList(
    @SerializedName("total_count")
    val total: Int,
    @SerializedName("items")
    val repos: List<Repository>
): Parcelable