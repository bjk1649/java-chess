# java-chess
## 1단계 - 체스판 초기화
### 기능 요구 사항
- **체스판 생성**
- [x] 체스판의 세로는 아래부터 위로 1 ~ 8로 구현한다
- [x] 체스판의 가로는 왼쪽부터 a ~ h로 구현한다
- **체스 말 배치**
- [x] 체스 말의 흑, 백은 대문자와 소문자로 구분한다
- [x] 체스 말의 종류는 폰, 룩, 나이트, 비숍, 퀸, 킹이 있다
- [x] 체스 말은 위치를 갖는다 (각자 초기 위치를 갖고있다)
  - 8: RNBQKBNR 배치
  - 7: PPPPPPPP 배치
  - 6~3: ........ 으로 배치
  - 2: pppppppp 배치
  - 1: rnbqkbnr 배치
- **입력**
- [x] 게임 시작 명령어는 start, 종료는 end 입력이 올바르지 않다면 예외 처리한다 

## 2단계 - 말 이동
### 기능 요구 사항
- **체스 말**
- [x] 이동 가능한지 검사
  - [x] 이동 위치에 같은 색의 체스 말이 있다면 이동할 수 없다 <- 체스판의 책임
  - [x] 체스 말 마다 가능한 이동이 다르다 <- Piece 마다 다르게 가져온다
    - [x] 이동 위치를 입력 받아 가능한 이동인지 계산하고 판단한다
- [x] 경로를 반환한다
- **체스판**
- [x] 체스 말을 움직인다
- [x] 체스 말들의 위치를 갖고 있다
  - [x] 선택한 위치에 체스 말이 없다면 예외 처리한다
  - [x] 체스 말을 이동할 순서가 다르다면 예외 처리한다
    - [x] 이동은 색을 번갈아 가며 한다 -> controller
- [x] 체스 말이 반환한 경로 안에 다른 체스 말이 있는지 검사
  - [x] 경로 안에 다른 체스 말이 있다면 이동할 수 없다 <- Path
- **입력**
- [x] move source위치 target위치을 실행해 이동한다

## 3단계 - 승패 및 점수
### 기능 요구 사항
- [x] King이 잡혔을 때 게임을 종료해야 한다
  - [x] 체스판에서 잡힌 말을 반환
  - [x] 체스 말에서 해당 말의 타입을 반환
- [x] status 명령어를 입력하면 점수를 출력
  - [x] 점수를 계산하는 ScoreCalculator 클래스
    - [x] 같은 File 에 있는 Pawn 은 0.5점을 준다

## 4단계 - DB 적용
### 기능 요구 사항
- **DB 연결 전 기능**
- [x] end 명령어를 입력하면 각자의 점수와 승리 팀을 출력한다
  - [x] 점수를 계산해서 점수가 높은 팀이 승리한다
- **DAO**
- [ ] 이전에 하던 체스 게임을 다시 시작할 수 있어야 한다
  - [ ] 사용자 ID로 사용자 엔티티를 조회한다
    - [ ] 사용자 ID를 저장한다 (이름은 10자 미만)
  - [ ] 사용자 ID로 체스 게임을 조회한다
    - [ ] 사용자가 현재 진행 중인 체스 게임(체스판)을 저장한다
    - [ ] 진행 중이던 게임을 이어서 시작할 수 있다
    - [ ] 새로운 게임을 시작할 수 있다
