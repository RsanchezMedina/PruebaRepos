package com.rsanchez.pruebarepo.interfaces

import com.rsanchez.pruebarepo.models.Owner
import com.rsanchez.pruebarepo.models.Repository

interface IFragmentChange {
    fun selectRepository(owner: Owner)

    fun filterSelection(list: ArrayList<Repository>)

}