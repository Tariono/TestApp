package com.nickcorban.testapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nickcorban.testapp.data.database.entry.Entry

@Database(
    entities = [Entry::class],
    version = 1
)

abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao() : ContactsDao

    companion object {
        @Volatile private var instance : ContactsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {instance = it}
        }

        private fun buildDatabase (context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ContactsDatabase::class.java,
                "contacts.db")
                .build()
    }
}