package com.osias.githubrepos.home.data.db

import androidx.room.*
import com.osias.githubrepos.home.model.OwnerEntity

@Dao
interface OwnerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(owner: OwnerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(owners: List<OwnerEntity>)

    @Query("SELECT * FROM owner WHERE login = :login")
    suspend fun findByLogin(login: String): OwnerEntity

    @Query("DELETE FROM owner WHERE login = :login")
    suspend fun deleteByLogin(login: String)

    @Delete
    suspend fun deleteList(owners: List<OwnerEntity>)

}