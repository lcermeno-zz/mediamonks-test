package com.qiubo.mediamonks.api.service

import com.qiubo.mediamonks.entities.Album
import com.qiubo.mediamonks.entities.Photo
import com.qiubo.mediamonks.misc.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMediaService {
    @GET("albums/")
    fun getAlbums(@Query(Constants.LIMIT_KEY) limit: Int, @Query(Constants.PAGE_KEY) page: Int): Observable<List<Album>>

    @GET("albums/{${Constants.ALBUM_ID_KEY}}/photos")
    fun getPhotos(
        @Path(Constants.ALBUM_ID_KEY) albumId: String,
        @Query(Constants.LIMIT_KEY) limit: Int,
        @Query(Constants.PAGE_KEY) page: Int
    ): Observable<List<Photo>>
}