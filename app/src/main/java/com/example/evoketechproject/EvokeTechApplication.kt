package com.example.evoketechproject

import android.app.Application
import com.example.evoketechproject.database.AppDatabase
import com.example.evoketechproject.network.ApiRequest
import com.example.evoketechproject.network.NetworkConnectionInterceptor
import com.example.evoketechproject.viewModels.MainViewModelFactory
import com.example.evoketechproject.viewModels.UserListViewModelFactory
import com.example.evoketechproject.viewModels.UserRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * @author Arnab
 */
class EvokeTechApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@EvokeTechApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiRequest(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { MainViewModelFactory(instance()) }
        bind() from singleton { UserListViewModelFactory(instance()) }
    }

}