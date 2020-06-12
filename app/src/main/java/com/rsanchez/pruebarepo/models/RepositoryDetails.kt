package com.rsanchez.pruebarepo.models

data class RepositoryDetails(var id : Int,
                             var name : String,
                             var full_name : String,
                             var description : String,
                             var html_url : String)