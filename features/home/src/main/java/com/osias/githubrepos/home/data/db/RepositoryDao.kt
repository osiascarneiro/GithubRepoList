package com.osias.githubrepos.home.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.osias.githubrepos.home.model.RepositoryEntity

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repositories: List<RepositoryEntity>)

    @Query("SELECT * FROM repository")
    fun getRepositories(): PagingSource<Int, RepositoryEntity>

    @Query("SELECT * FROM repository WHERE page = :page")
    fun getRepositoriesByPage(page: Int): PagingSource<Int, RepositoryEntity>

    @Query("SELECT * FROM repository WHERE page = :page")
    suspend fun listRepositoriesByPage(page: Int): List<RepositoryEntity>

    @Query("DELETE FROM repository WHERE page = :page")
    suspend fun deleteByPage(page: Int)

    @Query("SELECT MAX(page)+1 FROM repository")
    suspend fun getNextPageInDatabase(): Int?

}