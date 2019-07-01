package com.nickcorban.testapp.ui.contactDetail

import androidx.lifecycle.ViewModel
import com.nickcorban.testapp.repository.ContactsRepository
import com.nickcorban.testapp.util.lazyDeferred

class ContactDetailViewModel(
    private val contactsRepository: ContactsRepository,
    private val id : Int)
    : ViewModel()
    {
    val contactsDetail by lazyDeferred {
        contactsRepository.getContactDetail(id)
    }
}