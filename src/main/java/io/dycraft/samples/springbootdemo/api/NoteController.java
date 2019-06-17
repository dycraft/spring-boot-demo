package io.dycraft.samples.springbootdemo.api;

import io.dycraft.samples.springbootdemo.dto.NoteRequestDTO;
import io.dycraft.samples.springbootdemo.dto.NoteResponseDTO;
import io.dycraft.samples.springbootdemo.exception.ResourceNotFoundException;
import io.dycraft.samples.springbootdemo.model.Note;
import io.dycraft.samples.springbootdemo.security.Identity;
import io.dycraft.samples.springbootdemo.service.NoteService;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dayang Li on 12/06/2019
 */
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    ResponseEntity listNotes(@AuthenticationPrincipal Identity identity) {
        return Response.ok(
            noteService.listByUserId(identity.getUserId()).stream()
                .map(NoteResponseDTO::new)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    ResponseEntity getNoteById(@PathVariable Long id) {
        return noteService.getById(id)
            .map(note -> Response.ok(new NoteResponseDTO(note)))
            .orElseThrow(() -> new ResourceNotFoundException("note", id.toString()));
    }

    @PostMapping
    ResponseEntity addNote(@AuthenticationPrincipal Identity identity,
        @Valid @RequestBody NoteRequestDTO noteRequestDTO) {
        Note note = noteService.create(noteRequestDTO.toEntity(identity.getUserId()));
        return Response.created(note.getId());
    }

    @PutMapping("/{id}")
    ResponseEntity updateNote(@AuthenticationPrincipal Identity identity,
        @PathVariable Long id, @Valid @RequestBody NoteRequestDTO noteRequestDTO) {
        noteService.update(noteRequestDTO.toEntity(identity.getUserId(), id));
        return Response.noContent();
    }

    @DeleteMapping("/{id}")
    ResponseEntity removeNote(@PathVariable Long id) {
        noteService.delete(id);
        return Response.noContent();
    }
}
