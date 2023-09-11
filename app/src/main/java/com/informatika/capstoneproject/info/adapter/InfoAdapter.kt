package com.informatika.capstoneproject.info.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.informatika.capstoneproject.R
import com.informatika.capstoneproject.info.DetailInformasiActivity
import com.informatika.capstoneproject.model.ModelArticle
import com.informatika.capstoneproject.util.Utils.DateFormat
import com.informatika.capstoneproject.util.Utils.DateTimeHourAgo
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.information_layout.view.*

class InfoAdapter(private val modelArticles: MutableList<ModelArticle>, private val context: Context) :
    RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.information_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = modelArticles[position]

        if (model.urlToImage == null) {
            holder.imgThumbnailInfo.setImageResource(R.drawable.ic_image)
        } else {
            Glide.with(context)
                .load(model.urlToImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgThumbnailInfo)
        }

        if (model.author == null) {
            holder.tvNameSourceInfo.text = model.modelSource?.name
        } else {
            holder.tvNameSourceInfo.text = model.author + " \u2022 " + model.modelSource?.name
        }

        holder.tvTimeAgoInfo.text = DateTimeHourAgo (model.publishedAt)
        holder.tvTitleInfo.text = model.title
        holder.tvDateTimeInfo.text = DateFormat (model.publishedAt)
        holder.frameListInfo.setOnClickListener {
            val intent = Intent(context, DetailInformasiActivity::class.java)
            intent.putExtra(DetailInformasiActivity.DETAIL_NEWS, modelArticles[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelArticles.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var frameListInfo: FrameLayout
        var tvTimeAgoInfo: TextView
        var tvNameSourceInfo: TextView
        var tvTitleInfo: TextView
        var tvDateTimeInfo: TextView
        var imgThumbnailInfo: RoundedImageView

        init {
            frameListInfo = itemView.frameListInfo
            tvTimeAgoInfo = itemView.tvTimeAgoInfo
            tvNameSourceInfo = itemView.tvNameSourceInfo
            tvTitleInfo = itemView.tvTitleInfo
            tvDateTimeInfo = itemView.tvDateTimeInfo
            imgThumbnailInfo = itemView.imgThumbnailInfo
        }
    }

}