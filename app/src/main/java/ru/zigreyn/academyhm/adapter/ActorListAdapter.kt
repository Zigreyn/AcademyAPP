package ru.zigreyn.academyhm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.zigreyn.academyhm.R
import ru.zigreyn.academyhm.model.Actor

class ActorListAdapter(private val items: List<Actor>) : RecyclerView.Adapter<ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.actor_item, parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) =
        holder.bind(items[position])
}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val photoView = itemView.findViewById<ImageView>(R.id.actor_iv)
    private val actorNameView = itemView.findViewById<TextView>(R.id.actor_name)

    fun bind(item: Actor) {
        photoView.setImageResource(item.photoId)
        actorNameView.text = item.name
    }
}
