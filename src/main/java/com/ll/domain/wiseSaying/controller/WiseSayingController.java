package com.ll.domain.wiseSaying.controller;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final WiseSayingService wiseSayingService;
    private final Scanner sc;

    public WiseSayingController(Scanner sc, WiseSayingService wiseSayingService) {
        this.wiseSayingService = wiseSayingService;
        this.sc = sc;
    }

    //샘플데이터
    public void initSampleData() {
        wiseSayingService.addWiseSaying("나의 죽음을 적들에게 알리지 말라.", "이순신 장군");
        wiseSayingService.addWiseSaying("삶이 있는 한 희망은 있다.", "키케로");
    }

    //명언 등록
    public void actionAdd() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        if (content.isEmpty() || author.isEmpty()) {
            System.out.println("내용이 비어있을 수는 없습니다.");
            return;
        }

        WiseSaying wiseSaying = wiseSayingService.addWiseSaying(content, author);

        System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
    }

    //명언 목록
    public void actionList() {
        System.out.println("번호  /  작가  /  명언");
        System.out.println("-------------------");


        List<WiseSaying> wiseSayings = wiseSayingService.findAll();
        if (wiseSayings.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
            return;
        }


        for (WiseSaying wiseSaying : wiseSayings.reversed()) {
            System.out.println(wiseSaying);
        }
    }

    //명언 삭제
    public void actionDelete(String cmd) {
        int id = extractId(cmd);
        if (id == -1) return;

        boolean removed = wiseSayingService.removeById(id);

        if (removed) System.out.println(id + "번 명언을 삭제했습니다.");
        else System.out.println(id + "번 명언은 존재하지 않습니다.");
    }

    //명언 수정
    public void actionModify(String cmd) {
        int id = extractId(cmd);
        if (id == -1) return;

        WiseSaying foundWiseSaying = wiseSayingService.findById(id);

        System.out.println("명언(기존): " + foundWiseSaying.getContent());
        System.out.print("명언 : ");
        String newContent = sc.nextLine();

        System.out.println("작가(기존): " + foundWiseSaying.getAuthor());
        System.out.print("작가 : ");
        String newAuthor = sc.nextLine();

        if (newContent.isEmpty() || newAuthor.isEmpty()) {
            System.out.println("명언 또는 작가 이름은 비어있을 수 없습니다.");
            return;
        }

        wiseSayingService.modify(foundWiseSaying, newContent, newAuthor);

        System.out.println(id + "번 명언이 수정되었습니다.");
    }

    // cmd에서 id 추출
    public int extractId(String cmd) {
        String[] cmdArr = cmd.split("=");

        try {
            return Integer.parseInt(cmdArr[1]);
        } catch (Exception e) {
            System.out.println("잘못된 id 형식입니다.");
            return -1;
        }
    }
}
