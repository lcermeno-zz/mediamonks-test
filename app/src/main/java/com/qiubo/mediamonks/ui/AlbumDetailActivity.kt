package com.qiubo.mediamonks.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.qiubo.mediamonks.R
import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.entities.Photo
import com.qiubo.mediamonks.misc.Constants
import com.qiubo.mediamonks.misc.ScreenHelper
import com.qiubo.mediamonks.model.domain.GetMediaUseCase
import com.qiubo.mediamonks.presenter.AlbumDetailPresenter
import com.qiubo.mediamonks.presenter.IAlbumDetailPresenter
import com.qiubo.mediamonks.ui.adapters.PhotosAdapter
import com.qiubo.mediamonks.view.IAlbumDetailView
import kotlinx.android.synthetic.main.activity_album_detail.*

class AlbumDetailActivity : AppCompatActivity(), IAlbumDetailView, PhotosAdapter.IOnClickListener {


    private val mAdapter by lazy { PhotosAdapter(mutableListOf(), this) }
    private lateinit var mPresenter: IAlbumDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        albumDetailRecycler.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        albumDetailRecycler.adapter = mAdapter

        albumDetailRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                mPresenter.loadMoreItems(!albumDetailRecycler.canScrollVertically(1))
            }
        })

        albumDetailRefresh.setOnRefreshListener { mPresenter.getPhotos() }

        val album = intent.getParcelableExtra<Album>(Constants.ITEM_KEY)

        val width = ScreenHelper.getScreenRealWidth(this)

        albumDetailRefresh.isRefreshing = true
        mPresenter = AlbumDetailPresenter(this, GetMediaUseCase(), album, width)
        mPresenter.getPhotos()
    }

    override fun onError(message: String) {
        albumDetailRefresh.isRefreshing = false
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onClickItem(
        item: Photo,
        pair: Pair<View, String>
    ) {
        val intent = Intent(this, FullscreenActivity::class.java)
        intent.putExtra(Constants.ITEM_KEY, item)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            startActivity(intent, optionsCompat.toBundle())
        else
            startActivity(intent)
    }

    override fun onGetItems(items: List<Photo>) {
        mAdapter.setItems(items)
        albumDetailRefresh.isRefreshing = false
    }

    override fun onLoadMore(items: MutableList<Photo>) {
        mAdapter.loadMore(items)
    }
}
