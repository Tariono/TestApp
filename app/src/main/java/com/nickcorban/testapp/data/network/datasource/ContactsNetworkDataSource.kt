package com.nickcorban.testapp.data.network.datasource

import androidx.lifecycle.LiveData
import com.nickcorban.testapp.data.database.entry.Entry

interface ContactsNetworkDataSource {

    val downloadedContacts : LiveData<List<Entry>>

    suspend fun fetchContacts ()
}