package com.example.fitapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fitapp.domain.entities.UserProfileEntity

@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profile WHERE email = :email")
    suspend fun getProfile(email: String): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(profile: UserProfileEntity)

    @Update
    suspend fun update(profile: UserProfileEntity)
}
