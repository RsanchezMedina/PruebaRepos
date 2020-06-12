package com.rsanchez.pruebarepo.repository

import com.rsanchez.pruebarepo.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfi {
    companion object {
        private lateinit var retrofit: Retrofit
        /**
         * Metodo de configuracion de retrofit
         */
        fun getConfig(): Retrofit {
            retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }
}