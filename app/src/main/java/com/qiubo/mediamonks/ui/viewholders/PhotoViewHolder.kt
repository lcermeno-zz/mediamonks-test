package com.qiubo.mediamonks.ui.viewholders

import android.view.View
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.qiubo.mediamonks.entities.Photo
import com.qiubo.mediamonks.ui.adapters.PhotosAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_photo_item.*
import kotlin.math.roundToInt
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory


class PhotoViewHolder(
    override val containerView: View,
    private val mListener: PhotosAdapter.IOnClickListener
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

    fun setValues(item: Photo) {
        photoRoot.layoutParams.height = item.size.roundToInt()
        photoRoot.requestLayout()
        photoImg.requestLayout()
        photoImg.setOnClickListener {
            val pair: Pair<View, String> = Pair(photoImg, "transitionPhoto")
            mListener.onClickItem(item, pair)
        }
        Glide
            .with(containerView.context)
            .load(item.url)
            .transition(withCrossFade(factory))
            .into(photoImg)
    }
}