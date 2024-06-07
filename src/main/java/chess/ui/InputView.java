package chess.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int START_POSITION = 1;
    private static final int TARGET_POSITION = 2;

    private InputView() {

    }

    public static String inputCommand() {
        return scanner.nextLine();
    }

    public static String extractStartPosition(String moveCommands) {
        return separateCommand(moveCommands).get(START_POSITION);
    }

    public static String extractTargetPosition(String moveCommands) {
        return separateCommand(moveCommands).get(TARGET_POSITION);
    }

    public static List<String> separateCommand(String moveCommands) {
        List<String> commandList = Arrays.stream(moveCommands.split(" "))
                .collect(Collectors.toList());
        checkMoveCommand(commandList);
        return commandList;
    }

    public static void checkMoveCommand(List<String> commandList) {
        if (!commandList.get(0).equals("move")) {
            throw new IllegalArgumentException("올바른 move 명령을 입력해 주세요.");
        }
    }
}
