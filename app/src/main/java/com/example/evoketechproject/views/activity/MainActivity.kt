package com.example.evoketechproject.views.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.evoketechproject.R
import com.example.evoketechproject.databinding.ActivityMainBinding
import com.example.evoketechproject.viewModels.MainViewModel
import com.example.evoketechproject.viewModels.MainViewModelFactory
import com.example.evoketechproject.viewModels.listener.UserResponseListener
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(), KodeinAware, UserResponseListener {

    override val kodein by kodein()
    private val factory: MainViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.userResponseListener = this

    }

    override fun onLoading() {
        //TODO("Show progress")
    }

    override fun onSuccess() {
        startActivity(Intent(this, UsersListActivity::class.java))
    }

    override fun onFailure(massage: String) {
        Log.w("TAG", "onFailure: $massage" )
        startActivity(Intent(this, UsersListActivity::class.java))
        Toast.makeText(this, massage, Toast.LENGTH_LONG).show()
    }
}