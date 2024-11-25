package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings;
    private int lastId;

    public WiseSayingRepository() {
        this.wiseSayings = new ArrayList<>();
        this.lastId = 1;
    }

    public WiseSaying saveWiseSaying(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(lastId++, content, author);
        wiseSayings.add(wiseSaying);
        return wiseSaying;
    }


    public List<WiseSaying> findAllWiseSaying() {
        return wiseSayings;
    }

    public boolean remove(int id) {
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
    }

    public void modifyWiseSaying(WiseSaying foundWiseSaying, String content, String author) {
        foundWiseSaying.updateWiseSaying(content, author);
    }

    public Optional<WiseSaying> findWiseSayingById(int id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst();
    }
}
