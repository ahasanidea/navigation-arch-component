package com.ahasanidea.navcom.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahasanidea.navcom.data.Note
import com.ahasanidea.navcom.data.NotesManager

class NoteDetailViewModel : ViewModel() {
    //Encapsulation
    private val note = MutableLiveData<Note>()
    //publicly exposed LiveData, not mutable
    val onservableNote: LiveData<Note>
        get() = note

    fun getNote(id: Int){
        note.value = NotesManager.getNote(id)
    }
}