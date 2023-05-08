package may.boj_3020;

import java.io.*;
import java.util.*;

public class YuJeongYun {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), H = Integer.parseInt(st.nextToken());

        int[] barrier1 = new int[H + 1], barrier2 = new int[H + 1]; // 각각 석순, 종유석 높이별 개수 담을 배열
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                barrier1[height]++;
            } else {
                barrier2[height]++;
            }
        }

        int[] destroy1 = new int[H + 1], destroy2 = new int[H + 1]; // 각각 레벨 별 파괴해야 할 장애물 개수
        for (int i = H - 1; i >= 1; i--) {
            destroy1[i] = barrier1[i] + destroy1[i + 1];
            destroy2[i] = barrier2[i] + destroy2[i + 1];
        }

        int min = Integer.MAX_VALUE, cnt = 0; // 파괴해야하는 장애물 최솟값, 구간의 수
        for (int i = 1; i <= H; i++) {
            int destroy = destroy1[i] + destroy2[H - i + 1];
            if (destroy < min) {
                min = destroy;
                cnt = 1;
            } else if (destroy == min) {
                cnt++;
            }
        }

        bw.write(min + " " + cnt);
        bw.flush();
        bw.close();
        br.close();
    }

}