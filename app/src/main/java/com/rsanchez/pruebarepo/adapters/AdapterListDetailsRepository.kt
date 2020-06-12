package com.rsanchez.pruebarepo.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsanchez.pruebarepo.R
import com.rsanchez.pruebarepo.models.RepositoryDetails
import com.rsanchez.pruebarepo.utils.inflate
import kotlinx.android.synthetic.main.item_repository_details_list.view.*


class AdapterListDetailsRepository(
    private var context: Context,
    private var items: ArrayList<RepositoryDetails>
) : RecyclerView.Adapter<AdapterListDetailsRepository.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AdapterListDetailsRepository.ViewHolder(
            parent.inflate(
                R.layout.item_repository_details_list
            )
        )

    override fun onBindViewHolder(holder: AdapterListDetailsRepository.ViewHolder, position: Int) =
        holder.bind(items[position], position, context)

    override fun getItemCount() = items.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RepositoryDetails, position: Int, context: Context) = with(itemView) {
            tvNumber.text = position.toString()

            if (!item.name.isNullOrEmpty()) {
                tvNameDetails.text = item.name
            } else {
                textName.visibility = View.GONE
                tvNameDetails.visibility = View.GONE
            }

            if (!item.full_name.isNullOrEmpty()) {
                tvFullNameDetails.text = item.full_name
            } else {
                textFullName.visibility = View.GONE
                tvFullNameDetails.visibility = View.GONE
            }

            if (!item.description.isNullOrEmpty()) {
                tvDescriptionDetails.text = item.description
            } else {
                textDescription.visibility = View.GONE
                tvDescriptionDetails.visibility = View.GONE
            }

            if (!item.html_url.isNullOrEmpty()) {
                tvLinkDetails.text = item.html_url
            } else {
                textLink.visibility = View.GONE
                tvLinkDetails.visibility = View.GONE
            }

            tvLinkDetails.setOnClickListener {
                val uri: Uri = Uri.parse(item.html_url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }
        }
    }
}