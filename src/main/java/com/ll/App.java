package com.ll;

import com.ll.domain.wiseSaying.controller.WiseSayingController;
import com.ll.domain.wiseSaying.repository.WiseSayingMemoryRepository;
import com.ll.domain.wiseSaying.repository.WiseSayingRepository;
import com.ll.domain.wiseSaying.service.WiseSayingService;
import com.ll.system.controller.SystemController;

import java.util.Scanner;

public class App {
    private final Scanner sc;
    private final WiseSayingController wiseSayingController;
    private final SystemController systemController;

    public App() {
        sc = new Scanner(System.in);
        WiseSayingRepository wiseSayingRepository = new WiseSayingMemoryRepository();
        WiseSayingService wiseSayingService = new WiseSayingService(wiseSayingRepository);
        wiseSayingController = new WiseSayingController(sc, wiseSayingService);
        systemController = new SystemController();
    }


    public void run() {
        System.out.println("== 명언 앱 ==");

        wiseSayingController.initSampleData();

        while (true) {
            System.out.print("명령 ) ");
            String cmd = sc.nextLine();
            Command command = new Command(cmd);

            // 명령어에 따라 분기 처리
            switch (command.getActionName()) {
                case "종료" -> {
                    systemController.exit();
                    return;
                }
                case "등록" -> wiseSayingController.actionAdd();
                case "목록" -> wiseSayingController.actionList();
                case "삭제" -> wiseSayingController.actionDelete(command);
                case "수정" -> wiseSayingController.actionModify(command);
                default -> System.out.println("올바른 명령이 아닙니다.");
            }
        }
    }
}
