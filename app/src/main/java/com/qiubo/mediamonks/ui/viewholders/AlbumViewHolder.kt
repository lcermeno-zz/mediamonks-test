package com.qiubo.mediamonks.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.ui.adapters.AlbumAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_album_item.*

class AlbumViewHolder(
    override val containerView: View,
    private val mListener: AlbumAdapter.IOnClickListener
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun setValues(item: Album) {
        albumTitle.text = item.title
        albumRoot.setOnClickListener { mListener.onClickItem(item) }
    }
}