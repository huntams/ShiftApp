package com.huntams.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.huntams.model.User
import com.huntams.userlist.databinding.ItemUserBinding
import javax.inject.Inject

class UsersPagingAdapter @Inject constructor() :
    PagingDataAdapter<User, UsersPagingAdapter.DataViewHolder>(diffUtilCallback) {

    private var onClick: (User) -> Unit = {}
    fun setCallback(callback: (User) -> Unit) {
        this.onClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class DataViewHolder(
        private val binding: ItemUserBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            with(binding) {
                textViewAddress.text = item.location.streetStringBuilder()
                textViewName.text = item.name.nameBuilder()
                textViewPhone.text = item.phone
                imageViewPhoto.load(item.picture.thumbnail)
                binding.root.setOnClickListener {
                    onClick(item)
                }
            }

        }
    }
}

private val diffUtilCallback = object : DiffUtil.ItemCallback<User>() {

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.location.coordinates.latitude == newItem.location.coordinates.latitude
    }
}