package com.qiubo.mediamonks.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qiubo.mediamonks.R
import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.ui.viewholders.AlbumViewHolder

class AlbumAdapter(private val mItems: List<Album>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_album_item, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as AlbumViewHolder
        viewHolder.setValues(mItems[position])
    }
}