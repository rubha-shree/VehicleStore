package com.company;

import java.util.regex.*;

public class Util {

    public static boolean isValidRegistrationNumber(String str) throws InvalidInputException{
        if (Pattern.matches("[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}", str)) {
            return  true;
        }

        throw new InvalidInputException("Registration number is of invalid pattern.");
    }
}
