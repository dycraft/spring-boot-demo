package io.dycraft.samples.springbootdemo.dto;

import io.dycraft.samples.springbootdemo.model.Note;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Dayang Li on 12/06/2019
 */
@Data
public class NoteRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public Note toEntity(Long userId) {
        Note note = new Note();
        note.setUserId(userId);
        note.setTitle(title);
        note.setContent(content);
        return note;
    }

    public Note toEntity(Long userId, Long id) {
        Note note = new Note();
        note.setId(id);
        note.setUserId(userId);
        note.setTitle(title);
        note.setContent(content);
        return note;
    }
}
