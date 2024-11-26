package com.ll.domain.wiseSaying.service;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;
import java.util.Optional;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    // 명언 저장
    public WiseSaying add(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(0, content, author);
        wiseSayingRepository.add(wiseSaying);

        return wiseSaying;
    }

    // 명언 목록 출력
    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAllWiseSaying();
    }

    // 명언 삭제
    public boolean removeById(int id) {
        return wiseSayingRepository.remove(id);
    }

    // 명언 수정
    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.updateWiseSaying(content, author);

        wiseSayingRepository.modifyWiseSaying(wiseSaying);
    }

    // id로 WiseSaying 검색
    public Optional<WiseSaying> findById(int id) {
        // 조건에 맞는 요소 찾기
        return wiseSayingRepository.findWiseSayingById(id);
    }

}
