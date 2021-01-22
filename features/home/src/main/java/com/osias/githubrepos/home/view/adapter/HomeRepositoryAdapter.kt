package com.osias.githubrepos.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osias.githubrepos.home.model.RepositoryAndOwner
import com.osias.home.R
import com.osias.home.databinding.FragmentHomeRepositoryItemBinding

class HomeRepositoryAdapter: PagingDataAdapter<RepositoryAndOwner, HomeRepositoryAdapter.HomeViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.title.text = item?.repository?.fullName
        holder.description.text = item?.repository?.description
        holder.starCount.text = item?.repository?.starCount.toString()
        holder.forksCount.text = holder.itemView.context.resources.getString(R.string.forks_count, item?.repository?.forksCount)
        Glide.with(holder.itemView)
            .load(item?.owner?.avatarUrl).circleCrop()
            .into(holder.avatar)
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
        val avatar = view.avatarPhoto
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<RepositoryAndOwner>() {
            override fun areItemsTheSame(oldItem: RepositoryAndOwner, newItem: RepositoryAndOwner): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: RepositoryAndOwner, newItem: RepositoryAndOwner): Boolean {
                return oldItem.repository.id == newItem.repository.id
            }

        }
    }

}