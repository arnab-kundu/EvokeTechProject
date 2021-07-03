package com.example.evoketechproject.viewModels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.evoketechproject.EvokeTechApplication
import com.example.evoketechproject.database.entity.UserEntity

class UserListViewModel(private val repository: UserRepository) : AndroidViewModel(EvokeTechApplication()) {

    /**
     * Get LiveData of userList
     * @return LiveData<List<UserEntity>>
     */
    fun userLiveData(): LiveData<List<UserEntity>> {
        return repository.fetchUserFromDatabase()
    }
}