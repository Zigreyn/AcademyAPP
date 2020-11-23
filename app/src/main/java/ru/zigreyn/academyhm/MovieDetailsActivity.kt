package ru.zigreyn.academyhm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
    }

    companion object{
        fun startMovieDetailsActivity(activity: AppCompatActivity){
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            activity.startActivity(intent)
        }
    }
}