package com.iamshekhargh.bookslibrary.ui.viewAllBooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iamshekhargh.bookslibrary.databinding.ItemBookBinding
import com.iamshekhargh.bookslibrary.db.Book

/**
 * Created by <<-- iamShekharGH -->>
 * on 08 July 2021, Thursday
 * at 12:36 AM
 */
class BooksAdapter constructor() :
    ListAdapter<Book, BooksAdapter.BooksViewHolder>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BooksViewHolder(private val itemBookBinding: ItemBookBinding) :
        RecyclerView.ViewHolder(itemBookBinding.root) {
        fun bind(item: Book) {
            itemBookBinding.apply {
                itemBookName.text = item.product_name
                itemBookDesc.text = item.product_desc
                itemBookPrice.text = "Rs. ${item.product_price}"
                itemBookQuantity.text = "Quantity: ${item.product_quantity}"
            }
        }
    }
}

class DiffUtilItemCallback() : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem

}