package com.example.evoketechproject.viewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.example.evoketechproject.EvokeTechApplication
import com.example.evoketechproject.database.entity.UserEntity
import com.example.evoketechproject.models.response.UserResponse
import com.example.evoketechproject.network.Corotines
import com.example.evoketechproject.viewModels.listener.UserResponseListener
import retrofit2.Response

class MainViewModel(private val repository: UserRepository) : AndroidViewModel(EvokeTechApplication()) {
    private val TAG = "MainViewModel"

    var userResponseListener: UserResponseListener? = null

    /**
     * Get user from server using API call
     * and
     * Save the response in local Database
     */
    fun getUserFromServer(view: View) {
        Corotines.main {
            val userResponse: Response<ArrayList<UserResponse>>? = repository.getUserFromAPI()
            if (userResponse != null) {
                if (userResponse.isSuccessful) {
                    val users: ArrayList<UserResponse> = userResponse.body()!!
                    for (user in users) {
                        Log.d(TAG, "getUserFromServer: $user")
                        repository.saveUserIntoDB(userEntity = UserEntity(id = user.id, name = user.name, email = user.email, phone = user.phone))
                    }
                    userResponseListener?.onSuccess()
                } else {
                    userResponseListener?.onFailure("Some error occurred. ${userResponse.errorBody()}")
                }
            } else {
                userResponseListener?.onFailure("Some error occurred. Showing offline data.")
            }
        }
    }
}