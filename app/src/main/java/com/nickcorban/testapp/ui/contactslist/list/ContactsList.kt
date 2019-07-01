package com.nickcorban.testapp.ui.contactslist.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nickcorban.testapp.R
import com.nickcorban.testapp.data.database.output_entries.ContactShort
import com.nickcorban.testapp.ui.ScopedFragment
import com.nickcorban.testapp.ui.contactslist.item.ContactItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.contacts_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class ContactsList : ScopedFragment(), KodeinAware {


    private var order = true

    override val kodein by closestKodein()

    private val viewModelFactory : ContactsListViewModelFactory by instance()

    private lateinit var viewModel: ContactsListViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contacts_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ContactsListViewModel::class.java)
        bindUI()

    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val contactsList = viewModel.contacts.await()

        contactsList.observe(this@ContactsList, Observer{contacts ->
            if (contacts == null) return@Observer


            (activity as? AppCompatActivity)?.supportActionBar?.title = "Contacts"

            group_loading.visibility = View.GONE

            val sb = getView()?.findViewById<FloatingActionButton>(R.id.sb)
            sb?.setOnClickListener{view ->

                    sortList(order, contacts)
                    order = !order

            }

            initRecyclerView(contacts.toContactsList())

        })
    }

    private fun List<ContactShort>.toContactsList() : List<ContactItem> {
        return this.map{
            ContactItem(it)
        }
    }

    private fun initRecyclerView (items : List<ContactItem>) {

        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@ContactsList.context)
            adapter = groupAdapter
        }
        groupAdapter.setOnItemClickListener{item, view ->
            (item as? ContactItem)?.let {
                showContactDetail(it.contactEntry.id, view)
            }
        }
    }

    private fun showContactDetail(id : Int, view : View){
        val actionDetail = ContactsListDirections.actionContactsListToContactDetailFragment(id)
        Navigation.findNavController(view).navigate(actionDetail)
    }


    private fun sortList(asc : Boolean, contacts : List<ContactShort>) {
        if (asc == true){
            initRecyclerView(contacts.sortedBy{it.name}.toContactsList())
        }
        else{
            initRecyclerView(contacts.sortedByDescending{it.name}.toContactsList())
        }
    }

}
