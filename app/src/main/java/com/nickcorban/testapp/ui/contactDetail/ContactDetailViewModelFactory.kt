package com.nickcorban.testapp.ui.contactDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nickcorban.testapp.repository.ContactsRepository

@Suppress("UNCHECKED_CAST")
class ContactDetailViewModelFactory(private val contactsRepository : ContactsRepository,
                                    private val id : Int)
    : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactDetailViewModel(contactsRepository,id) as T
    }
}