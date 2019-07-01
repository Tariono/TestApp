package com.nickcorban.testapp.ui.contactslist.item

import com.nickcorban.testapp.R
import com.nickcorban.testapp.data.database.output_entries.ContactShort
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.contact_item_fragment.*

class ContactItem (

    val contactEntry : ContactShort

) : Item() {


    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply{
            textView_website.text = contactEntry.companyName
            textView_name.text = contactEntry.name
            textView_email.text = contactEntry.email
        }
    }

    override fun getLayout() = R.layout.contact_item_fragment
}