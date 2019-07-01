package com.nickcorban.testapp.repository

import androidx.lifecycle.LiveData
import com.nickcorban.testapp.data.database.output_entries.ContactDetail
import com.nickcorban.testapp.data.database.output_entries.ContactShort

interface ContactsRepository {

    suspend fun getContacts() : LiveData<out List<ContactShort>>


    suspend fun getContactDetail(id : Int) : LiveData<out ContactDetail>

}