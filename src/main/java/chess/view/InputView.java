package chess.view;

import java.util.Scanner;

public class InputView {

  private static final Scanner scanner = new Scanner(System.in);

  public static String receiveInitialCommand() {
    System.out.println("> 체스 게임을 시작합니다.");
    System.out.println("> 이어하기 : continue");
    System.out.println("> 새로하기 : new");
    return scanner.nextLine();
  }

  public static String receiveCommand() {
    System.out.println("> 게임 시작 : start");
    System.out.println("> 게임 종료 : end");
    System.out.println("> 게임 이동 : move source 위치 target 위치 - 예. move b2 b3");
    System.out.println("> 점수 출력 : status");
    return scanner.nextLine();
  }
}
