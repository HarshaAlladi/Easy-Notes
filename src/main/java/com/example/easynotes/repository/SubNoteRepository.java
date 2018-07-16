package com.example.easynotes.repository;

import com.example.easynotes.entity.SubNote;
import com.example.easynotes.exception.NoteException;

public interface SubNoteRepository {

	public SubNote saveNote(SubNote note) throws NoteException;

}
