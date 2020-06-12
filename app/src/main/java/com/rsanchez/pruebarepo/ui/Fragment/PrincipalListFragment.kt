package com.rsanchez.pruebarepo.ui.Fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsanchez.pruebarepo.R
import com.rsanchez.pruebarepo.adapters.AdapterListRepository
import com.rsanchez.pruebarepo.factory.RepositoriesFactory
import com.rsanchez.pruebarepo.interfaces.IFragmentChange
import com.rsanchez.pruebarepo.iterator.RepositoriesIterator
import com.rsanchez.pruebarepo.models.Owner
import com.rsanchez.pruebarepo.models.Repository
import com.rsanchez.pruebarepo.viewmodels.RepositoriesViewModel
import kotlinx.android.synthetic.main.fragment_principal_list.*
import kotlinx.android.synthetic.main.fragment_principal_list.view.*


class PrincipalListFragment : Fragment(), IFragmentChange {

    private lateinit var _view: View
    private lateinit var viewModel: RepositoriesViewModel

    private var listRepositories: ArrayList<Repository> = ArrayList()
    private lateinit var adapter: AdapterListRepository
    private lateinit var filter: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _view = inflater.inflate(R.layout.fragment_principal_list, container, false)
        viewModel = ViewModelProvider(
            this,
            RepositoriesFactory(RepositoriesIterator())
        )[RepositoriesViewModel::class.java]

        setUpRecycleView()
        viewModel.let {
            viewModel.getAllReporitory()

            viewModel.getlistRepositories().observe(this, Observer {
                progressBar.visibility = View.GONE
                listRepositories.clear()
                listRepositories.addAll(it)
                adapter.notifyDataSetChanged()

            })

            val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
            filter = _view.findViewById(R.id.searchView)
            filter.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            filter.maxWidth = Int.MAX_VALUE

            filter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    adapter.filter.filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }

            })
        }
        return _view
    }

    override fun selectRepository(owner: Owner) {
        filter.setQuery("", false);
        filter.clearFocus();
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        val fm = DetailsFragment.newInstance(owner)
        transaction.replace(R.id.root_frame, fm)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun filterSelection(list: ArrayList<Repository>) {
        listRepositories.clear()
        listRepositories.addAll(list)
        adapter.notifyDataSetChanged()
    }

    private fun setUpRecycleView() {
        val layoutManager = LinearLayoutManager(context)
        adapter = AdapterListRepository(listRepositories, this)
        _view.repositoryList.setHasFixedSize(true)
        _view.repositoryList.layoutManager = layoutManager
        _view.repositoryList.itemAnimator = DefaultItemAnimator()
        _view.repositoryList.adapter = adapter
    }

}
