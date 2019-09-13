package com.qiubo.mediamonks.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.qiubo.mediamonks.entities.Album
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_album_item.*

class AlbumViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun setValues(item: Album) {
        album_title.text = item.title
    }
}