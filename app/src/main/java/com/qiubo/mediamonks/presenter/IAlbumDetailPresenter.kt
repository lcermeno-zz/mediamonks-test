package com.qiubo.mediamonks.presenter

interface IAlbumDetailPresenter {
    fun getPhotos()
    fun loadMoreItems(isBottom: Boolean = false)
}