package com.sunil.agentapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunil.agentapp.R
import com.sunil.agentapp.model.Result
import kotlinx.android.synthetic.main.item_layout.view.*

class MovieListAdapter(private var result: ArrayList<Result>) : RecyclerView.Adapter<MovieListAdapter.DataViewHolder>() ,
    Filterable {

    lateinit var mResultCopyData:ArrayList<Result>




    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(result: Result) {
            itemView.apply {
                txtTitle.text = result.artistName
                txtDescription.text ="Track: "+result.trackName
                txtCollectionName.text="Collection: "+result.collectionName
                txtCollectionprice.text="CollectionPrice: "+result.collectionPrice+result.currency
                Glide.with(imageViewAvatar.context)
                    .load(result.artworkUrl60)
                    .placeholder(R.drawable.ic_no_image)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        mResultCopyData=result

        holder.bind(result[position])
    }

    fun addUsers(result: List<Result>) {
        this.result.apply {
            clear()
            addAll(result)
            notifyDataSetChanged()
        }

    }

    override fun getFilter(): Filter {
        ///
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charSequenceString = constraint.toString()
                if (charSequenceString.isEmpty()) {
                    result = mResultCopyData
                } else {
                    val filteredList: MutableList<Result> = ArrayList()
                    for (name in mResultCopyData) {
                        if (name.artistName.toLowerCase()
                                .contains(charSequenceString.toLowerCase())
                        ) {
                            filteredList.add(name)
                        }
                        result = filteredList as ArrayList<Result>
                    }
                }
                val results = FilterResults()
                results.values = result
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                result = results.values as ArrayList<Result>
                notifyDataSetChanged()
            }
        }
        ///

    }
}