package may.boj_1890;

import java.io.*;
import java.util.*;

public class YuJeongYun {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        long[][] dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int start = board[0][0];
        if (start < N) {
            dp[start][0]++;
            dp[0][start]++;
        }

        out:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) break out;
                if (dp[i][j] > 0) {
                    int dist = board[i][j];
                    if (j + dist < N) {
                        dp[i][j + dist] += dp[i][j];
                    }
                    if (i + dist < N) {
                        dp[i + dist][j] += dp[i][j];
                    }
                }
            }
        }

        bw.write(dp[N - 1][N - 1] + "");
        bw.flush();
        bw.close();
        br.close();
    }


}
