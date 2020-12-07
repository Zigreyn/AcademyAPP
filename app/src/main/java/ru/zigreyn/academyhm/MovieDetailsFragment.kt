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
        val header = view?.findViewById<ImageView>(R.id.header_iv)
        header?.setImageResource(item.posterId)

        val minAge = view?.findViewById<TextView>(R.id.movie_pg_tw)
        minAge?.text = item.minAge

        val movieName = view?.findViewById<TextView>(R.id.movie_name_tw)
        movieName?.text = item.movieName

        val genre = view?.findViewById<TextView>(R.id.genre_tv)
        genre?.text = item.genre

        val reviewsView = view?.findViewById<TextView>(R.id.reviews_tv)
        val reviews = "${item.reviewsCount} reviews"
        reviewsView?.text = reviews

        val storyline = view?.findViewById<TextView>(R.id.storyline_desc_tv)
        storyline?.text = item.description

        val rating = view?.findViewById<RatingBar>(R.id.reviews_rb)
        rating?.rating = item.rating.toFloat()

    }

    private fun initRecycler() {
        recycler = view?.findViewById(R.id.actor_list_rv)
        recycler?.adapter = item?.cast?.let { ActorListAdapter(it) }
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