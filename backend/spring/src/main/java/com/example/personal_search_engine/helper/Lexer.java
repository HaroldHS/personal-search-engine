package com.example.personal_search_engine.helper;

public class Lexer {
    
    public static String[] getAlpahNumericTokens(String content) {
        StringBuilder result = new StringBuilder();
        int contentLength = content.length();

        for (int i=0; i<contentLength; i++) {
            char charASCII = content.charAt(i);

            // Only accept alphanumeric characters (and space)
            if(
                (charASCII == ' ')
                || (charASCII >= '0' && charASCII <= '9')
                || (charASCII >= 'A' && charASCII <= 'Z')
                || (charASCII >= 'a' && charASCII <= 'z')
            ) {
                result.append(charASCII);
            }

        }

        return result.toString().split("[\\s]+");
    }

    public static String getContentFromLexerTokens(String[] tokens) {
        StringBuilder result = new StringBuilder();

        for (String s : tokens) {
            result.append(s.concat(" "));
        }

        return result.toString();
    }
}
