package ru.zigreyn.academyhm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), MovieListFragment.MovieListClickListener {
    var items: ArrayList<Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = if (savedInstanceState!= null){
            savedInstanceState.getParcelableArrayList(ITEMS)
        }else{
              arrayListOf(Movie("test", false))
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieListFragment.newInstance(items!!)).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(ITEMS, items)
    }

    override fun onItemClick() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieDetailsFragment.newInstance(items!![0]))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(MovieDetailsFragment::class.simpleName)
            .commit()
    }

    override fun onItemLikeClick(isLiked: Boolean) {
        items!![0].isLiked = isLiked
    }

    companion object {
        private const val ITEMS: String = "items"
    }
}