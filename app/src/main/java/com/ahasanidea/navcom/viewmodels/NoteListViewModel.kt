package com.ahasanidea.navcom.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.ahasanidea.navcom.data.Note
import com.ahasanidea.navcom.data.NotesManager

class NoteListViewModel : ViewModel() {
    private val noteList=MutableLiveData<List<Note>>()
    val observableNoteList:LiveData<List<Note>>
    get()=noteList
    init {
        load()
    }
     fun load(){
        noteList.value=NotesManager.getNoteList()
    }
}
