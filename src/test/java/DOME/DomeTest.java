package DOME;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class DomeTest {
    @Test
    public void testGetHtml(){
        given()
                .log().all().get("https://www.baidu.com")
                .then().log().all().statusCode(200);
    }

    @Test
    public void testMp3(){
        given()
                .queryParam("wd","mp3")
        .when()
                .get("https://www.baidu.com")
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testSearch(){
        useRelaxedHTTPSValidation();
        given()
                .log().all()
                //.proxy("127.0.0.1",8888)
                .queryParam("code","sogo")
                .header("Cookie","_ga=GA1.2.1028913041.1552358871; device_id=d5fecc82d1df9d7391ce832662a3f9d7; s=br17pfxgmt; xq_a_token=5c104b18ca74a0bac1d609b63885de33c07a65d0; xqat=5c104b18ca74a0bac1d609b63885de33c07a65d0; xq_r_token=03d48b28745dbed6e0596a03930056dbcb3f5e5b; xq_token_expire=Sun%20Aug%2018%202019%2017%3A51%3A04%20GMT%2B0800%20(China%20Standard%20Time); xq_is_login=1; u=3647164279; Hm_lvt_1db88642e346389874251b5a1eded6e3=1563962040,1564369207; snbim_minify=true; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1564386032")
        .when()
                .get("https://xueqiu.com/stock/search.json")
        .then()
                .log().all()
                .statusCode(200)
                .body("stocks.code",hasItem("SOGO"))
                .body("stocks.name",hasItem("搜狗"))
                .body("stocks.find { it.code == 'SOGO' }.name",equalTo("搜狗"));
    }
/*
    @Test
    public void testJson(){
        when().get("http://192.168.31.99:8000/")
        .then()
                .body("store.book.category",hasItems("reference"))
                .body("store.book[0].author",equalTo("Nigel Rees"))
                .body("store.book[-1].author",equalTo("J. R. R. Tolkien"))
                .body("store.book.findAll { book -> book.price >= 5 && book.price <= 15 }",hasItems())
                .body("store.book.find { book -> book.price ==8.95f }.price",equalTo(8.95f));
    }

    @Test
    public void testXml(){
        when().get("http://192.168.31.99:8000/")
                .then()
                        .body("shopping.category.item[0].name",equalTo("Chocolate"))
                        .body("shopping.category.item.size()",equalTo(5))
                        .body("shopping.category.findAll { it.@type == 'groceries' }.size()",equalTo(1))
                        .body("shopping.category.item.findAll { it.price == 20 }.name",equalTo("Coffe"))
                        .body("**.findAll { it.price == 20 }.name",equalTo("Coffe"));
    }

    @Test
    public void testHtml(){
        given()
                .log().all()
                .queryParam("wd","mp3")
        .when()
                .get("http://www.baidu.com/s")
        .then()
                .log().all()
                .statusCode(200)
                .body("html.head.title",equalTo("mp3_百度搜索"));
    }
    */
}
