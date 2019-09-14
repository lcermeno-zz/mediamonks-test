package com.qiubo.mediamonks.model.domain

import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.entities.Photo
import com.qiubo.mediamonks.misc.Constants
import com.qiubo.mediamonks.model.repository.MediaRepository
import io.reactivex.Observable


class GetMediaUseCase {

    private val mRepository by lazy { MediaRepository() }

    fun getAll(page: Int): Observable<List<Album>> = mRepository.getAlbums(Constants.PAGE_LIMIT, page)
    fun getPhotos(id: Int, page: Int): Observable<List<Photo>> =
        mRepository.getPhotos(id.toString(), Constants.PAGE_LIMIT, page)

}