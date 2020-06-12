package com.rsanchez.pruebarepo.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.rsanchez.pruebarepo.R
import com.rsanchez.pruebarepo.interfaces.IFragmentChange
import com.rsanchez.pruebarepo.models.Repository
import com.rsanchez.pruebarepo.utils.inflate
import kotlinx.android.synthetic.main.item_repository_list.view.*


class AdapterListRepository(
    private var items: ArrayList<Repository>,
    private var listener: IFragmentChange
) : RecyclerView.Adapter<AdapterListRepository.ViewHolder>(),
    Filterable {

    internal var filterList: ArrayList<Repository>

    init {
        this.filterList = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_repository_list))
    }

    override fun onBindViewHolder(holder: AdapterListRepository.ViewHolder, position: Int) =
        holder.bind(filterList[position], position, listener)

    override fun getItemCount() = filterList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Repository, position: Int, listener: IFragmentChange) = with(itemView) {
            tvNumber.text = position.toString()
            tvName.text = item.name
            tvFullName.text = item.full_name
            tvDescription.text = item.description
            contentItemRepository.setOnClickListener {
                listener.selectRepository(item.owner)
            }

        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterString = constraint.toString().toLowerCase()
                if (filterString.isEmpty()) {
                    filterList = items
                } else {
                    val resultList = ArrayList<Repository>()
                    for (row in items) {
                        if (row.name.toLowerCase().contains(filterString.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    filterList = resultList
                }
                val results = Filter.FilterResults()
                results.values = filterList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<Repository>
                notifyDataSetChanged()

            }

        }
    }
}