package com.example.easynotes.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.easynotes.entity.Note;
import com.example.easynotes.entity.SubNote;
import com.example.easynotes.exception.NoteException;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.repository.SubNoteRepository;

@Repository
@Transactional
public class NoteImpl implements NoteRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private SubNoteRepository subRepo;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Note saveNote(Note note) throws NoteException {
		entityManager.persist(note);
		
		SubNote subNote = new SubNote();
		subNote.setTitle("SubNote");
		subNote.setContent("SubContent");
		subRepo.saveNote(subNote);
		return note;

	}

	@Override
	public Note getNote(Long noteId) throws NoteException {
		return entityManager.find(Note.class, noteId);
	}

	@Override
	public void addAllNotes(List<Note> noteList) throws NoteException {
		entityManager.persist(noteList);
	}

	@Override
	public void deleteNote(Long id) throws NoteException {
		Note emp = getNote(id);
		if (emp == null) {
			throw new NoteException("Note with id: " + id + " is not found in the DB");
		} else {
			entityManager.remove(emp);
		}
	}

	@Override
	public void updateNote(Long id, Note note) throws NoteException {
		Note updatedNote = getNote(id);
		if (updatedNote == null) {
			throw new NoteException("Note not found with id: " + note.getId());
		} else {
			updatedNote.setTitle(note.getTitle());
			updatedNote.setContent(note.getContent());

			entityManager.merge(updatedNote);
		}
	}

	@Override
	public List<Note> getAllNotes() throws NoteException {
		String query = "SELECT w FROM Note w";

		List<Note> notes = null;
		

		notes = entityManager.createQuery(query, Note.class).getResultList();
		
		return notes;
	}

}
