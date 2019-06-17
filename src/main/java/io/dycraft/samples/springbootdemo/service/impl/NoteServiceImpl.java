package io.dycraft.samples.springbootdemo.service.impl;

import io.dycraft.samples.springbootdemo.exception.ResourceNotFoundException;
import io.dycraft.samples.springbootdemo.model.Note;
import io.dycraft.samples.springbootdemo.repository.NoteRepository;
import io.dycraft.samples.springbootdemo.service.NoteService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Dayang Li on 12/06/2019
 */
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public List<Note> listByUserId(Long userId) {
        return noteRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<Note> getById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public Note create(Note note) {
        note.setId(null);
        return noteRepository.save(note);
    }

    @Override
    public Note update(Note note) {
        noteRepository.findById(note.getId())
            .orElseThrow(() -> new ResourceNotFoundException("note", note.getId()));
        return noteRepository.save(note);
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}
