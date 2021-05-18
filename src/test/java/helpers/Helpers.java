package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Helpers {

    public static RequestSpecification getBaseRequestSpec() {
        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.ANY)
                .addHeader("Accept-API-Version", "resource=2.0, protocol=1.0")
                .build();
    }
}
