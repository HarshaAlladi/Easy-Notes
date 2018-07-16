package com.example.easynotes.repository.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.easynotes.entity.SubNote;
import com.example.easynotes.exception.NoteException;
import com.example.easynotes.repository.SubNoteRepository;

@Repository
@Transactional
public class SubNoteImpl implements SubNoteRepository {

	@Autowired
	private EntityManager entityManager;

	// change to required to rollback all transactions parent and child
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = NoteException.class)
	public SubNote saveNote(SubNote note) throws NoteException {
		if (note.getTitle().equalsIgnoreCase("SubNote")) {
			throw new NoteException();
		}
		entityManager.persist(note);
		return note;
	}

}
