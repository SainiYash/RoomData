package com.example.roomproject.db

class SubscriberRepository(private val dao:SbuscriberDao)
{
    val subscribers = dao.getAllSubscriber()

    suspend fun insert(subscriber: Subscriber){

        dao.insertSubscriberData(subscriber)
    }

    suspend fun update(subscriber: Subscriber)
    {
        dao.updateSubscriberData(subscriber)
    }

    suspend fun delete(subscriber: Subscriber)
    {
        dao.deleteSubscriberData(subscriber)
    }

    suspend fun clearAll()
    {
        dao.deleteAll()
    }




}