package com.qiubo.mediamonks.presenter

interface IMainPresenter {
    fun getAllAlbum()
    fun loadMoreItems(isBottom: Boolean)
}