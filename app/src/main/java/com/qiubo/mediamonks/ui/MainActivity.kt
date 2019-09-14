package com.qiubo.mediamonks.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qiubo.mediamonks.R
import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.misc.Constants
import com.qiubo.mediamonks.model.domain.GetMediaUseCase
import com.qiubo.mediamonks.presenter.MainPresenter
import com.qiubo.mediamonks.ui.adapters.AlbumAdapter
import com.qiubo.mediamonks.view.IMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView, AlbumAdapter.IOnClickListener {


    private val mPresenter by lazy { MainPresenter(this, GetMediaUseCase()) }
    private val mAdapter by lazy { AlbumAdapter(mutableListOf(), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecycler.layoutManager = GridLayoutManager(this, 2)
        mainRecycler.adapter = mAdapter

        mainRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                mPresenter.loadMoreItems(!mainRecycler.canScrollVertically(1))
            }
        })

        mainRefreshLayout.setOnRefreshListener { mPresenter.getAllAlbum() }

        mPresenter.getAllAlbum()
    }

    override fun onGetItems(items: List<Album>) {
        mAdapter.setItems(items)
        mainRefreshLayout.isRefreshing = false
    }

    override fun onLoadMore(items: MutableList<Album>) {
        mAdapter.loadMore(items)
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onClickItem(item: Album) {
        val intent = Intent(this, AlbumDetailActivity::class.java)
        intent.putExtra(Constants.ITEM_KEY, item)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

}
