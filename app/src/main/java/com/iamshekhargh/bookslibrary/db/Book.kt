package com.iamshekhargh.bookslibrary.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by <<-- iamShekharGH -->>
 * on 07 July 2021, Wednesday
 * at 9:34 PM
 */
@Entity(tableName = "books_table")
@Parcelize
data class Book(
    val product_desc: String = "",
    val product_name: String,
    val product_price: Int,
    val product_quantity: Int,
    val user_mobile_no: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable
