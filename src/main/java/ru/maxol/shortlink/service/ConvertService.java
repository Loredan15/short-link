package ru.maxol.shortlink.service;

import org.springframework.stereotype.Service;

@Service
public class ConvertService {

    private static final String stringForCoding = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final char[] charsForCoding = stringForCoding.toCharArray();

    public String encode(long input){
        var encodedString = new StringBuilder();
        if(input == 0) {
            return String.valueOf(charsForCoding[0]);
        }

        while (input > 0) {
            encodedString.append(charsForCoding[(int) (input % charsForCoding.length)]);
            input = input / charsForCoding.length;
        }

        return encodedString.reverse().toString();
    }

    public long decode(String input) {
        var characters = input.toCharArray();
        var length = characters.length;
        var decoded = 0;
        var counter = 1;
        for (char character : characters) {
            decoded += stringForCoding.indexOf(character) * Math.pow(charsForCoding.length, length - counter);
            counter++;
        }
        return decoded;
    }
}
