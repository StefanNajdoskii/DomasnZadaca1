package com.stefan.domasnzadaca1


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stefan.domasnzadaca1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val tagsList = mutableListOf(
        Pair("AndroidFP", "Android tutorials"),
        Pair("Deitel", "Deitel programming"),
        Pair("Google", "Google news"),
        Pair("iPhoneFP", "iPhone tutorials"),
        Pair("JavaFP", "Java programming"),
        Pair("JavaHTP", "Java how to program")
    )

    private lateinit var adapter: TagAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TagAdapter(
            tags = tagsList,
            onTagClick = { query ->
                Toast.makeText(this, "Searching: $query", Toast.LENGTH_SHORT).show()
            },
            onEditClick = { position ->
                showEditDialog(position)
            }
        )

        binding.tagsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.tagsRecyclerView.adapter = adapter

        binding.saveButton.setOnClickListener {
            val query = binding.queryEditText.text.toString().trim()
            val tag = binding.tagEditText.text.toString().trim()

            if (query.isEmpty() || tag.isEmpty()) {
                Toast.makeText(this, "Внеси query и tag!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            tagsList.add(Pair(tag, query))
            adapter.notifyItemInserted(tagsList.size - 1)

            binding.queryEditText.text?.clear()
            binding.tagEditText.text?.clear()
        }

        binding.clearButton.setOnClickListener {
            tagsList.clear()
            adapter.notifyDataSetChanged()
        }
    }

    private fun showEditDialog(position: Int) {
        val (currentTag, currentQuery) = tagsList[position]

        AlertDialog.Builder(this)
            .setTitle("Edit Tag")
            .setMessage("Tag: $currentTag\nQuery: $currentQuery")
            .setPositiveButton("Delete") { _, _ ->
                tagsList.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}