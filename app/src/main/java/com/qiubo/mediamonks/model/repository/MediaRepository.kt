package com.qiubo.mediamonks.model.repository

import com.qiubo.mediamonks.api.HttpClientFactory
import com.qiubo.mediamonks.api.service.IMediaService
import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.entities.Photo
import io.reactivex.Observable

class MediaRepository : IMediaRepository {
    override fun getAlbums(limit: Int, page: Int): Observable<List<Album>> {
        return HttpClientFactory.getService(IMediaService::class.java).getAlbums(limit, page)
    }

    override fun getPhotos(albumId: String, limit: Int, page: Int): Observable<List<Photo>> {
        return HttpClientFactory.getService(IMediaService::class.java)
            .getPhotos(albumId, limit, page)
    }
}