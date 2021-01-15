package ru.zigreyn.academyhm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ru.zigreyn.academyhm.R
import ru.zigreyn.academyhm.model.Movie

class MovieListAdapter(private var items: List<Movie>, private val clickListener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            clickListener(items[position])
        }
    }

    fun updateData(newData: List<Movie>) {
        val diffResult = DiffUtil.calculateDiff(MoviesDiffUtilCallback(items, newData))
        items = newData
        diffResult.dispatchUpdatesTo(this)
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val posterView = itemView.findViewById<ImageView>(R.id.poster_iv)
    private val minAgeView = itemView.findViewById<TextView>(R.id.movie_pg_tw)
    private val movieNameView = itemView.findViewById<TextView>(R.id.movie_name_tw)
    private val reviewsCountView = itemView.findViewById<TextView>(R.id.reviews_tv)
    private val genreView = itemView.findViewById<TextView>(R.id.genre_tv)
    private val durationView = itemView.findViewById<TextView>(R.id.duration_tv)
    private val likeView = itemView.findViewById<ToggleButton>(R.id.addFavoriteButton)

    private val ratingBar = listOf<ImageView>(
        itemView.findViewById(R.id.star_1),
        itemView.findViewById(R.id.star_2),
        itemView.findViewById(R.id.star_3),
        itemView.findViewById(R.id.star_4),
        itemView.findViewById(R.id.star_5)
    )

    fun bind(item: Movie) {
        Glide.with(itemView.context)
            .load(item.imageUrl)
            .apply(
                RequestOptions().placeholder(R.drawable.ic_placeholder)
            )
            .into(posterView)

        minAgeView.text = item.pgAge.toString()
        likeView.isChecked = item.isLiked
        movieNameView.text = item.title
        genreView.text = item.genres.joinToString { it.name }
        val mins = "${item.runningTime} min"
        durationView.text = mins

        val reviews = "${item.reviewCount} reviews"
        reviewsCountView.text = reviews
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

private class MoviesDiffUtilCallback(
    private val oldListMovies: List<Movie>,
    private val newListMovies: List<Movie>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldListMovies.size

    override fun getNewListSize() = newListMovies.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldListMovies[oldItemPosition] == newListMovies[newItemPosition]

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldListMovies[oldItemPosition].id == newListMovies[newItemPosition].id
}
