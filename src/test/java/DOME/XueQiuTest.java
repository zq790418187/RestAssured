package DOME;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class XueQiuTest {
    public static String code;
    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;
    @BeforeClass
    public static void  before(){
        useRelaxedHTTPSValidation();
        RestAssured.proxy("127.0.0.1",8888);
        RestAssured.baseURI="https://api.xueqiu.com";

        requestSpecification=new RequestSpecBuilder().build();
        requestSpecification.port(80);
        requestSpecification.header("","");
        requestSpecification.cookie("","");

        responseSpecification=new ResponseSpecBuilder().build();
        responseSpecification.statusCode(200);
        responseSpecification.body("code",equalTo(1));
        loginXueqiu();
    }

    public static void loginXueqiu(){
        String code=given()
                .header("","")
                .cookie("","")
                .queryParam("","")
                .queryParam("","")
                .formParam("","")
                .formParam("","")
                .formParam("","")
                .when()
                .post("/provider/oauth/token")
                .then()
                .statusCode(400)
                .body("",equalTo(""))
                .extract()
                .path("");
        System.out.println(code);
    }
    @Test
    public void testLogin1(){
        Response response=given()
                //.header("","")
                .queryParam("","")
                .queryParam("","")
                //.cookie("","")
                .formParam("","")
                .formParam("","")
                .formParam("","")
                .when()
                .post("")
                .then()
                .statusCode(400)
                .body("",equalTo(""))
        .extract()
                .response();

        System.out.println(response.headers());
    }

    @Test
    public void testLogin(){
        given()
                //.header("","")
                // .cookie("","")
                .spec(requestSpecification)
                .queryParam("","")
                .queryParam("","")
                .queryParam("","")
                .queryParam("","")
                .when().get("")
                .then()
                //.statusCode(200)
                //.body("",equalTo(""))
                .spec(responseSpecification)
                .time(lessThan(1000L));
    }

    @Test
    public void testPostJson(){
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("a",1);
        map.put("b","testerhome");
        map.put("array",new String[] {"111","222"});
        given()
                .contentType(ContentType.JSON)
                .body(map)
       .when().post("")
       .then().statusCode(200);
    }

}
