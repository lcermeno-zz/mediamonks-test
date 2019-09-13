package com.qiubo.mediamonks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qiubo.mediamonks.R
import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.model.domain.GetMediaUseCase
import com.qiubo.mediamonks.presenter.MainPresenter
import com.qiubo.mediamonks.view.IMainView

class MainActivity : AppCompatActivity(), IMainView {
    private val mPresenter by lazy { MainPresenter(this, GetMediaUseCase()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter.getAllAlbum()
    }

    override fun onGetItems(items: List<Album>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
