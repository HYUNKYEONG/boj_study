package may.boj_16438;

import java.io.*;
import java.util.*;

public class YuJeongYun {
    static int cnt = 7;
    static boolean[][] team; // 각 경기 별 팀 담을 배열 (true=A ,false=B)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        team = new boolean[cnt][N];
        for (int i = 0; i < cnt; i++) { // 각 팀에 최소 한 마리 원숭이 있어야 하므로
            team[i][0] = true; // 경기별 첫 번째 원숭이 A팀으로 지정
        }

        findTeam(0, 0, N - 1);

        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < N; j++) {
                if (team[i][j]) bw.write("A");
                else bw.write("B");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void findTeam(int seq, int s, int e) { // seq-경기 횟수, s-시작 인덱스, e-마지막 인덱스
        if (seq == cnt || s == e) { // 경기 횟수 초과 or 원숭이 한마리
            return;
        }

        int mid = (s + e) / 2;
        for (int i = s; i <= mid; i++) {
            team[seq][i] = true;
        }

        findTeam(seq + 1, s, mid);
        findTeam(seq + 1, mid + 1, e);
    }


}