package com.ll.domain.wiseSaying.service;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    // 명언 저장
    public WiseSaying addWiseSaying(String content, String author) {
        return wiseSayingRepository.saveWiseSaying(content, author);
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
    public void modify(WiseSaying foundWiseSaying, String content, String author) {
        wiseSayingRepository.modifyWiseSaying(foundWiseSaying, content, author);
    }

    // ID로 WiseSaying 검색
    public WiseSaying findById(int id) {
        // 조건에 맞는 요소 찾기
        WiseSaying wiseSaying = wiseSayingRepository.findWiseSayingById(id).orElse(null);
        if (wiseSaying == null) {
            System.out.println(id + "%d번 명언은 존재하지 않습니다.");
        }
        return wiseSaying;
    }

}
