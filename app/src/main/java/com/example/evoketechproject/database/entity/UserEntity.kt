package com.example.evoketechproject.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "USER")
data class UserEntity(
    @NotNull
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null
)