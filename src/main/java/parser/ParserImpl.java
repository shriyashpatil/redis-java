package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class ParserImpl implements Parser{
    @Override
    public List<String> parse(BufferedReader br)  {
        List<String> tokens = new ArrayList<>();
        String input;

        try {
            System.out.println("Parsing the input");

            while ((input = br.readLine()) != null) {
                System.out.println("Input: " + input);
                char type = input.charAt(0);
                switch (type) {
                    case '*':
                        tokens.addAll(parseArray(br, input));
                        break;
                    case '$':
                        tokens.add(parseSingleLine(br,input));
                        break;
                    case '+':
                    case '-':
                        tokens.add(input.substring(1));
                        break;
                    default:
                        System.out.println("Unknown type: " + type);
                }
                System.out.println("After Input: " + input);
            }
        } catch (IOException e) {
            System.err.println("Error while parsing: " + e.getMessage());
        }

        return tokens;

    }

    private List<String> parseArray(BufferedReader br, String input) throws IOException {
        List<String> arrayTokens = new ArrayList<>();
        int arrayLength = Character.getNumericValue(input.charAt(1));

        for (int i = 0; i < arrayLength; i++) {
            // Skip current line and read the next line
            input = br.readLine();
            input = br.readLine();
            if (input != null) {
                System.out.println("Array element: " + input);
                arrayTokens.add(input);
            }
        }

        return arrayTokens;
    }

    private String parseSingleLine(BufferedReader br,String input) throws IOException {
        input = br.readLine(); // Skip current line
        input = br.readLine();
        if (input != null) {
            System.out.println("Single line token: " + input);
        }
        return input;
    }



}
