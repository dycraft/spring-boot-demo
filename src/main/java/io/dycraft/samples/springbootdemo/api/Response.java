package io.dycraft.samples.springbootdemo.api;

import java.net.URI;
import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Dayang Li on 17/06/2019
 */
public class Response {

    public static ResponseEntity ok() {
        return ResponseEntity.ok().build();
    }

    public static <T> ResponseEntity ok(String key, String value) {
        return ResponseEntity.ok(Collections.singletonMap(key, value));
    }

    public static <T> ResponseEntity ok(T body) {
        return ResponseEntity.ok(body);
    }

    public static <T> ResponseEntity created(Object id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    public static <T> ResponseEntity noContent() {
        return ResponseEntity.noContent().build();
    }
}
