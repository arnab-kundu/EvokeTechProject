package com.example.evoketechproject.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evoketechproject.R
import com.example.evoketechproject.databinding.ActivityUsersListBinding
import com.example.evoketechproject.models.response.UserResponse
import com.example.evoketechproject.viewModels.UserListViewModel
import com.example.evoketechproject.viewModels.UserListViewModelFactory
import com.example.evoketechproject.views.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_users_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class UsersListActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: UserListViewModelFactory by instance()

    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityUsersListBinding = DataBindingUtil.setContentView(this, R.layout.activity_users_list)
        val viewModel = ViewModelProviders.of(this, factory).get(UserListViewModel::class.java)
        binding.viewModel = viewModel

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_user_list.layoutManager = layoutManager

        adapter = UserAdapter()
        rv_user_list.adapter = adapter

        viewModel.userLiveData().observe(this, Observer { userList -> adapter.setData(userList) })
    }
}





























//val apiRequest = RetrofitClient.retrofitInstance!!.create(ApiRequest::class.java)
/*apiRequest.getUsers().enqueue(object : Callback<ArrayList<UserResponse>> {
    override fun onResponse(call: Call<ArrayList<UserResponse>>, response: Response<ArrayList<UserResponse>>) {
        adapter.setData(response.body()!!)
    }

    override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable) {

    }

})*/

/* Corotines.IO {
     userList = apiRequest.getUsers().execute().body()!!
     for (user in userList) {
         Log.d(TAG, user.toString())
     }

 }.apply {
     adapter.setData(userList)
 }*/
