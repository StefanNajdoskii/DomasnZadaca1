package com.stefan.domasnzadaca1


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.stefan.domasnzadaca1.R

class TagAdapter(
    private val tags: MutableList<Pair<String, String>>,
    private val onTagClick: (String) -> Unit,
    private val onEditClick: (Int) -> Unit
) : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    inner class TagViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tagButton: Button = view.findViewById(R.id.tagButton)
        val editButton: Button = view.findViewById(R.id.editButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tag, parent, false)
        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val (tag, query) = tags[position]
        holder.tagButton.text = tag
        holder.tagButton.setOnClickListener { onTagClick(query) }
        holder.editButton.setOnClickListener { onEditClick(position) }
    }

    override fun getItemCount() = tags.size
}