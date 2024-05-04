package chess.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static String inputCommand() {
        return scanner.nextLine();
    }

    public static List<String> extractMovePath(String moveCommands) {
        List<String> commandList = Arrays.stream(moveCommands.split(" "))
                .collect(Collectors.toList());
        checkMoveCommand(commandList);
        commandList.remove(0);
        return commandList;
    }

    public static void checkMoveCommand(List<String> commandList) {
        if(!commandList.get(0).equals("move")) {
            throw new IllegalArgumentException("올바른 move 명령을 입력해 주세요.");
        }
    }
}
