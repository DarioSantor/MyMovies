package com.example.mymoviesapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoviesapp.api.RetrofitInstance
import com.example.mymoviesapp.data.MyMoviesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class ItemViewModel : ViewModel() {
    var moviesFlow = MutableSharedFlow<Response<MyMoviesData>>()
    var movie: com.example.mymoviesapp.data.Result? = null

    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            moviesFlow.emit(RetrofitInstance.api.getData())
        }
    }
}