package com.rsanchez.pruebarepo.ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsanchez.pruebarepo.R

/**
 * A simple [Fragment] subclass.
 */
class RootFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val _view = inflater.inflate(R.layout.fragment_root, container, false)
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.root_frame, PrincipalListFragment())
        transaction.commit()
        return _view
    }

}
