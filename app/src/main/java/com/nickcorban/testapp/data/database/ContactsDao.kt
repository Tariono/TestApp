package com.nickcorban.testapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nickcorban.testapp.data.database.entry.Entry
import com.nickcorban.testapp.data.database.output_entries.ContactShort
import com.nickcorban.testapp.data.database.output_entries.ContactDetail

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contactListEntry:List<Entry>)

    @Query("select * from contactsTable")
    fun getContacts() : LiveData<List<ContactShort>>

    @Query("select * from contactsTable where id = (:id)")
    fun getContactDetail(id : Int) : LiveData<ContactDetail>


}