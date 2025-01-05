package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class ParserImpl implements Parser{
    @Override
    public List<String> parse(BufferedReader br)  {
            try {
                List<String> tokens = new ArrayList<>();
                String input = br.readLine();
                while (input!=null){

                    if(input.charAt(0)=='*'){
                        int arrayLength = Character.getNumericValue(input.charAt(1));
                        for(int itr=0; itr<arrayLength; itr++){
                            br.readLine();
                            input = br.readLine();
                            tokens.add(input);
                        }
                        input = br.readLine();
                    }
                    else if(input.charAt(0)=='$'){
                        br.readLine();
                        input = br.readLine();
                        tokens.add(br.readLine());
                    }
                    else if(input.charAt(0)=='+'){
                        tokens.add(input.substring(1));
                    }
                    else if(input.charAt(0)=='-'){
                        tokens.add(input.substring(1));
                    }
                    input = br.readLine();
                }
                return tokens;
            }catch(IOException e){

            }

        return new ArrayList<>();
    }
}
