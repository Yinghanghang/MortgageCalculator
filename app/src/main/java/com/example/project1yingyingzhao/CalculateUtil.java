package com.example.project1yingyingzhao;

public class CalculateUtil {
    public static float calculateTax(float P, float J, int N, float T){
        if(J == 0) {
            return P / N + T;
        } else {
            return (float) ((P * J) /(1f - Math.pow(1f + J, -N)) + T);
        }
    }
}
