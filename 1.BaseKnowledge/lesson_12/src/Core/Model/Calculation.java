package Core.Model;

import java.util.ArrayList;

public class Calculation implements SolutionModel{
    public String getSolution(String clientRequest) {
        ArrayList<String> PostfixList = InfixToPostfix.toPostfix(clientRequest);
        Double digit = InfixToPostfix.calculatePostfix(PostfixList);
        return String.valueOf(digit);
    }
}

