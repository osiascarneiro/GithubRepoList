package com.osias.githubrepos.home.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.osias.githubrepos.home.model.OwnerEntity
import com.osias.githubrepos.home.model.RepositoryEntity

@Database(entities = [RepositoryEntity::class,OwnerEntity::class],
          version = 1,
          exportSchema = false)
abstract class GithubDb:RoomDatabase() {

    companion object {
        fun create(context: Context): GithubDb {
            val builder = Room.databaseBuilder(context, GithubDb::class.java, "github.db")
            return builder.fallbackToDestructiveMigration().build()
        }
    }

    abstract fun repositories(): RepositoryDao
    abstract fun owners(): OwnerDao

}