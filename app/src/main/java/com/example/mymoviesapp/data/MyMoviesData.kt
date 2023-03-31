package com.example.mymoviesapp.data

data class MyMoviesData(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)