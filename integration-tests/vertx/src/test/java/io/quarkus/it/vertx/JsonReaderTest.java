package io.quarkus.it.vertx;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

@QuarkusTest
public class JsonReaderTest {

    @Test
    public void testJson() {
        String body = new JsonObject().put("Hello", "World").toString();
        given().contentType(ContentType.JSON).body(body)
                .post("/vertx-test/json-bodies/json/sync")
                .then().statusCode(200).body(equalTo("Hello World"));
    }

    @Test
    public void testEmptyJson() {
        given().contentType(ContentType.JSON).body("")
                .post("/vertx-test/json-bodies/json/sync")
                .then().statusCode(400);
    }

    @Test
    public void testArray() {
        String body = new JsonArray().add("Hello").add("World").toString();
        given().contentType(ContentType.JSON).body(body)
                .post("/vertx-test/json-bodies/array/sync")
                .then().statusCode(200).body(equalTo("Hello World"));
    }

    @Test
    public void testEmptyArray() {
        given().contentType(ContentType.JSON).body("")
                .post("/vertx-test/json-bodies/array/sync")
                .then().statusCode(400);
    }
}
