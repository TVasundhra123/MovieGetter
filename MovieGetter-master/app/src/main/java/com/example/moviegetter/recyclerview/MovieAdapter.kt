package com.example.moviegetter.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviegetter.R

class MoviesAdapter(private val moviesData: MutableList<Movies>, private val listener: MoviesItemClicked): RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val data = moviesData[position]
        holder.bind(data)
        holder.imageView.setOnClickListener {
            listener.onMovieItemClicked(data)
        }
        holder.btn.setOnClickListener {
            listener.onMovieItemClicked(data)
        }

    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    // Basically setData() tells adapter that dataset has changed and now again call getItemCount()-> onCreateViewHolder() -> onBindViewHolder()
    fun setData(movies: List<Movies>?) {
        moviesData.clear()
        if(movies!= null) moviesData.addAll(movies)
        //Log.d("VASU", "Transformer fragment called notify data changed")
        notifyDataSetChanged()
    }
}

class MoviesViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {

    private val titleView: TextView = itemView.findViewById(R.id.title)
    val imageView: ImageView = itemView.findViewById(R.id.image)
    private val year: TextView = itemView.findViewById(R.id.year)
    private val type: TextView = itemView.findViewById(R.id.type)
    val btn: Button = itemView.findViewById(R.id.button2)

    fun bind(data: Movies) {
        titleView.text=data.Title
        Glide.with(itemView.context).load(data.Poster).into(imageView)
        year.text = data.Year
        type.text = data.Type
    }


}

interface MoviesItemClicked{
    fun onMovieItemClicked(item: Movies)
}

