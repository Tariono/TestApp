package com.nickcorban.testapp.ui.contactslist.list

import androidx.lifecycle.ViewModel
import com.nickcorban.testapp.repository.ContactsRepository
import com.nickcorban.testapp.util.lazyDeferred

class ContactsListViewModel (private val contactsRepository: ContactsRepository) : ViewModel() {

    val contacts by lazyDeferred{
        contactsRepository.getContacts()
    }
}