package com.ahasanidea.navcom.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.ahasanidea.navcom.R
import com.ahasanidea.navcom.data.Note
import com.ahasanidea.navcom.fragments.EditNoteFragmentArgs.fromBundle
import com.ahasanidea.navcom.utils.closeSoftKeyboard
import com.ahasanidea.navcom.viewmodels.EditNoteViewModel
import kotlinx.android.synthetic.main.fragment_edit_note.*

/**
 * A simple [Fragment] subclass.
 *
 */
class EditNoteFragment : Fragment() {
    private lateinit var viewModel: EditNoteViewModel

    private val noteId by lazy {
        fromBundle(arguments!!).noteId
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(EditNoteViewModel::class.java)
        viewModel.observableCurrentNote.observe(this, Observer { currentNote ->
            currentNote?.let { initCurrentNote(currentNote) }
        })

        viewModel.observableEditStatus.observe(this, Observer { editStatus ->
            editStatus?.let { render(editStatus) }
        })

        setupEditNoteSubmitHandling()

    }

    override fun onResume() {
        super.onResume()
        viewModel.initNote(noteId)
    }

    private fun render(editStatus: Boolean) {
        when (editStatus) {
            true -> {
                view?.let {
                    Navigation.findNavController(it).popBackStack()
                }
            }
            false -> editNoteText.error = "Invalid text"
        }
    }

    private fun initCurrentNote(note: Note) {
        editNoteText.setText(note.text)
    }
    private fun setupEditNoteSubmitHandling() {
        editNoteText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val args = fromBundle(arguments!!)
                viewModel.editNote(noteId, v.text.toString())
                v.closeSoftKeyboard()
                true
            } else {
                false
            }
        }
    }


}
