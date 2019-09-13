package com.qiubo.mediamonks.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.qiubo.mediamonks.R
import com.qiubo.mediamonks.entities.Photo
import com.qiubo.mediamonks.ui.viewholders.PhotoViewHolder

class PhotosAdapter( private val mItems: MutableList<Photo>,
                     private val mListener: IOnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface IOnClickListener {
        fun onClickItem(
            item: Photo,
            pair: Pair<View, String>
        )
    }

    override fun getItemCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_photo_item, parent, false)
        return PhotoViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as PhotoViewHolder
        viewHolder.setValues(mItems[position])
    }

    fun setItems(items: List<Photo>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }
}