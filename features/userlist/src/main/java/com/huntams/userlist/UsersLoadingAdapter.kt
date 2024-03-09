package com.huntams.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huntams.userlist.databinding.ItemLoadStateBinding
import javax.inject.Inject

class UsersLoadingAdapter @Inject constructor(
) : LoadStateAdapter<UsersLoadingAdapter.DataViewHolder>() {

    private var onClick: (LoadState) -> Unit = {}
    fun setCallback(callback: (LoadState) -> Unit) {
        this.onClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): DataViewHolder {
        val binding =
            ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    /**
     * The same layout is used for:
     * - footer
     * - main indicator
     */

    inner class DataViewHolder(
        private val binding: ItemLoadStateBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LoadState) {
            with(binding) {
                textViewError.isVisible = loadState is LoadState.Error
                buttonRestart.isVisible = loadState is LoadState.Error
                progressBar.isVisible = loadState !is LoadState.Error
                binding.buttonRestart.setOnClickListener {
                    onClick(item)
                }
            }

        }
    }
}