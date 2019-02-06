package com.ahasanidea.navcom.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahasanidea.navcom.data.Note
import com.ahasanidea.navcom.data.NotesManager

class NoteDetailViewModel : ViewModel() {
    private val note=MutableLiveData<Note>()
    fun getNote(id:Int):LiveData<Note>{
        note.value=NotesManager.getNote(id)
        return note
    }
}