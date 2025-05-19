package org.example.services;

import java.util.regex.Pattern;

public class PassengerService {
    public int sum;

    public boolean validateCpf(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int firstCheckDigit = 11 - (sum % 11);
        firstCheckDigit = firstCheckDigit >= 10 ? 0 : firstCheckDigit;
        if (firstCheckDigit != cpf.charAt(9) - '0') return false;

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int secondCheckDigit = 11 - (sum % 11);
        secondCheckDigit = secondCheckDigit >= 10 ? 0 : secondCheckDigit;
        return secondCheckDigit == cpf.charAt(10) - '0';
    }

    public boolean validateEmail(String email) {
        String patternEmail = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$";
        return Pattern.matches(patternEmail, email);
    }
}
