package com.az3ez.newzbee.Adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.shafiq.newsheadlines.models.Article

class Adapter(private val mArticleList: List<Article>, private val mContext: Context) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    val itemCount: Int
        get() = mArticleList.size

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false)
        return MyViewHolder(view, onItemClickListener)
    }

    fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val model = mArticleList[position]

        val requestOptions = RequestOptions()
        requestOptions.placeholder(Utils.getRandomDrawbleColor())
        requestOptions.error(Utils.getRandomDrawbleColor())
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions.centerCrop()

        Glide.with(mContext)
            .load(model.getUrlToImage())
            .apply(requestOptions)
            .listener(object : RequestListener<Drawable>() {
                fun onLoadFailed(
                    e: GlideException,
                    model: Any,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }

                fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }
            }).transition(DrawableTransitionOptions.withCrossFade()).into(holder.imageView)

        holder.title.setText(model.getTitle())
        holder.description.setText(model.getDescription())
        holder.source.setText(model.getSource().getName())
        holder.time.text = " \u2022" + Utils.DateToTimeFormat(model.getPublishedAt())
        holder.published_At.setText(Utils.DateFormat(model.getPublishedAt()))
        holder.author.setText(model.getAuther())

    }

    inner class MyViewHolder(
        itemView: View,
        internal var onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var title: TextView
        internal var description: TextView
        internal var author: TextView
        internal var published_At: TextView
        internal var source: TextView
        internal var time: TextView
        internal var imageView: ImageView
        internal var progressBar: ProgressBar

        init {
            itemView.setOnClickListener(this)

            title = itemView.findViewById(R.id.title)
            description = itemView.findViewById(R.id.desc)
            author = itemView.findViewById(R.id.author)
            published_At = itemView.findViewById(R.id.publishedAt)
            source = itemView.findViewById(R.id.source)
            time = itemView.findViewById(R.id.time)
            imageView = itemView.findViewById(R.id.img)
            progressBar = itemView.findViewById(R.id.progress_load_photo)
        }

        override fun onClick(v: View) {
            onItemClickListener.onItemClick(v, getAdapterPosition())
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}
