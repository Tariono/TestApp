package com.nickcorban.testapp.util

import android.app.Application
import com.nickcorban.testapp.data.database.ContactsDatabase
import com.nickcorban.testapp.data.network.UserAPI
import com.nickcorban.testapp.data.network.datasource.ContactsNetworkDataSource
import com.nickcorban.testapp.data.network.datasource.ContactsNetworkDataSourceImpl
import com.nickcorban.testapp.data.network.interceptors.ConnectivityInterceptor
import com.nickcorban.testapp.data.network.interceptors.ConnectivityInterceptorImpl
import com.nickcorban.testapp.repository.ContactsRepository
import com.nickcorban.testapp.repository.ContactsRepositoryImpl
import com.nickcorban.testapp.ui.contactDetail.ContactDetailViewModelFactory
import com.nickcorban.testapp.ui.contactslist.list.ContactsListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class ContactsApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy{
        import (androidXModule(this@ContactsApplication))

        bind() from singleton{ ContactsDatabase(instance()) }
        bind() from singleton{instance<ContactsDatabase>().contactsDao()}
        bind<ConnectivityInterceptor>() with singleton{ConnectivityInterceptorImpl(instance())}
        bind() from singleton{UserAPI(instance())}
        bind<ContactsNetworkDataSource>() with singleton{ContactsNetworkDataSourceImpl(instance())}
        bind<ContactsRepository>() with singleton{ContactsRepositoryImpl(instance(),instance())}
        bind() from provider {ContactsListViewModelFactory(instance()) }
        bind() from factory {id : Int -> ContactDetailViewModelFactory(instance(), id) }
    }
}