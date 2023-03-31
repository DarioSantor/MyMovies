package com.example.mymoviesapp.api

import com.example.mymoviesapp.data.MyMoviesData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("popular?api_key=08b18442475b89bc5a0ea411b6e6f762&language=en-US&page=1")
    suspend fun getData(): Response<MyMoviesData>
}