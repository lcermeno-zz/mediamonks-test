package com.qiubo.mediamonks.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.qiubo.mediamonks.R
import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.model.domain.GetMediaUseCase
import com.qiubo.mediamonks.presenter.MainPresenter
import com.qiubo.mediamonks.ui.adapters.AlbumAdapter
import com.qiubo.mediamonks.view.IMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView {
    private val mPresenter by lazy { MainPresenter(this, GetMediaUseCase()) }
    private val mAdapter by lazy { AlbumAdapter(mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecycler.layoutManager = GridLayoutManager(this, 2)
        mainRecycler.adapter = mAdapter

        mainRefreshLayout.setOnRefreshListener { mPresenter.getAllAlbum() }

        mPresenter.getAllAlbum()
    }

    override fun onGetItems(items: List<Album>) {
        mAdapter.setItems(items)
        mainRefreshLayout.isRefreshing = false
    }

    override fun onError(message: String) {
        Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show()
    }

}
