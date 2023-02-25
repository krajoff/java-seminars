package Core.Model;

import java.util.ArrayList;

public class Calculation {
    public String Postfix(String clientRequest){
//        String clientRequest;
        ArrayList<String> PostfixList = InfixToPostfix.toPostfix(clientRequest);
        Double digit = InfixToPostfix.calculatePostfix(PostfixList);
        return String.valueOf(digit);
    }



}

