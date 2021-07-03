package com.example.evoketechproject.viewModels

import androidx.lifecycle.LiveData
import com.example.evoketechproject.database.AppDatabase
import com.example.evoketechproject.database.entity.UserEntity
import com.example.evoketechproject.models.response.UserResponse
import com.example.evoketechproject.network.ApiRequest
import retrofit2.Response
import java.io.IOException

/**
 * @param request   RetrofitClient object of API call
 * @param db        Local app Database object
 */
class UserRepository(private val request: ApiRequest, private val db: AppDatabase) {

    /**
     * @return Response<ArrayList<UserResponse>>
     */
    suspend fun getUserFromAPI(): Response<ArrayList<UserResponse>>? {
        return try {
            request.getUsers()
        } catch (e: IOException) {
            null
        }
    }

    /**
     * @return LiveData<List<User>>
     */
    fun fetchUserFromDatabase(): LiveData<List<UserEntity>> {
        return db.getUserDao().getAllUser()
    }

    /**
     * @param userEntity
     */
    suspend fun saveUserIntoDB(userEntity: UserEntity) {
        db.getUserDao().upsert(userEntity)
    }
}