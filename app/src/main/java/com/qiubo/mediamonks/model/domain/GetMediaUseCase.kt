package com.qiubo.mediamonks.model.domain

import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.model.repository.MediaRepository
import io.reactivex.Observable


class GetMediaUseCase {

    private val mRepository by lazy { MediaRepository() }

    fun getAll(): Observable<List<Album>> = mRepository.getAlbums()

}