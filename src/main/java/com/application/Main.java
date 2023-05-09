package com.application;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] stages = {4,4,4,4,4};
        int N = 4;
        int[] result = new int[N];

        // 실패율 계산
        // 실패율 = 스테이지 도달 but 클리어 X / 스테이지 도달

        double[][] fRate = new double[N][2];

        for (int i = 1; i <= N; i++) {
            int num = 0; // numerator
            int den = 0; // denominator
            for (int stage : stages) {
                if (stage > i) num++;
                if (stage >= i) den++;
            }
            fRate[i - 1][0] = i;
            fRate[i - 1][1] = num / (double) den;
        }

        // sorting
        for (int i = 0; i < fRate.length; i++) {
            for (int j = 0; j < fRate.length - i - 1; j++) {
                double[] temp = new double[2];
                if (fRate[j + 1][1] < fRate[j][1]) {
                    temp = fRate[j + 1];
                    fRate[j + 1] = fRate[j];
                    fRate[j] = temp;
                }
            }
        }

        for (int i = 0; i < fRate.length; i++) {
            result[i] = (int) fRate[i][0];
        }
    }
}