package com.qiubo.mediamonks.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.misc.Constants
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
        if (isBottom && !mLoading && mPage != Constants.INDEX_NOT_FOUND) {
            mPage++
            val list = mutableListOf<Album>()
            requestData(list) { mView?.onLoadMore(list) }
        }
    }

    @SuppressLint("CheckResult")
    private fun requestData(list: MutableList<Album>, function: (List<Album>) -> Unit) {
        mLoading = true
        mGetMediaUseCase
            .getAll(mPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isEmpty()) mPage = Constants.INDEX_NOT_FOUND
                Log.d(TAG, "Received album list with size: ${it.size}")
                list.addAll(it)
                function(list)
                mLoading = false
            }, {
                Log.e(TAG, it.message)
                it.printStackTrace()
                it.message?.let { message -> mView?.onError(message) }
                mLoading = false
            })
    }
}