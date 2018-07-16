package com.example.easynotes.repository;

import java.util.List;

import com.example.easynotes.entity.Note;
import com.example.easynotes.exception.NoteException;



public interface NoteRepository  {
	
	public Note saveNote(Note note) throws NoteException;

	public Note getNote(Long noteId) throws NoteException;
	
	public List<Note> getAllNotes() throws NoteException;

	public void addAllNotes(List<Note> noteList) throws NoteException;

	public void deleteNote(Long id) throws NoteException;

	public void updateNote(Long id, Note note) throws NoteException;

}
