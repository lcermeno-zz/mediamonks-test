package com.qiubo.mediamonks.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.entities.Photo
import com.qiubo.mediamonks.model.domain.GetMediaUseCase
import com.qiubo.mediamonks.view.IAlbumDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlbumDetailPresenter(
    private var mView: IAlbumDetailView?,
    private val mGetMediaUseCase: GetMediaUseCase,
    private val mAlbum: Album,
    private val width: Int
) :
    IAlbumDetailPresenter {


    private var mPage = 1
    private var mLoading = false

    companion object {
        private const val TAG = "AlbumDetailPresenter"
    }

    override fun getPhotos() {
        if (!mLoading) {
            mPage = 1
            val list = mutableListOf<Photo>()
            requestData(list) { mView?.onGetItems(list) }
        }
    }

    override fun loadMoreItems(isBottom: Boolean) {
        if (!mLoading) {
            mPage++
            val list = mutableListOf<Photo>()
            requestData(list) { mView?.onLoadMore(list) }
        }
    }

    @SuppressLint("CheckResult")
    private fun requestData(list: MutableList<Photo>, function: (List<Photo>) -> Unit) {
        mLoading = true
        mGetMediaUseCase.getPhotos(mAlbum.id, mPage)
            .map {

                var row = 0

                for ((index, photo) in it.withIndex()) {

                    if (index % 3 == 0) row++

                    when (row % 3) {
                        0 -> assignItemSize(1, index, photo)
                        1 -> assignItemSize(2, index, photo)
                        2 -> assignItemSize(3, index, photo)
                    }
                }
                it
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "Received album list with size: ${it.size}")
                list.addAll(it)
                function(list)
                mLoading = false
            }, {
                Log.e(TAG, it.message)
                it.printStackTrace()
            })
    }

    private fun assignItemSize(option: Int, index: Int, photo: Photo) {
        when (option) {
            1 -> when {
                index % 3 == 0 -> photo.size = width * 0.5f
                index % 3 == 1 -> photo.size = width * 0.3f
                else -> photo.size = width * 0.2f
            }
            2 -> when {
                index % 3 == 0 -> photo.size = width * 0.2f
                index % 3 == 1 -> photo.size = width * 0.5f
                else -> photo.size = width * 0.3f
            }
            3 -> when {
                index % 3 == 0 -> photo.size = width * 0.3f
                index % 3 == 1 -> photo.size = width * 0.2f
                else -> photo.size = width * 0.5f
            }
        }
    }
}