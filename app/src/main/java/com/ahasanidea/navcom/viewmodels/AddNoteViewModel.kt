package com.ahasanidea.navcom.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahasanidea.navcom.data.NotesManager
import java.lang.IllegalArgumentException

class AddNoteViewModel: ViewModel() {
    private val status=MutableLiveData<Boolean>()
    val observableStatus:LiveData<Boolean>
    get()=status
    fun addNote(noteText:String){
        status.value=try {
            NotesManager.addNote(noteText)
            true
        }
        catch (e:IllegalArgumentException){
            false
        }
    }
}