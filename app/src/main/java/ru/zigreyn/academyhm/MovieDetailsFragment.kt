package ru.zigreyn.academyhm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.zigreyn.academyhm.adapter.ActorListAdapter
import ru.zigreyn.academyhm.model.Movie

class MovieDetailsFragment : Fragment() {

    private var item: Movie? = null
    private var recycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable(ITEM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backBtn = view.findViewById<TextView>(R.id.back_btn)

        backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        item?.let { bind(it) }
        initRecycler()
    }

    private fun bind(item: Movie) {
        val posterView = view?.findViewById<ImageView>(R.id.header_iv)
        posterView?.let {
            Glide.with(requireContext())
                .load(item.imageUrl)
                .into(it)
        }

        val minAgeView = view?.findViewById<TextView>(R.id.movie_pg_tw)
        minAgeView?.text = item.pgAge.toString()

        val movieNameView = view?.findViewById<TextView>(R.id.movie_name_tw)
        movieNameView?.text = item.title

        val genreView = view?.findViewById<TextView>(R.id.genre_tv)
        genreView?.text = item.genres.joinToString { it.name }

        val reviewsView = view?.findViewById<TextView>(R.id.reviews_tv)
        val reviews = "${item.reviewCount} reviews"
        reviewsView?.text = reviews

        val storylineView = view?.findViewById<TextView>(R.id.storyline_desc_tv)
        storylineView?.text = item.storyLine

        val ratingView = view?.findViewById<RatingBar>(R.id.reviews_rb)
        ratingView?.rating = item.rating.toFloat()

        if (item.actors.isNotEmpty()) {
            initRecycler()
            (recycler?.adapter as ActorListAdapter).updateData(item.actors)
        } else {
            view?.findViewById<TextView>(R.id.cast_tv)?.visibility = View.GONE
        }

    }

    private fun initRecycler() {
        recycler = view?.findViewById(R.id.actor_list_rv)
        recycler?.adapter = item?.actors?.let { ActorListAdapter(it) }
        recycler?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    companion object {
        private const val ITEM: String = "item"

        @JvmStatic
        fun newInstance(movie: Movie) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ITEM, movie)
            }
        }

    }
}