package com.rsanchez.pruebarepo.repository

import com.rsanchez.pruebarepo.interfaces.ICallRetrofit
import com.rsanchez.pruebarepo.models.Repository
import com.rsanchez.pruebarepo.models.RepositoryDetails
import com.rsanchez.pruebarepo.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository {


    fun getDataRepositoryFromApi(
        onSuccess: (List<Repository>) -> Unit,
        onFailure: (Error) -> Unit
    ) {
        val retrofit = RetrofitConfi.getConfig()
        retrofit.let {
            val call = retrofit.create(ICallRetrofit::class.java).getDataPost()
            call.enqueue(object : Callback<List<Repository>> {
                override fun onResponse(
                    call: Call<List<Repository>>,
                    response: Response<List<Repository>>
                ) {
                    if (response.body() != null) {
                        onSuccess.invoke(response.body()!!)
                    } else {
                        onFailure.invoke(Error(Constant.ERROR_JSON))
                    }
                }

                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    onFailure.invoke(Error(Constant.ERROR_JSON))
                }
            })
        }
    }


    fun getDataReposDetailsFromApi(
        url_repos: String,
        onSuccess: (List<RepositoryDetails>) -> Unit,
        onFailure: (Error) -> Unit
    ) {
        val retrofit = RetrofitConfi.getConfig()
        retrofit.let {
            val call = retrofit.create(ICallRetrofit::class.java).getDataReposDetails(url_repos)
            call.enqueue(object : Callback<List<RepositoryDetails>> {
                override fun onResponse(
                    call: Call<List<RepositoryDetails>>,
                    response: Response<List<RepositoryDetails>>
                ) {
                    if (response.body() != null) {
                        onSuccess.invoke(response.body()!!)
                    } else {
                        onFailure.invoke(Error(Constant.ERROR_JSON))
                    }
                }

                override fun onFailure(call: Call<List<RepositoryDetails>>, t: Throwable) {
                    onFailure.invoke(Error(Constant.ERROR_JSON))
                }
            })
        }
    }


}