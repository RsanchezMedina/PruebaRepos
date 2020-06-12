package com.rsanchez.pruebarepo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rsanchez.pruebarepo.iterator.RepositoriesIterator
import com.rsanchez.pruebarepo.viewmodels.RepositoriesViewModel

class RepositoriesFactory(private val iterator : RepositoriesIterator) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepositoriesViewModel(iterator) as T
    }
}