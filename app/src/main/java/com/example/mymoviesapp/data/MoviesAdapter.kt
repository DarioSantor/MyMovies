package com.example.mymoviesapp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviesapp.R
import com.example.mymoviesapp.databinding.FragmentItemBinding

class MoviesAdapter(private val context: Context, val listener: (Result) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root)
    private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var items: List<Result>
        get() = differ.currentList
        set(value) { differ.submitList(value) }
    override fun getItemCount() = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val item = items[position]
            movieTitle.text = item.title
            movieTagline.text = item.overview
            movieRating.text = item.vote_average.toString()
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/original/${item.poster_path}")
                .into(moviePoster)
            root.setOnClickListener {
                listener(item)
                }
            }
        }
    }
