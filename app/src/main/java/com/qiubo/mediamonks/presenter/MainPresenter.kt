package com.qiubo.mediamonks.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.qiubo.mediamonks.model.domain.GetMediaUseCase
import com.qiubo.mediamonks.view.IMainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private var mView: IMainView?, private val mGetMediaUseCase: GetMediaUseCase) :
    IMainPresenter {

    companion object {
        private const val TAG = "MainPresenter"
    }

    @SuppressLint("CheckResult")
    override fun getAllAlbum() {
        mGetMediaUseCase
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "Received album list with size: ${it.size}")
                mView?.onGetItems(it)
            }, {
                Log.e(TAG, it.message)
                it.printStackTrace()
            })
    }
}