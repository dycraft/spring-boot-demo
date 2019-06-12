package io.dycraft.samples.springbootdemo.service;

import io.dycraft.samples.springbootdemo.model.Note;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Dayang Li on 12/06/2019
 */
public interface NoteService {

    List<Note> list();

    Optional<Note> getById(Long id);

    Note create(Note note);

    Note update(Note note);

    void delete(Long id);
}
