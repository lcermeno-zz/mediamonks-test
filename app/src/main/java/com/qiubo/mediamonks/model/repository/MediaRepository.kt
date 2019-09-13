package com.qiubo.mediamonks.model.repository

import com.qiubo.mediamonks.api.HttpClientFactory
import com.qiubo.mediamonks.api.service.IMediaService
import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.entities.Photo
import io.reactivex.Observable

class MediaRepository : IMediaRepository {
    override fun getAlbums(): Observable<List<Album>> {
        return HttpClientFactory.getService(IMediaService::class.java).getAlbums()
    }

    override fun getPhotos(albumId: String): Observable<List<Photo>> {
        return HttpClientFactory.getService(IMediaService::class.java).getPhotos(albumId)
    }
}