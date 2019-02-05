package com.ahasanidea.navcom.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.ahasanidea.navcom.R
import com.ahasanidea.navcom.utils.closeSoftKeyboard
import com.ahasanidea.navcom.viewmodels.AddNoteViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*


class AddNoteFragment : Fragment() {
 private lateinit var viewModel:AddNoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddNoteViewModel::class.java)
        viewModel.observableStatus.observe(this, Observer { status ->
            status?.let { render(status) }
        })
        addNoteText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.addNote(v.text.toString())
                v.closeSoftKeyboard()
                true
            } else {
                false
            }
        }

    }

    private fun render(status: Boolean?) {
        when(status){
            true->{
                view?.let {
                    it.findNavController().popBackStack()
                }
            }
            false -> addNoteText.error = "Invalid text"
        }

    }


}
