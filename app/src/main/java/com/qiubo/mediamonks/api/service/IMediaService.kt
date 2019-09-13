package com.qiubo.mediamonks.api.service

import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.entities.Photo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface IMediaService {
    @GET("albums/")
    fun getAlbums(): Observable<List<Album>>

    @GET("albums/{albumId}/photos")
    fun getPhotos(@Path("albumId") albumId: String): Observable<List<Photo>>
}