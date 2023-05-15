package may.boj_15486;

import java.io.*;
import java.util.*;

public class YuJeong_Yun {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N + 1][2]; // 0열:기간, 1열:금액

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());

            arr[i][0] = day;
            arr[i][1] = money;
        }

        // dp -> i일에 얻을 수 있는 최대 금액
        // 돈을 다음날 받는다고 가정 & 마지막날 기간이 1일인 경우 있을 수 있으므로 => N+2
        int[] dp = new int[N + 2];
        int max = 0; // i일차 최대금액
        for (int i = 1; i <= N; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }

            int nxt = i + arr[i][0]; // i일차 작업 끝냈을 때 날짜
            // ex) 2일차 => nxt = 2 + 5 = 7
            if (nxt < N + 2) { // i일차 작업을 퇴사 전까지 완료할 수 있을 경우
                dp[nxt] = Math.max(dp[nxt], max + arr[i][1]); // dp[nxt]에 들어있는 값과, 2일차 전까지 최대값+2일차 작업의 수당 비교
            }
        }
        max = Math.max(max, dp[N + 1]); // 마지막 날 일이 1일인 경우 있을 수 있으므로

        bw.write(max + "");
        bw.flush();
        bw.close();
        br.close();
    }

}