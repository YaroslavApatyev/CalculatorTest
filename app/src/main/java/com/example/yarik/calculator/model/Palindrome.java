package com.example.yarik.calculator.model;

public class Palindrome  {

    public Palindrome(){
    }

    public boolean isPalindrome(int number) {

        int result = 0;
        int n = number;

        while (n > 0) {
            int digit = n % 10;
            result = result * 10 + digit;
            n /= 10;
        }
        return result == number;
    }
}