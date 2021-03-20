package com.jdagnogo.lydiausers.repository.api

import com.jdagnogo.lydiausers.repository.api.model.ResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET(GET_USERS)
    suspend fun getUsers(
        @Query("seed") seed: String,
        @Query("results") page: Int,
        @Query("page") itemsPerPage: Int
    ): ResultResponse

    companion object {
        const val BASE_URL = "https://randomuser.me/"
        private const val GET_USERS = "api/1.0/"
    }
}