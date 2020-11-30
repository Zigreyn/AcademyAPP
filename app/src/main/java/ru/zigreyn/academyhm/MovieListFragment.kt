package ru.zigreyn.academyhm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class MovieListFragment : Fragment() {

    private var items: ArrayList<Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items = arrayListOf(
            Movie("test1", false),
            Movie("test2", true),
            Movie("test3", false)
        )
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
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailsFragment.newInstance(items!![0]))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(MovieDetailsFragment::class.simpleName)
                .commit()
        }

        val like = item.findViewById<ToggleButton>(R.id.addFavoriteButton)

        like.setOnClickListener {
            items!![0].isLiked = like.isChecked
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }
}