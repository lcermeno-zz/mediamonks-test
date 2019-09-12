package com.qiubo.mediamonks.model.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMediaService {
    @GET("albums/")
    fun getAlbums(): Call<List<Any>>

    @GET("albums/{albumId}/photos/{photoId}")
    fun getPhotos(@Path("albumId") albumId: String, @Path("photoId") photoId: String = ""): Call<List<Any>>
}