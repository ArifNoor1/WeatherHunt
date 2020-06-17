package com.devadilarif.weatherhunt.repo.local.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.devadilarif.weatherhunt.R
import com.squareup.picasso.Picasso

@Entity
data class News(
    var author: String,
    var content: String,
    var description: String,

//    @Ignore
    var publishedAt: String,

    @Embedded
    var source: Source,
    var title: String,
    var url: String,
    var urlToImage: String,

    @PrimaryKey
    var timeStamp : Double = publishedAt.toDouble()
){

    companion object{
        @BindingAdapter("imageUrl")
        fun loadImage(imageView : ImageView, newsImageUrl: String){
            Picasso.get().load(newsImageUrl).into(imageView)
        }
    }
}