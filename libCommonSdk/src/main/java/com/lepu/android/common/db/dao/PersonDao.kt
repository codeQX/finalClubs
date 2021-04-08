package com.lepu.android.common.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lepu.android.common.db.entity.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Query("select * from person order by id")
    fun getPersons():Flow<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPersons(person: Person)
}