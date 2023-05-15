package com.application;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 문제 테스트 데이터
        int n = 5;
        int[] lost = {2, 4, 5};
        int[] reserve = {3};

        // 1 0 2 0 0

        int result = 0;
        int[] now = new int[n];

        for (int i = 0; i < n; i++) {
            now[i] = calNow(isLost(i + 1, lost), isReserve(i + 1, reserve));
        }

        for (int i = 0; i <= n; i++) {
            int[] nowLocal = now.clone();
            int resultLocal = 0;

            for (int j = i - 1; j >= 0; j--) {
                if (nowLocal[j] == 2) {
                    int wPriority = priority(j, nowLocal);
                    if (wPriority != 0) {
                        nowLocal[j + wPriority] = 1;
                        nowLocal[j] = 1;
                    }
                }
            }

            for (int j = i; j < n; j++) {
                if (nowLocal[j] == 2) {
                    int wPriority = priority(j, nowLocal);
                    if (wPriority != 0) {
                        nowLocal[j + wPriority] = 1;
                        nowLocal[j] = 1;
                    }
                }
            }

            System.out.printf("case #%d:", i);
            for (int j = 0; j < n; j++) {
                System.out.printf(" %d", nowLocal[j]);
                if (j != n - 1) {
                    System.out.printf(",");
                } else {
                    System.out.println("");
                }
                if (nowLocal[j] > 0) resultLocal++;
            }

            result = Math.max(result, resultLocal);
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

    public static int priority(int i, int[] now) {
        int previousPriority = calPriority(i - 1, now);
        int nextPriority = calPriority(i + 1, now);

        if (previousPriority == 1) return -1;
        if (nextPriority == 1) return 1;
        if (previousPriority == 2 && nextPriority == 2) return -1;
        return 0;
    }

    public static int calPriority(int i, int[] now) {
        int count = 0;
        if (i >= 0 && i < now.length) {
            if (now[i] != 0) return 0;
            if (i > 0) {
                if (now[i - 1] == 2) count++;
            }
            if (i < now.length - 1) {
                if (now[i + 1] == 2) count++;
            }
            return count;
        } else {
            return 0;
        }
    }
}