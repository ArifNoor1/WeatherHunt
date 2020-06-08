package com.devadilarif.weatherhunt.repo.local.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.Ignore
import com.devadilarif.weatherhunt.R
import com.squareup.picasso.Picasso

@Entity
data class News(
    val author: String,
    val content: String,
    val description: String,

    @Ignore
    val publishedAt: String,

    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
    val timeStamp : Double = publishedAt.toDouble()
){

    companion object{
        @BindingAdapter("imageUrl")
        fun loadImage(imageView : ImageView, newsImageUrl: String){
            Picasso.get().load(newsImageUrl).into(imageView)
        }
    }
}