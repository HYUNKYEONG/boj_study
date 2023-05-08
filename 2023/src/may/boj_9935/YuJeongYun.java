package may.boj_9935;

import java.io.*;
import java.util.*;

public class YuJeongYun {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        String bomb = br.readLine();
        int len = bomb.length();

        StringBuilder sb = new StringBuilder();
        out:
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(c);

            if (c == bomb.charAt(len - 1) && sb.length() >= len) { // 폭발 문자열의 마지막 글자와 같으면 && 스택에 bomb 길이만큼 남아있으면
                for (int j = 0; j < len; j++) {
                    if (sb.charAt(sb.length() - len + j) != bomb.charAt(j)) { // 폭발 문자열과 다르면 넘어감
                        continue out;
                    }
                }

                sb.delete(sb.length() - len, sb.length());
            }
        }


        if (sb.length() > 0) {
            bw.write(sb.toString());
        } else {
            bw.write("FRULA");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}