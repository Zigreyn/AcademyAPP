package ru.zigreyn.academyhm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ru.zigreyn.academyhm.adapter.MovieListAdapter
import ru.zigreyn.academyhm.data.loadMovies
import ru.zigreyn.academyhm.model.Movie


class MovieListFragment : Fragment() {

    private var items: List<Movie>? = listOf()
    private var recycler: RecyclerView? = null
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Default)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        loadMovieList(recycler?.adapter as MovieListAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        coroutineScope.cancel()
    }

    private fun initRecycler() {
        recycler = view?.findViewById(R.id.movie_list_rv)
        recycler?.adapter = items?.let { MovieListAdapter(it) { movie -> doOnClick(movie) } }
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

    private fun loadMovieList(adapter: MovieListAdapter) {
        coroutineScope.launch {
            val movies = loadMovies(requireContext())
            withContext(Dispatchers.Main) {
                items = movies
                adapter.updateData(movies)
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }
}