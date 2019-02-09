package com.ahasanidea.navcom.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.ahasanidea.navcom.R
import com.ahasanidea.navcom.adapters.NoteAdapter
import com.ahasanidea.navcom.data.Note
import com.ahasanidea.navcom.viewmodels.NoteListViewModel
import kotlinx.android.synthetic.main.note_list_fragment.*


class NoteListFragment : Fragment() {

private lateinit var viewModel:NoteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProviders.of(this).get(NoteListViewModel::class.java)
        viewModel.observableNoteList.observe(this, Observer { notes-> notes?.let { render(notes) } })

        fab.setOnClickListener{
            it.findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }
    }

    private fun render(notes: List<Note>?) {
        if (notes!!.isEmpty()) {
            notesRecyclerView.visibility = View.GONE
            notesNotFound.visibility = View.VISIBLE
        } else {
            setupRecyclerView(notes)
            notesRecyclerView.visibility = View.VISIBLE
            notesNotFound.visibility = View.GONE
        }
    }

    private fun setupRecyclerView(notes: List<Note>) {
        notesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        notesRecyclerView.adapter = NoteAdapter(notes)
        notesRecyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }


}
