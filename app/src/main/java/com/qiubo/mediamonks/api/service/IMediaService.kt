package com.qiubo.mediamonks.api.service

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface IMediaService {
    @GET("albums/")
    fun getAlbums(): Observable<List<Any>>

    @GET("albums/{albumId}/photos/{photoId}")
    fun getPhotos(@Path("albumId") albumId: String, @Path("photoId") photoId: String = ""): Observable<List<Any>>
}