package may.boj_3109;

import java.util.*;
import java.io.*;

public class YuJeongYun {
    static int R, C;
    static boolean[][] board;
    static int[] dx = {-1, 0, 1}; // 최대한 위쪽으로 이동하도록
    static int[] dy = {1, 1, 1}; // 오른쪽 위->오른쪽->오른쪽 아래
    static int answer = 0;
    static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new boolean[R][C]; // .:true, x:false

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                if (s.charAt(j) == '.') {
                    board[i][j] = true;
                }
            }
        }

        for (int i = 0; i < R; i++) { // 각 행의 첫 번째 열에서 출발
            flag = false;
            DFS(i, 0);
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int x, int y) {
        if (flag) {
            return;
        }
        if (y == C - 1) { // 마지막 열 도착했을 경우
            answer++;
            flag = true;
            return;
        }

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < R && ny < C && board[nx][ny]) {
                if (flag) return; // DFS로 첫 열에서 도착한 경로 있으면 반복문 안 돌도록
                board[nx][ny] = false;
                DFS(nx, ny);
                // DFS로 이전에 방문 체크 한 부분은 연결된 파이프 경로이거나, 마지막 열까지 도착 못 하는 경로
                // => 방문 체크 해제 할 필요 없음

            }
        }
    }


}