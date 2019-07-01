package com.nickcorban.testapp.data.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nickcorban.testapp.data.database.entry.Entry
import com.nickcorban.testapp.data.network.UserAPI
import com.nickcorban.testapp.util.NoConnectivityException

class ContactsNetworkDataSourceImpl(
    private val userAPI: UserAPI
) : ContactsNetworkDataSource {

    private val _downloadedContacts = MutableLiveData<List<Entry>>()

    override val downloadedContacts: LiveData<List<Entry>>
        get() = _downloadedContacts

    override suspend fun fetchContacts() {
        try{
            val fetchedContacts = userAPI
                .getContacts()
                .await()
            _downloadedContacts.postValue(fetchedContacts)
        } catch (e : NoConnectivityException)
        { Log.e("Connection", "No internet connection", e) }
    }
}