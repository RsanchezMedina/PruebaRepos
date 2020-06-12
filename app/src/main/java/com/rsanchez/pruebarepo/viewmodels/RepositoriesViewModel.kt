package com.rsanchez.pruebarepo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsanchez.pruebarepo.iterator.RepositoriesIterator
import com.rsanchez.pruebarepo.models.Repository
import com.rsanchez.pruebarepo.models.RepositoryDetails

class RepositoriesViewModel(private val iterator: RepositoriesIterator) : ViewModel() {

    private var dataRepositories = MutableLiveData<List<Repository>>()
    private var dataReposDetails = MutableLiveData<List<RepositoryDetails>>()

    fun getlistRepositories(): MutableLiveData<List<Repository>> {
        return dataRepositories
    }


    fun getListReposDetails(): MutableLiveData<List<RepositoryDetails>> {
        return dataReposDetails
    }

    fun getAllReporitory() {
        iterator.getDataRepository({ list ->
            val auxArray: ArrayList<Repository> = ArrayList()
            for (i in list.indices) {
                if (!list.get(i).private) {
                    auxArray.add(list.get(i))
                }
            }
            getlistRepositories().value = auxArray
        }, { error ->

        })
    }

    fun getReposDetails(url_repos: String) {
        iterator.getDataReposDetails(url_repos, { list ->
            getListReposDetails().value = list
        }, { error ->

        })
    }


}