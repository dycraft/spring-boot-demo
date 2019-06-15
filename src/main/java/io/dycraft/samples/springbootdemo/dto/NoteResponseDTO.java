package io.dycraft.samples.springbootdemo.dto;

import io.dycraft.samples.springbootdemo.model.Note;
import java.util.Date;
import lombok.Data;

/**
 * @author Dayang Li on 12/06/2019
 */
@Data
public class NoteResponseDTO {

    private Long id;

    private Long userId;

    private String title;

    private String content;

    private Date createdAt;

    public NoteResponseDTO(Note note) {
        this.id = note.getId();
        this.userId = note.getUserId();
        this.title = note.getTitle();
        this.content = note.getContent();
        this.createdAt = note.getCreatedAt();
    }
}
