package com.qiubo.mediamonks.model.repository

import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.entities.Photo
import io.reactivex.Observable

interface IMediaRepository {
    fun getAlbums(): Observable<List<Album>>
    fun getPhotos(albumId: String, photoId: String = ""): Observable<List<Photo>>
}