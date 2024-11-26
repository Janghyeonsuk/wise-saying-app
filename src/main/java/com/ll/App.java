package com.ll;

import com.ll.system.controller.SystemController;
import com.ll.domain.wiseSaying.controller.WiseSayingController;
import com.ll.domain.wiseSaying.repository.WiseSayingRepository;
import com.ll.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class App {
    private final Scanner sc;
    private final WiseSayingController wiseSayingController;
    private final SystemController systemController;

    public App() {
        sc = new Scanner(System.in);
        wiseSayingController = new WiseSayingController(sc, new WiseSayingService(new WiseSayingRepository()));
        systemController = new SystemController();
    }


    public void run() {
        System.out.println("== 명언 앱 ==");
        boolean isExcute = true;

        wiseSayingController.initSampleData();

        while (isExcute) {
            System.out.print("명령 ) ");
            String cmd = sc.nextLine();

            // 명령어에 따라 분기 처리
            switch (parseCommand(cmd)) {
                case "종료" -> isExcute = systemController.exit();
                case "등록" -> wiseSayingController.actionAdd();
                case "목록" -> wiseSayingController.actionList();
                case "삭제" -> wiseSayingController.actionDelete(cmd);
                case "수정" -> wiseSayingController.actionModify(cmd);
                default -> System.out.println("올바른 명령이 아닙니다.");
            }
        }
    }


    // 명령어 판별
    public String parseCommand(String cmd) {
        if (cmd.startsWith("삭제?id=")) return "삭제";
        if (cmd.startsWith("수정?id=")) return "수정";
        return cmd;
    }


}
