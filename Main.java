package com.company;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    final static byte MONTHS_IN_YEARS = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {

        int principal = (int) readNumber("Principal", 1000, 1_000_000);
        float  annualInterestRate  = (float) readNumber ("Annual Interest Rate :", 1 , 30);
        byte periodYears = (byte) readNumber("Period (Years): ", 1, 30);
        printMorgage(principal, annualInterestRate, periodYears);
        printPaymentSchedule(principal, annualInterestRate, periodYears);

    }

    private static void printMorgage(int principal, float annualInterestRate, byte periodYears) {
        double morgage = calculateMortgage(principal, annualInterestRate, periodYears);
        String morgageFormatted = NumberFormat.getCurrencyInstance().format(morgage); // formatting
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("-------");
        System.out.println("Monthly Payments: " + morgageFormatted);

    }

    private static void printPaymentSchedule(int principal, float annualInterestRate, byte periodYears) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("-------------");

        for(short month = 1; month <= periodYears *MONTHS_IN_YEARS; month++){

            double balance = calculateBalance(principal, annualInterestRate, periodYears, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));

        }
    }

    public static double readNumber(String prompt, double min , double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true){
            System.out.println(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between" + min + " and " + max);
        }

        return value;

    }

    public static double calculateMortgage(
            int principal,
            float annualInterestRate,
            byte periodYears) {

        float monthlyInterestRate = annualInterestRate / MONTHS_IN_YEARS / PERCENT;
        short periodMonths = (short) (periodYears * MONTHS_IN_YEARS);

        double morgage = principal
                * (monthlyInterestRate * Math.pow((1 + monthlyInterestRate), periodMonths))
                / (Math.pow((1 + monthlyInterestRate), periodMonths) - 1);

        return morgage;

    }

    public static double calculateBalance(
            int principal,
            float annualInterestRate,
            byte periodYears,
            short numberOfPaymentsMade) {

        float monthlyInterestRate = annualInterestRate / MONTHS_IN_YEARS / PERCENT;
        int numberOfPayments = (int) (periodYears * MONTHS_IN_YEARS);

        double balance = (float) (principal * ((Math.pow((1 + monthlyInterestRate), numberOfPayments)) -
                (Math.pow((1 + monthlyInterestRate), numberOfPaymentsMade) ) )
                /( (Math.pow( (1 + monthlyInterestRate), numberOfPayments) ) - 1));

        return balance;

    }

}
