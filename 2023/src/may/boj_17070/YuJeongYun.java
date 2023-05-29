package may.boj_17070;

import java.util.*;
import java.io.*;

public class YuJeongYun {
    static int[][] board;
    static int[][] dp1, dp2, dp3; //1:가로 ,2:세로, 3:대각선

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp1 = new int[N][N]; // 가로
        dp2 = new int[N][N]; // 세로
        dp3 = new int[N][N]; // 대각선
        dp1[0][1] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp1[i][j] != 0) { // 가로
                    if (j + 1 < N && board[i][j + 1] == 0) dp1[i][j + 1] += dp1[i][j]; // 오른쪽으로 이동
                    if (i + 1 < N && j + 1 < N && board[i][j + 1] == 0 && board[i + 1][j] == 0 && board[i + 1][j + 1] == 0)
                        dp3[i + 1][j + 1] += dp1[i][j]; // 대각선으로 이동
                }
                if (dp2[i][j] != 0) { // 세로
                    if (i + 1 < N && board[i + 1][j] == 0) dp2[i + 1][j] += dp2[i][j]; // 아래로 이동 이동
                    if (i + 1 < N && j + 1 < N && board[i][j + 1] == 0 && board[i + 1][j] == 0 && board[i + 1][j + 1] == 0)
                        dp3[i + 1][j + 1] += dp2[i][j]; // 대각선으로 이동
                }
                if (dp3[i][j] != 0) { // 대각선
                    if (j + 1 < N && board[i][j + 1] == 0) dp1[i][j + 1] += dp3[i][j]; // 오른쪽으로 이동
                    if (i + 1 < N && board[i + 1][j] == 0) dp2[i + 1][j] += dp3[i][j]; // 아래로 이동 이동
                    if (i + 1 < N && j + 1 < N && board[i][j + 1] == 0 && board[i + 1][j] == 0 && board[i + 1][j + 1] == 0)
                        dp3[i + 1][j + 1] += dp3[i][j]; // 대각선으로 이동
                }
            }
        }

        int answer = dp1[N - 1][N - 1] + dp2[N - 1][N - 1] + dp3[N - 1][N - 1];
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

}