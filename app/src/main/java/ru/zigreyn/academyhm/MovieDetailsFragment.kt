package ru.zigreyn.academyhm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MovieDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // TODO add Movie support
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(move: Movie) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    // TODO add Movie support
                }
            }
    }
}