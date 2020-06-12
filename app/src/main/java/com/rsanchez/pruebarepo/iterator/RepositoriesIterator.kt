package com.rsanchez.pruebarepo.iterator

import com.rsanchez.pruebarepo.models.Repository
import com.rsanchez.pruebarepo.models.RepositoryDetails
import com.rsanchez.pruebarepo.repository.DataRepository

class RepositoriesIterator {

    fun getDataRepository(onSuccess: (List<Repository>) -> Unit, onFailure: ((Error) -> Unit)) {
        DataRepository().getDataRepositoryFromApi({ list ->
            onSuccess.invoke(list)
        }, { error ->
            onFailure?.invoke(error)
        })
    }

    fun getDataReposDetails(
        url_repos: String,
        onSuccess: (List<RepositoryDetails>) -> Unit,
        onFailure: ((Error) -> Unit)
    ) {
        DataRepository().getDataReposDetailsFromApi(url_repos, { list ->
            onSuccess.invoke(list)
        }, { error ->
            onFailure?.invoke(error)
        })
    }
}