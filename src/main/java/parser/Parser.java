package parser;

import java.io.BufferedReader;
import java.util.List;

public interface Parser {

    public List<String> parse(BufferedReader br);

}
