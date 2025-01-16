package com.example.fitapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fitapp.domain.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Insert
    suspend fun insert(user: UserEntity)

}