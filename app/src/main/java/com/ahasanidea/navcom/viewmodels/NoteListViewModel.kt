package com.ahasanidea.navcom.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.ahasanidea.navcom.data.Note
import com.ahasanidea.navcom.data.NotesManager

class NoteListViewModel : ViewModel() {
    //Encapsulation
    private val noteList = MutableLiveData<List<Note>>()
    //publicly exposed LiveData, not mutable
    val observableNoteList: LiveData<List<Note>>
        get() = noteList

    init {
        load()
    }
    //load note list
    fun load() {
        noteList.value = NotesManager.getNoteList()
    }
}
