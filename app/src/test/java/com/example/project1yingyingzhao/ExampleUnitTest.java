package com.example.project1yingyingzhao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test1_isCorrect() {
        float P = 10000.00f;
        float J = 0.055f;
        int N = 15 * 12;
        float T = 0f;
        float actual = CalculateUtil.calculateTax(P, J / 12, N, T);
        float expect = 81.71f;
        assertEquals(expect, actual, 0.1f);
    }

    @Test
    public void test2_isCorrect() {
        float P = 10000.00f;
        float J = 0.055f;
        int N = 15 * 12;
        float T = 0.1f / 100 * P;
        float actual = CalculateUtil.calculateTax(P, J / 12, N, T);
        float expect = 91.71f;
        assertEquals(expect, actual, 0.1f);
    }

    @Test
    public void test3_isCorrect() {
        float P = 20000.00f;
        float J = 0.1f;
        int N = 20 * 12;
        float T = 0.1f / 100 * P;
        float actual = CalculateUtil.calculateTax(P, J / 12, N, T);
        float expect = 213.00f;
        assertEquals(expect, actual, 0.1f);
    }
}