package ru.zigreyn.academyhm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment


class MovieListFragment : Fragment() {

    private var movieListClickListener: MovieListClickListener? = null
    private var items:ArrayList<Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            items = it.getParcelableArrayList(ITEMS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = view.findViewById<View>(R.id.movie1)


        item.setOnClickListener {
            movieListClickListener?.onItemClick()
        }

        val like = item.findViewById<ToggleButton>(R.id.addFavoriteButton)
        like.isChecked = items!![0].isLiked

        like.setOnClickListener {
            movieListClickListener?.onItemLikeClick(like.isChecked)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieListClickListener) {
            movieListClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        movieListClickListener = null
    }

    interface MovieListClickListener {
        fun onItemClick()

        fun onItemLikeClick(isLiked: Boolean)
    }


    companion object {
        @JvmStatic
        fun newInstance(items: ArrayList<Movie>) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ITEMS, items)
                }
            }

        private const val ITEMS = "items"
    }
}