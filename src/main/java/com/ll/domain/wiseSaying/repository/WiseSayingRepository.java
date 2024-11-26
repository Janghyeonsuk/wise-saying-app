package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;

import java.util.List;
import java.util.Optional;

public interface WiseSayingRepository {

    WiseSaying add(WiseSaying wiseSaying);

    List<WiseSaying> findAllWiseSaying();

    boolean remove(int id);

    Optional<WiseSaying> findWiseSayingById(int id);

    void modifyWiseSaying(WiseSaying wiseSaying);
}
