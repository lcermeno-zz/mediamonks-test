package com.qiubo.mediamonks.model.repository

import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.entities.Photo
import io.reactivex.Observable

interface IMediaRepository {
    fun getAlbums(limit: Int, page: Int): Observable<List<Album>>
    fun getPhotos(albumId: String, limit: Int, page: Int): Observable<List<Photo>>
}