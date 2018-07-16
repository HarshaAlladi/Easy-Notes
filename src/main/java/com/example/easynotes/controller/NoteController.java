package com.example.easynotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.entity.Note;
import com.example.easynotes.exception.NoteException;
import com.example.easynotes.repository.NoteRepository;

@RestController
@RequestMapping("/api")
public class NoteController {

	@Autowired
	NoteRepository noteRepository;

	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		Note savedNote = null;
		try {
			savedNote = noteRepository.saveNote(note);
		} catch (NoteException e) {
			e.printStackTrace();
		}
		return savedNote;
	}

	@PostMapping("/addNoteList")
	public ResponseEntity<?> addAllNotes(@Valid @RequestBody List<Note> noteList) {
		try {
			noteRepository.addAllNotes(noteList);
		} catch (NoteException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();

	}

	@GetMapping("/notesList")
	public List<Note> getAllNotes() {
		List<Note> notes = null;
		try {
			notes = noteRepository.getAllNotes();
		} catch (NoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}

	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
		Note note = null;
		try {
			note = noteRepository.getNote(noteId);
		} catch (NoteException e) {
			e.printStackTrace();
		}
		return note;
	}

	@PutMapping("/notes/{id}")
	public ResponseEntity<?> updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {
		try {
			noteRepository.updateNote(noteId, noteDetails);
		} catch (NoteException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {

		try {
			noteRepository.deleteNote(noteId);
		} catch (NoteException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok().build();
	}
}
