package hooks;

import model.DataHelper;
import helpers.Helpers;
import io.cucumber.java.Before;
import net.serenitybdd.rest.SerenityRest;

import java.io.FileNotFoundException;

public class Hooks {

    public static DataHelper dataHelper;

    @Before
    public void setUp() throws FileNotFoundException {
        dataHelper = new DataHelper();
        SerenityRest.setDefaultRequestSpecification(Helpers.getBaseRequestSpec());
    }

}
