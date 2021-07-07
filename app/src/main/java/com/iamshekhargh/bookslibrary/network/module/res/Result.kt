package com.iamshekhargh.bookslibrary.network.module.res

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "results_table")
@Parcelize
data class Result(
    val book_author: String = "",
    val book_desc: String = "",
    @PrimaryKey val book_id: Int = 0,
    val book_img_url: String = "",
    val book_name: String,
    val book_price: String
) : Parcelable