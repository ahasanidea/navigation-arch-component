package com.ahasanidea.navcom.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.ahasanidea.navcom.R
import com.ahasanidea.navcom.data.Note
import com.ahasanidea.navcom.fragments.NoteDetailFragmentArgs.fromBundle
import com.ahasanidea.navcom.viewmodels.NoteDetailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_note_detail.*


/**
 * A simple [Fragment] subclass.
 *
 */
class NoteDetailFragment : Fragment() {
    private lateinit var viewModel:NoteDetailViewModel
    private val noteId by lazy {
        fromBundle(arguments!!).noteId
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteDetailViewModel::class.java)
        viewModel.getNote(noteId).observe(this, Observer { note ->
            note?.let { render(note) } ?: renderNoteNotFound()
        })
    }
    private fun render(note: Note) {
        noteIdView.text = String.format(getString(R.string.id_s), note.id)
        noteText.text = String.format(getString(R.string.text_s), note.text)
    }

    private fun renderNoteNotFound() {
        noteIdView.visibility = View.GONE
        noteText.visibility = View.GONE
        view?.let {
            Snackbar.make(it, "Error", Snackbar.LENGTH_LONG).show()
        }
    }


}
