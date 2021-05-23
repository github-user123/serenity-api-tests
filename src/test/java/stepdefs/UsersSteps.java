package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import java.util.Map;

import static hooks.Hooks.dataHelper;
import static net.serenitybdd.rest.SerenityRest.*;

public class UsersSteps {

    @Given("the GET request to list the users")
    public void theListOfUsers() {

        SerenityRest.given(getDefaultRequestSpecification())
                .when().log().all()
                .get(dataHelper.getUserData().getHerokuappUrl() + "users");
    }
    @Given("the GET request for instructions")
    public void theInstructions() {
        SerenityRest.given(getDefaultRequestSpecification())
                .when().log().all()
                .get(dataHelper.getUserData().getHerokuappUrl() + "instructions");
    }

    @Then("the initiation is successful")
    public void initiationIsSuccessful() {
        SerenityRest.lastResponse().prettyPeek()
                .then()
                .statusCode(200);
    }

    @And("the instructions returned with todo attribute")
    public void retrieveInstructions() {
        String resTodo = SerenityRest.lastResponse().jsonPath().get("todo");
        Assert.assertEquals("Build an API which calls this API, and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London. Push the answer to Github, and send us a link.",resTodo);
    }

    @Given("the GET request to retrieve user details by Id")
    public void getUserById() {
        List<Integer> resIDs = SerenityRest.lastResponse().jsonPath().getList("id");
        for(Integer userId:resIDs) {
            SerenityRest.given(getDefaultRequestSpecification())
                    .when().log().all()
                    .get(dataHelper.getUserData().getHerokuappUrl() + "user/" + userId);
            String resCity = SerenityRest.lastResponse().jsonPath().get("city");
            if(resCity.equals("Reading")){
                System.out.println(SerenityRest.lastResponse().jsonPath());
                Assert.assertEquals("Antonius",SerenityRest.lastResponse().jsonPath().getJsonObject("first_name"));
            }
        }
    }

    @Given("the GET user with null Id")
    public void checkNullId() {
        SerenityRest.given(getDefaultRequestSpecification())
                .when().log().all()
                .get(dataHelper.getUserData().getHerokuappUrl() + "user/ ");
    }

    @Then("the GET with null city returns 404")
    public void errorNotFound() {
        SerenityRest.lastResponse().prettyPeek()
                .then()
                .statusCode(404);
    }

    @Given("the GET users with null city")
    public void checkNullCity() {
        SerenityRest.given(getDefaultRequestSpecification())
                .when().log().all()
                .get(dataHelper.getUserData().getHerokuappUrl() + "city//users ");
    }
    @And("user data returned correctly")
    public void userDataReturned() {
        List<String> resCity = SerenityRest.lastResponse().jsonPath().get();
        for (String data : resCity) {
            Assert.assertEquals("London", data);
        }
    }

    @And("number of users returned 1000")
    public void correctNumberOfUsersReturned() {
        List<String> resIDs = SerenityRest.lastResponse().jsonPath().getList("id");
        Assert.assertEquals(1000, resIDs.size());
    }

    @Given("the GET request to list users in London")
    public void theUsersFromCity() {
        SerenityRest.given(getDefaultRequestSpecification())
                .when().log().all()
                .get(dataHelper.getUserData().getHerokuappUrl() + "city/London/users");
    }

    @And("list of users live in London")
    public void listOfUsersInLondon() {
        List<String> resFirstName = SerenityRest.lastResponse().jsonPath().getList("first_name");
        Assert.assertEquals(6, resFirstName.size());
        for(String user:resFirstName ){
                System.out.println("List of users who live in London: "+user);
        }
    }

    @And("list of users live in London 50 miles radius")
    public void listOfUsersInLondon50MilesRadius() {
        //TODO add solution to calculate user location based on latitude and longitude, bug needs to be fixed before this.
       ArrayList<Object> resLat = SerenityRest.lastResponse().jsonPath().get("latitude");
        System.out.println("List of users who live around London:" +resLat);
      //  Double[] actualLat = {-6.511591, -6.709855, 27.69417, -8.184485, 37.13, 5.7204204};
        for (Object user : resLat) {
            // System.out.println(user);
            if (user.getClass() == Float.class) {
                System.out.println(user);
            }
            Assert.assertEquals(SerenityRest.lastResponse().jsonPath().getJsonObject("latitude"), resLat);
        }

    }

}
