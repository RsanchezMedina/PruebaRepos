package com.rsanchez.pruebarepo.models

import java.io.Serializable

data class Owner(var id : Int,
                 var login : String,
                 var avatar_url : String,
                 var repos_url : String,
                 var html_url : String) : Serializable