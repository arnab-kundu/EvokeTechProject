package com.example.evoketechproject.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.evoketechproject.database.entity.UserEntity

@Dao
interface UserDao {

    /**
     * Insert or update data into database
     */
    @Insert(entity = UserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(userEntity: UserEntity): Long

    /**
     * Get livedata of user list
     * @return      LiveData<List<UserEntity>>
     */
    @Query("SELECT * FROM USER")
    fun getAllUser(): LiveData<List<UserEntity>>

}