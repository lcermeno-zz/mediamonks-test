package com.qiubo.mediamonks.entities

data class Album(val id: Int, val userId: Int, val title: String)
data class Photo(val id: Int, val title: String, val url: String, val thumbnailUrl: String)