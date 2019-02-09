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

import com.ahasanidea.navcom.R
import com.ahasanidea.navcom.data.Note
import com.ahasanidea.navcom.fragments.DeleteNoteFragmentArgs.fromBundle
import com.ahasanidea.navcom.viewmodels.DeleteNoteViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_delete_note.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DeleteNoteFragment : Fragment() {

    private lateinit var viewModel: DeleteNoteViewModel

    private val noteId by lazy {
        fromBundle(arguments!!).noteId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProviders.of(this).get(DeleteNoteViewModel::class.java)
        viewModel.observableCurrentNote.observe(this, Observer {currentNote->
            currentNote?.let { initCurrentNote(currentNote) }
        })

        viewModel.observableDeleteStatus.observe(this, Observer { deleteStatus ->
            deleteStatus?.let { render(deleteStatus) }
        })

        cancelDeleteButton.setOnClickListener{
            it.findNavController().popBackStack()
        }

        confirmDeleteButton.setOnClickListener{
            viewModel.deleteNote(noteId)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.initNote(noteId)
    }

    private fun render(deleteStatus: Boolean?) {
        when(deleteStatus){
            true->{
                view?.let {
                    it.findNavController().popBackStack(R.id.noteListFragment,false)
                }
            }
            false -> Snackbar.make(confirmDeleteButton, "Error deleting note", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun initCurrentNote(note: Note) {
        noteIdView.text= String.format("id: %s",note.id)
        noteText.text= String.format("text: %s",note.text)
    }


}
