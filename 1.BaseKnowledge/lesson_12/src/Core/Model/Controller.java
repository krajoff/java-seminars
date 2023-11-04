package Core.Model;

import java.io.IOException;

public class Controller {
    private SolutionModel model = new Calculation();

    public String getSolution(String equation) throws IOException {
        return model.getSolution(equation);
    }

}
