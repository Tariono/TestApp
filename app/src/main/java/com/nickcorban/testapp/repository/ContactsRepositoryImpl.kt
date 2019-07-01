package com.nickcorban.testapp.repository

import androidx.lifecycle.LiveData
import com.nickcorban.testapp.data.database.ContactsDao
import com.nickcorban.testapp.data.database.entry.Entry
import com.nickcorban.testapp.data.database.output_entries.ContactDetail
import com.nickcorban.testapp.data.database.output_entries.ContactShort
import com.nickcorban.testapp.data.network.datasource.ContactsNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactsRepositoryImpl(
    private val contactsDao: ContactsDao,
    private val contactsNetworkDataSource: ContactsNetworkDataSource
) : ContactsRepository {


    init {
        contactsNetworkDataSource.apply{
            downloadedContacts.observeForever {newContacts ->
                persistFetchedContacts(newContacts)

            }
        }
    }

    override suspend fun getContacts(): LiveData<out List<ContactShort>> {
        return withContext(Dispatchers.IO){
            initContactLoad()
            return@withContext contactsDao.getContacts()
        }

    }


    override suspend fun getContactDetail(id : Int): LiveData<out ContactDetail> {
        return withContext(Dispatchers.IO){
            return@withContext contactsDao.getContactDetail(id)
        }
    }


    private suspend fun initContactLoad() {
        contactsNetworkDataSource.fetchContacts()
    }



    private fun persistFetchedContacts(fetchedContacts : List<Entry>){
        GlobalScope.launch(Dispatchers.IO){
            contactsDao.insert(fetchedContacts)
        }
    }
 }