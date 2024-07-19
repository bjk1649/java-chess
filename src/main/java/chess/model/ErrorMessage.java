package chess.model;

public enum ErrorMessage {
  INVALID_INITIAL_COMMAND("[ERROR] 게임 시작 명령은 start, 게임 종료 명령은 end 여야 합니다."),
  MISMATCH_FILE_ARGUMENT("[ERROR] 잘못된 파일 규격입니다."),
  MISMATCH_RANK_ARGUMENT("[ERROR] 잘못된 랭크 규격입니다."),
  MISMATCH_SYMBOL("[ERROR] 잘못된 피스 타입입니다."),
  HAS_OBSTACLE("[ERROR] 경로에 다른 체스 말이 존재합니다."),
  NO_PIECE_AT_SOURCE("[ERROR] 출발점에 말이 없습니다."),
  INVALID_TURN("[ERROR] 차례에 맞는 말을 선택해야 합니다."),

  INVALID_DIRECTION("[ERROR] 이동할 수 없는 방향입니다."),
  SAME_COLOR_PIECE("[ERROR] 같은 색의 말로는 이동할 수 없습니다."),
  SAME_POSITION("[ERROR] 출발지와 목적지가 동일하여 이동할 수 없습니다."),
  INVALID_MOVE_COMMAND("[ERROR] 이동은 move source 위치 target 위치여야 합니다."),
  INVALID_COMMAND("[ERROR] 유효하지 않은 명령입니다.");

  private final String message;

  ErrorMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
