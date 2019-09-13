package com.qiubo.mediamonks.view

import com.qiubo.mediamonks.entities.Album

interface IMainView : IOnError{
    fun onGetItems(items: List<Album>)
}