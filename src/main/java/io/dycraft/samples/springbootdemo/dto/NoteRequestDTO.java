package io.dycraft.samples.springbootdemo.dto;

import io.dycraft.samples.springbootdemo.model.Note;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dayang Li on 12/06/2019
 */
@Getter
@Setter
public class NoteRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public Note toEntity() {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        return note;
    }

    public Note toEntity(Long id) {
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        return note;
    }
}
