package com.ll.wiseSaying;

import java.util.HashMap;
import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final HashMap<Integer, WiseSaying> wiseSayings = new HashMap<>();
    private int nextId = 1;

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령 ) ");
            String inputCommand = scanner.nextLine().trim();

            if (inputCommand.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            // 명령어에 따라 분기 처리
            switch (parseCommand(inputCommand)) {
                case "등록" -> add();
                case "목록" -> findAll();
                case "삭제" -> delete(inputCommand);
                case "수정" -> update(inputCommand);
                default -> System.out.println("올바른 명령이 아닙니다.");
            }
        }
    }

    // 명언 등록
    private void add() {
        System.out.print("명언 : ");
        String wiseSaying = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        if (wiseSaying.isEmpty() || author.isEmpty()) {
            System.out.println("내용이 비어있을 수는 없습니다.");
            return;
        }

        WiseSaying newWiseSaying = new WiseSaying(nextId, wiseSaying, author);
        wiseSayings.put(nextId, newWiseSaying);
        System.out.println(nextId + "번 명언이 등록되었습니다.");
        nextId++;
    }

    // 명언 목록 출력
    private void findAll() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        if (wiseSayings.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
            return;
        }

        wiseSayings.values().forEach(wiseSaying ->
                System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent()));
    }

    // 명언 삭제
    private void delete(String command) {
        int id = extractId(command);

        if (id == -1) return;

        if (wiseSayings.remove(id) != null) {
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    // 명언 수정
    private void update(String command) {
        int id = extractId(command);

        if (id == -1) return;

        if (!wiseSayings.containsKey(id)) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying wiseSaying = wiseSayings.get(id);

        System.out.println("명언(기존): " + wiseSaying.getContent());
        System.out.print("명언 : ");
        String newContent = scanner.nextLine().trim();

        System.out.println("작가(기존): " + wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String newAuthor = scanner.nextLine().trim();

        if (newContent.isEmpty() || newAuthor.isEmpty()) {
            System.out.println("명언 또는 작가 이름은 비어있을 수 없습니다.");
            return;
        }

        wiseSaying.setContent(newContent);
        wiseSaying.setAuthor(newAuthor);
        System.out.println(id + "번 명언이 수정되었습니다.");
    }

    // 명령어 판별
    private String parseCommand(String command) {
        if (command.startsWith("삭제?id=")) return "삭제";
        if (command.startsWith("수정?id=")) return "수정";
        return command;
    }

    // ID 추출
    private int extractId(String command) {
        String[] parts = command.split("=");

        try {
            return Integer.parseInt(parts[1].trim());
        } catch (Exception e) {
            System.out.println("잘못된 ID 형식입니다.");
            return -1;
        }
    }
}
