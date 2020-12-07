package ru.zigreyn.academyhm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.zigreyn.academyhm.adapter.MovieListAdapter
import ru.zigreyn.academyhm.data.MovieSample
import ru.zigreyn.academyhm.model.Movie


class MovieListFragment : Fragment() {

    private var items: List<Movie>? = null
    private var recycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items = MovieSample.movies
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    fun initRecycler() {
        recycler = view?.findViewById(R.id.movie_list_rv)
        recycler?.adapter = items?.let { MovieListAdapter(it) { actor -> doOnClick(actor) } }
        recycler?.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
    }

    private fun doOnClick(movie: Movie) {
        recycler?.let {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailsFragment.newInstance(movie))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(MovieDetailsFragment::class.simpleName)
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }
}