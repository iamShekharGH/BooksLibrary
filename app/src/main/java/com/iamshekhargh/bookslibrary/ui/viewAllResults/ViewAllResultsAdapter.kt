package com.iamshekhargh.bookslibrary.ui.viewAllResults

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iamshekhargh.bookslibrary.databinding.ItemResultBinding
import com.iamshekhargh.bookslibrary.network.module.res.Result

/**
 * Created by <<-- iamShekharGH -->>
 * on 07 July 2021, Wednesday
 * at 11:57 PM
 */
class ViewAllResultsAdapter constructor() :
    ListAdapter<Result, ViewAllResultsAdapter.ResultViewHolder>(DiffUtilsItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ResultViewHolder(
        private val itemResultBinding: ItemResultBinding,
    ) : RecyclerView.ViewHolder(itemResultBinding.root) {

        fun bind(item: Result) {
            itemResultBinding.apply {
                Glide.with(itemResultImage).load(item.book_img_url).into(itemResultImage)
                itemResultName.text = item.book_name
                itemResultAuthor.text = item.book_author
                itemResultDescription.text = item.book_desc
                itemResultPrice.text = "Rs. ${item.book_price}"
            }
        }
    }
}

class DiffUtilsItemCallback : DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem.book_id == newItem.book_id


    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean = oldItem == newItem


}