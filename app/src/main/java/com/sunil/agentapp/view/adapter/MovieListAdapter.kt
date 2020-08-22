package com.sunil.agentapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunil.agentapp.R
import com.sunil.agentapp.model.Result
import kotlinx.android.synthetic.main.item_layout.view.*

class MovieListAdapter (private val result: ArrayList<Result>) : RecyclerView.Adapter<MovieListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(result: Result) {
            itemView.apply {
                txtTitle.text = result.collectionName
                txtDescription.text = result.artistName
                Glide.with(imageViewAvatar.context)
                    .load(result.artworkUrl60)
                    .placeholder(R.drawable.ic_no_image)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(result[position])
    }

    fun addUsers(result: List<Result>) {
        this.result.apply {
            clear()
            addAll(result)
            notifyDataSetChanged()
        }

    }
}