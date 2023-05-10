package com.application;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 문제 테스트 데이터
        int n = 5;
        int[] lost = {1, 3};
        int[] reserve = {2, 4};
        // 0 2 0 2 1
        // 우선 순위?
        // 양쪽 다 빌릴 수 있다 => 순위 낮음
        // 한쪽만 빌릴 수 있다 => 순위 높음
        int result = 0;
        // 여벌 있을 경우 대여, 앞이나 뒤만 가능
        // 최대한 많은 학생
        // lost reserve 로 전체 학생들의 체육복 보유를 나타내는 배열 선언?
        int[] now = new int[n];

        for (int i = 0; i < n; i++) {
            now[i] = calNow(isLost(i + 1, lost), isReserve(i + 1, reserve));
        }
        for (int i = 0; i < n - 1; i++) {
            if (now[i] == 2 && now[i + 1] == 0) {
                now[i] = 1;
                now[i + 1] = 1;
            }
        }
        for (int i = n - 1; i > 0; i--) {
            if (now[i] == 2 && now[i - 1] == 0) {
                now[i] = 1;
                now[i - 1] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (now[i] > 0) result++;
        }

        System.out.println(result);
    }

    public static boolean isLost(int num, int[] lost) {
        for (int n : lost) {
            if (n == num) return true;
        }
        return false;
    }

    public static boolean isReserve(int num, int[] reserve) {
        for (int n : reserve) {
            if (n == num) return true;
        }
        return false;
    }

    public static int calNow(boolean lost, boolean reserve) {
        if (lost && !reserve) {
            return 0;
        } else if (lost && reserve) {
            return 1;
        } else if (!lost && !reserve) {
            return 1;
        } else {
            return 2;
        }
    }
}