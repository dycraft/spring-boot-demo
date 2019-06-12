package io.dycraft.samples.springbootdemo.repository;

import io.dycraft.samples.springbootdemo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dayang Li on 12/06/2019
 */
public interface NoteRepository extends JpaRepository<Note, Long> {}
