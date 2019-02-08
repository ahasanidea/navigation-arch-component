package com.ahasanidea.navcom.viewmodels


import androidx.core.widget.ListViewAutoScrollHelper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahasanidea.navcom.data.Note
import com.ahasanidea.navcom.data.NotesManager
import java.lang.IllegalArgumentException

class EditNoteViewModel() : ViewModel() {
    private val currentNote = MutableLiveData<Note>()
    private val editStatus = MutableLiveData<Boolean>()

    val observableCurrentNote:LiveData<Note>
    get() = currentNote
    val observableEditStatus:LiveData<Boolean>
    get() = editStatus
    //edit note
    fun editNote(id:Int,noteText:String){
        editStatus.value=try {
            NotesManager.editNote(id,noteText)
            true
        }catch (e:IllegalArgumentException){
            false
        }
    }
    //initialize current note
    fun initNote(id:Int){
        currentNote.value=NotesManager.getNote(id)
    }

}