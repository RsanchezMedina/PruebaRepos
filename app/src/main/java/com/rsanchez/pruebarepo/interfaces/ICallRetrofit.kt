package com.rsanchez.pruebarepo.interfaces

import com.rsanchez.pruebarepo.models.Repository
import com.rsanchez.pruebarepo.models.RepositoryDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ICallRetrofit {

    @GET("/repositories")
    fun getDataPost(): Call<List<Repository>>

    @GET
    fun getDataReposDetails(@Url url: String?): Call<List<RepositoryDetails>>
}