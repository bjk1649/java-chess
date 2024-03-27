package chess;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<String, String> board; //좌표, 기물종류

    public Board(){
        this.board = new HashMap<>();
    }
}
