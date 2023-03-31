package com.example.mymoviesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mymoviesapp.databinding.FragmentDetailBinding
import com.example.mymoviesapp.viewModel.ItemViewModel


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel = ViewModelProvider(requireActivity()).get(ItemViewModel::class.java)

        binding.apply {
            context?.let {
                Glide.with(it)
                    .load("https://image.tmdb.org/t/p/original/${itemViewModel.movie?.poster_path}")
                    .into(moviePoster)
            }
            movieTitle.text = itemViewModel.movie?.title
            movieRatingValue.text = itemViewModel.movie?.vote_average.toString()
            movieReleaseValue.text = itemViewModel.movie?.release_date
            movieOverviewValue.text = itemViewModel.movie?.overview
        }

    }
}

