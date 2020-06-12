package com.rsanchez.pruebarepo.models

import java.io.Serializable

data class Repository(var id : Int,
                      var node_id : String,
                      var name: String,
                      var full_name : String,
                      var description : String,
                      var private : Boolean,
                      var owner : Owner)