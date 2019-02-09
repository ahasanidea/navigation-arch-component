package com.ahasanidea.navcom.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ahasanidea.navcom.R
import com.ahasanidea.navcom.data.Note
import com.ahasanidea.navcom.fragments.NoteListFragmentDirections
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(val noteList: List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return noteList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteList[position]
        holder.bind(note)
    }


    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        fun bind(note: Note) {
            item.noteId.text = note.id.toString()
            item.noteText.text = note.text
            item.setOnClickListener {
                val direction = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(note.id)
                it.findNavController().navigate(direction)
            }
        }
    }
}