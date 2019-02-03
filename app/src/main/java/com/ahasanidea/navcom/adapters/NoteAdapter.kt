package com.ahasanidea.navcom.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahasanidea.navcom.R
import com.ahasanidea.navcom.data.Note

class NoteAdapter(val noteList:List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item=LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return noteList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteList[position]
        holder.id.text = note.id.toString()
        holder.text.text = note.text
    }

    class ViewHolder(item:View) :RecyclerView.ViewHolder(item)  {
        val id: TextView = item.findViewById(R.id.noteId)
        val text: TextView = item.findViewById(R.id.noteText)
    }
}