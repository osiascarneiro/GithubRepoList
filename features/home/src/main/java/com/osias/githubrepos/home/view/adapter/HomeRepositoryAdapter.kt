package com.osias.githubrepos.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.osias.githubrepos.home.model.RepositoryEntity
import com.osias.home.R
import com.osias.home.databinding.FragmentHomeRepositoryItemBinding

class HomeRepositoryAdapter: PagingDataAdapter<RepositoryEntity, HomeRepositoryAdapter.HomeViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.title.text = item?.fullName
        holder.description.text = item?.description
        holder.starCount.text = item?.starCount.toString()
        holder.forksCount.text = holder.itemView.context.resources.getString(R.string.forks_count, item?.forksCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = FragmentHomeRepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    inner class HomeViewHolder(view: FragmentHomeRepositoryItemBinding): RecyclerView.ViewHolder(view.root) {
        val title = view.title
        val description = view.description
        val starCount = view.starCount
        val forksCount = view.forksCount
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<RepositoryEntity>() {
            override fun areItemsTheSame(oldItem: RepositoryEntity, newItem: RepositoryEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: RepositoryEntity, newItem: RepositoryEntity): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}