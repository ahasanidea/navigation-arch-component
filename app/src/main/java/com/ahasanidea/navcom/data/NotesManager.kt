package com.ahasanidea.navcom.data

import java.lang.IllegalStateException

/**
 * This is quick and dirty implementation.
 */
object NotesManager {
    private val notes= mutableMapOf<Int,String>()

    //Get all notes
    fun getNoteList():List<Note> = notes.map { Note(id = it.key, text = it.value) }

    //Get note by id
    fun getNote(id:Int):Note? = notes
        .filter { it.key == id }
        .map { Note(it.key,it.value) }
        .firstOrNull()

    //Add note
    fun addNote(noteText:String){
        validateText(noteText)
        val nextId=getNextId()
        notes[nextId]=noteText
    }

    private fun getNextId():Int = notes.count() +1

    private fun validateText(noteText: String) {
        require(noteText.isNotBlank()){"Note text cannot blank"}
    }
    //Edit note
    fun editNote(id:Int,noteText:String){
        validateText(noteText)
        notes[id]=noteText
    }
    //Delete note
    fun deleteNote(id:Int)= notes.remove(id)?:throw IllegalStateException("Note was note removed")

}