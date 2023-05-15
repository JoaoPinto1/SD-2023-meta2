package com.example.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // This indicates that any properties not bound in this type should be
// ignored.
public record HackerNewsUser(
        String about, // The item's unique id.
        Long created, // true if the item is deleted.
        String id, // The type of item. One of "job", "story", "comment", "poll", or "pollopt".
        Integer karma, // The username of the item's author.
        List<Integer> submitted // Creation date of the item, in Unix Time.
) {
}
