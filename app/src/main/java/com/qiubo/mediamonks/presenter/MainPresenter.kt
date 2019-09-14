package com.qiubo.mediamonks.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.model.domain.GetMediaUseCase
import com.qiubo.mediamonks.view.IMainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private var mView: IMainView?, private val mGetMediaUseCase: GetMediaUseCase) :
    IMainPresenter {

    private var mPage = 1
    private var mLoading = false

    companion object {
        private const val TAG = "MainPresenter"
    }


    override fun getAllAlbum() {
        if (!mLoading) {
            mPage = 1
            val list = mutableListOf<Album>()
            requestData(list) { mView?.onGetItems(list) }
        }
    }

    override fun loadMoreItems(isBottom: Boolean) {
        if (!mLoading) {
            mPage++
            val list = mutableListOf<Album>()
            requestData(list) { mView?.onLoadMore(list) }
        }
    }

    @SuppressLint("CheckResult")
    private fun requestData(list: MutableList<Album>, function: (List<Album>) -> Unit) {
        mGetMediaUseCase
            .getAll(mPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "Received album list with size: ${it.size}")
                list.addAll(it)
                function(list)
            }, {
                Log.e(TAG, it.message)
                it.printStackTrace()
            })
    }
}