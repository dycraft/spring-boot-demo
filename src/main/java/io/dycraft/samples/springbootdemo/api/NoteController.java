package io.dycraft.samples.springbootdemo.api;

import io.dycraft.samples.springbootdemo.dto.NoteRequestDTO;
import io.dycraft.samples.springbootdemo.dto.NoteResponseDTO;
import io.dycraft.samples.springbootdemo.exception.ResourceNotFoundException;
import io.dycraft.samples.springbootdemo.service.NoteService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dayang Li on 12/06/2019
 */
@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    List<NoteResponseDTO> listNotes() {
        return noteService.list().stream().map(NoteResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    NoteResponseDTO getNoteById(@PathVariable Long id) {
        return new NoteResponseDTO(noteService.getById(id)
            .orElseThrow(() -> new ResourceNotFoundException("note", id.toString())));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void addNote(@Valid @RequestBody NoteRequestDTO noteRequestDTO) {
        noteService.create(noteRequestDTO.toEntity());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateNote(@PathVariable Long id, @Valid @RequestBody NoteRequestDTO noteRequestDTO) {
        noteService.update(noteRequestDTO.toEntity(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeNote(@PathVariable Long id) {
        noteService.delete(id);
    }
}
