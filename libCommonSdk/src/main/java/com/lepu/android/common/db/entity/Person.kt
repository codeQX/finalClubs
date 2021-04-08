package com.lepu.android.common.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey @ColumnInfo(name = "id") val personId: Long,
    val name: String,
    val age: Int
)
