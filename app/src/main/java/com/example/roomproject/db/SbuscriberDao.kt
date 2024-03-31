package com.example.roomproject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SbuscriberDao {

    @Insert
    suspend fun insertSubscriberData(subscriber:Subscriber):Long

    @Update
    suspend fun updateSubscriberData(subscriber:Subscriber)

    @Delete
    suspend fun deleteSubscriberData(subscriber:Subscriber)

    @Query(value = "DELETE FROM subscriber_data_table")
    suspend fun deleteAll()

    @Query(value = "SELECT * FROM subscriber_data_table")
    fun getAllSubscriber():LiveData<List<Subscriber>>



}