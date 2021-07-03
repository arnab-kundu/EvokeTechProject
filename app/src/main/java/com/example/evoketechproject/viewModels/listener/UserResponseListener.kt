package com.example.evoketechproject.viewModels.listener


interface UserResponseListener {
    fun onLoading()
    fun onSuccess()
    fun onFailure(massage: String)
}