package com.nickcorban.testapp.ui.contactslist.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nickcorban.testapp.repository.ContactsRepository

@Suppress("UNCHECKED_CAST")
class ContactsListViewModelFactory(private val contactsRepository : ContactsRepository)
    : ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ContactsListViewModel(contactsRepository) as T
        }

}