package com.example.mymoviesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mymoviesapp.data.MoviesAdapter
import com.example.mymoviesapp.databinding.FragmentListBinding
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import com.example.mymoviesapp.R
import com.example.mymoviesapp.viewModel.ItemViewModel
import kotlinx.coroutines.Dispatchers

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private var adapter: MoviesAdapter? = null
    private lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ItemViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
        adapter = MoviesAdapter(requireContext()) {
            viewModel.movie = it
            findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }
        binding.rvMoviesList.adapter = adapter

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.moviesFlow.collect {
                adapter?.items = it.body()?.results?: emptyList()
            }
        }
    }
}




