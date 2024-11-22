package com.ll.wiseSaying;

import java.util.HashMap;
import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final HashMap<Integer, WiseSaying> wiseSayings = new HashMap<>();
    private int lastId = 1;

    public void run() {
        System.out.println("== 명언 앱 ==");
        boolean execute = true;

        while (execute) {
            System.out.print("명령 ) ");
            String cmd = scanner.nextLine();

            // 명령어에 따라 분기 처리
            switch (parseCommand(cmd)) {
                case "종료" -> execute = false;
                case "등록" -> add();
                case "목록" -> findAll();
                case "삭제" -> delete(cmd);
                case "수정" -> update(cmd);
                default -> System.out.println("올바른 명령이 아닙니다.");

            }
        }
    }

    // 명언 등록
    private void add() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();

        if (content.isEmpty() || author.isEmpty()) {
            System.out.println("내용이 비어있을 수는 없습니다.");
            return;
        }

        WiseSaying saveWiseSaying = saveWiseSaying(content, author);

        System.out.println(saveWiseSaying.getId() + "번 명언이 등록되었습니다.");
        lastId++;
    }

    //명언 저장
    private WiseSaying saveWiseSaying(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(lastId, content, author);
        wiseSayings.put(wiseSaying.getId(), wiseSaying);
        return wiseSaying;
    }

    // 명언 목록 출력
    private void findAll() {
        System.out.println("번호  /  작가  /  명언");
        System.out.println("-------------------");

        if (wiseSayings.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
            return;
        }

        wiseSayings.values().forEach(System.out::println);
    }

    // 명언 삭제
    private void delete(String cmd) {
        int id = extractId(cmd);

        if (id == -1) return;

        if (wiseSayings.remove(id) != null) {
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    // 명언 수정
    private void update(String cmd) {
        int id = extractId(cmd);

        if (id == -1) return;

        if (!wiseSayings.containsKey(id)) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying wiseSaying = wiseSayings.get(id);

        System.out.println("명언(기존): " + wiseSaying.getContent());
        System.out.print("명언 : ");
        String newContent = scanner.nextLine();

        System.out.println("작가(기존): " + wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String newAuthor = scanner.nextLine();

        if (newContent.isEmpty() || newAuthor.isEmpty()) {
            System.out.println("명언 또는 작가 이름은 비어있을 수 없습니다.");
            return;
        }

        wiseSaying.updateWiseSaying(newContent, newAuthor); //명언 수정

        System.out.println(id + "번 명언이 수정되었습니다.");
    }

    // 명령어 판별
    private String parseCommand(String cmd) {
        if (cmd.startsWith("삭제?id=")) return "삭제";
        if (cmd.startsWith("수정?id=")) return "수정";
        return cmd;
    }

    // ID 추출
    private int extractId(String cmd) {
        String[] cmdArr = cmd.split("=");

        try {
            return Integer.parseInt(cmdArr[1]);
        } catch (Exception e) {
            System.out.println("잘못된 id 형식입니다.");
            return -1;
        }
    }
}
