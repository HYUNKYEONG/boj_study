package may.boj_13255;

import java.io.*;
import java.util.*;

public class YuJeong_Yun {
    static int N, K;
    static int[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        A = new int[K + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 문제에서 구하고자 하는 것은 전체 동전들의 앞면 기댓값이므로
        // 시퀀스 전체들의 확률은 결국에는 동일한 확률값을 나눠가지게 되는 것으로 봐도 OK

        // 각각의 동전이 뒤집어질 확률 : K/N    [ex. 10개 중에 3개 뒤집을 때 => (1/10)*3 ]
        //  동전이 뒤집히지 않을 확률 : 1-K/N

        // N개 동전 중 a개의 앞면과 N-a개의 뒷면으로 구성된 상황에서, K개를 랜덤으로 뒤집을 때 앞면 기댓값은
        // 앞면은 그대로 유지되도록 뽑히지 않을 확률을 곱해주고 + 뒷면은 뒤집혀서 앞면이 나오도록 뽑힐 확률을 곱해줌
        // => a*(1-(K/N)) + (N-a)(K/N)

        double pr = N; // 앞면의 기댓값 담을 변수
        for (int i = 1; i <= K; i++) {
            double temp = 0.0;
            temp += pr * (1 - (double) A[i] / N) + (N - pr) * ((double) A[i] / N);
            pr = temp;
        }

        bw.write(pr + "");
        bw.flush();
        bw.close();
        br.close();
    }


}
