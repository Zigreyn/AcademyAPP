package ru.zigreyn.academyhm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ru.zigreyn.academyhm.R
import ru.zigreyn.academyhm.model.Actor

class ActorListAdapter(private var items: List<Actor>) : RecyclerView.Adapter<ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.actor_item, parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) =
        holder.bind(items[position])

    fun updateData(newData: List<Actor>) {
        val diffResult = DiffUtil.calculateDiff(ActorDiffUtilCallback(items, newData))
        items = newData
        diffResult.dispatchUpdatesTo(this)
    }
}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val photoView = itemView.findViewById<ImageView>(R.id.actor_iv)
    private val actorNameView = itemView.findViewById<TextView>(R.id.actor_name)

    fun bind(item: Actor) {
        Glide.with(photoView.context)
            .load(item.imageUrl)
            .apply(
                RequestOptions().placeholder(R.drawable.ic_placeholder)
            )
            .into(photoView)
        actorNameView.text = item.name
    }

}

private class ActorDiffUtilCallback(
    private val oldListMovies: List<Actor>,
    private val newListMovies: List<Actor>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldListMovies.size

    override fun getNewListSize() = newListMovies.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldListMovies[oldItemPosition] == newListMovies[newItemPosition]

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldListMovies[oldItemPosition].id == newListMovies[newItemPosition].id
}
