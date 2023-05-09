package may.boj_11660;

import java.io.*;
import java.util.*;

public class YuJeongYun {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            int pre = 0;
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken()) + pre; // 현재 위치 값 = 현재 행에서 누적 합 값
                pre = board[i][j];
            }
        }
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                board[i][j] += board[i - 1][j]; // 현재 위치 값 = 현재 위치까지 블록 누적 합 값
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken()),
                    x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken()),
                    answer = board[x2][y2] - board[x2][y1 - 1] - board[x1 - 1][y2] + board[x1 - 1][y1 - 1];
            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }


}