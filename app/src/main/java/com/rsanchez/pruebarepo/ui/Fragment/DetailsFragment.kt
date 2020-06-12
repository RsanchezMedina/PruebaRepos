package com.rsanchez.pruebarepo.ui.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsanchez.pruebarepo.R
import com.rsanchez.pruebarepo.adapters.AdapterListDetailsRepository
import com.rsanchez.pruebarepo.factory.RepositoriesFactory
import com.rsanchez.pruebarepo.iterator.RepositoriesIterator
import com.rsanchez.pruebarepo.models.Owner
import com.rsanchez.pruebarepo.models.RepositoryDetails
import com.rsanchez.pruebarepo.utils.Constant
import com.rsanchez.pruebarepo.utils.loadImgFromUrl
import com.rsanchez.pruebarepo.viewmodels.RepositoriesViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*


class DetailsFragment : Fragment() {

    companion object {
        fun newInstance(owner: Owner) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(Constant.DATA_OWNER, owner)
            }
        }
    }

    private lateinit var _view: View
    private lateinit var viewModel: RepositoriesViewModel

    private var listRepositories: ArrayList<RepositoryDetails> = ArrayList()
    private lateinit var adapter: AdapterListDetailsRepository

    private lateinit var tvName: TextView
    private lateinit var tvLink: TextView
    private lateinit var imgRepo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _view = inflater.inflate(R.layout.fragment_details, container, false)
        inizialice();
        setUpRecycleView()
        viewModel = ViewModelProvider(
            this,
            RepositoriesFactory(RepositoriesIterator())
        )[RepositoriesViewModel::class.java]
        viewModel.let {
            arguments?.getSerializable(Constant.DATA_OWNER)?.let { owner ->
                if (owner is Owner) {
                    //rellenamos la cabezera
                    tvName.text = owner.login
                    tvLink.text = owner.html_url
                    imgRepo.loadImgFromUrl(owner.avatar_url)
                    viewModel.getReposDetails(owner.repos_url)
                    viewModel.getListReposDetails().observe(this, androidx.lifecycle.Observer {
                        progressDetails.visibility = View.GONE
                        listRepositories.clear()
                        listRepositories.addAll(it)
                        adapter.notifyDataSetChanged()
                    })

                    tvLink.setOnClickListener {
                        val uri: Uri = Uri.parse(owner.html_url)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    }


                }
            }
        }
        return _view
    }

    private fun inizialice() {
        tvName = _view.findViewById(R.id.tvNameDetails)
        tvLink = _view.findViewById(R.id.tvLink)
        imgRepo = _view.findViewById(R.id.imgRepo)
    }

    private fun setUpRecycleView() {
        val layoutManager = LinearLayoutManager(context)
        adapter = AdapterListDetailsRepository(context!!, listRepositories)
        _view.listRepositories.setHasFixedSize(true)
        _view.listRepositories.layoutManager = layoutManager
        _view.listRepositories.itemAnimator = DefaultItemAnimator()
        _view.listRepositories.adapter = adapter
    }

}
