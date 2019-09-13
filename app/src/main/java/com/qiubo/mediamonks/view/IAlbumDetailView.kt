package com.qiubo.mediamonks.view

import com.qiubo.mediamonks.entities.Photo

interface IAlbumDetailView: IOnError {
    fun onGetItems(items: List<Photo>)
}