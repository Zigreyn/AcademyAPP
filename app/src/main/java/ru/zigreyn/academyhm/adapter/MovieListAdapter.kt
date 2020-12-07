package ru.zigreyn.academyhm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import ru.zigreyn.academyhm.R
import ru.zigreyn.academyhm.model.Movie

class MovieListAdapter(private val items: List<Movie>, private val clickListener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            clickListener(items[position])
        }
    }

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val poster = itemView.findViewById<ImageView>(R.id.poster_iv)
    private val minAge = itemView.findViewById<TextView>(R.id.movie_pg_tw)
    private val movieName = itemView.findViewById<TextView>(R.id.movie_name_tw)
    private val reviewsCount = itemView.findViewById<TextView>(R.id.reviews_tv)
    private val genre = itemView.findViewById<TextView>(R.id.genre_tv)
    private val duration = itemView.findViewById<TextView>(R.id.duration_tv)
    private val like = itemView.findViewById<ToggleButton>(R.id.addFavoriteButton)

    private val ratingBar = listOf<ImageView>(
        itemView.findViewById(R.id.star_1),
        itemView.findViewById(R.id.star_2),
        itemView.findViewById(R.id.star_3),
        itemView.findViewById(R.id.star_4),
        itemView.findViewById(R.id.star_5)
    )

    fun bind(item: Movie) {
        poster.setImageResource(item.posterId)
        minAge.text = item.minAge
        like.isChecked = item.isLiked
        movieName.text = item.movieName
        genre.text = item.genre

        val mins = "${item.duration} min"
        duration.text = mins

        val reviews = "${item.reviewsCount} reviews"
        reviewsCount.text = reviews
        setRating(item.rating)
    }

    private fun setRating(rating: Int) {
        for (i: Int in ratingBar.indices) {
            if (i <= rating - 1) {
                ratingBar[i].setImageResource(R.drawable.ic_star_enable)
            } else {
                ratingBar[i].setImageResource(R.drawable.ic_star_unable)
            }
        }
    }
}
