package com.rsanchez.pruebarepo.ui.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rsanchez.pruebarepo.R
import com.rsanchez.pruebarepo.ui.Fragment.RootFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = RootFragment()
        fragmentTransaction.add(R.id.contentFragment, fragment)
        fragmentTransaction.commit()
    }
}
