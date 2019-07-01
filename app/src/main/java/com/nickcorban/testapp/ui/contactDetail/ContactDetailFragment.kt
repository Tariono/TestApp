package com.nickcorban.testapp.ui.contactDetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nickcorban.testapp.R
import com.nickcorban.testapp.ui.ScopedFragment
import kotlinx.android.synthetic.main.contact_detail_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory


class ContactDetailFragment : ScopedFragment(), KodeinAware {


    override val kodein by closestKodein()

    private val viewModelFactoryInstanceFactory : ((Int?) -> ContactDetailViewModelFactory) by factory()

    private lateinit var viewModel: ContactDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

         val safeArgs = arguments?.let{ContactDetailFragmentArgs.fromBundle(it)}
         val id = safeArgs?.id

        viewModel = ViewModelProviders.of(this,viewModelFactoryInstanceFactory(id)).
            get(ContactDetailViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main){

        val detailedContact = viewModel.contactsDetail.await()

        detailedContact.observe(this@ContactDetailFragment, Observer { contactDetails ->
            if (contactDetails == null) return@Observer


            group_loading.visibility = View.GONE

            (activity as? AppCompatActivity)?.supportActionBar?.title = contactDetails.name

            textView_username.text = contactDetails.username
            textView_phone.text = contactDetails.phone
            textView_website.text = contactDetails.website
            textView_email.text = contactDetails.email
            textView_address.text = "${contactDetails.suite}, ${contactDetails.street}," + "\n" +
                    "${contactDetails.city}, ${contactDetails.zipcode}"
            textView_company.text = "${contactDetails.companyName}" + "\n" + "${contactDetails.catchphrase}" +
                    "\n" + "${contactDetails.bs}"



        })

    }

}
