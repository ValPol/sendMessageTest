import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class SendMail extends tryToSendMail{

    tryToSendMail sendMail = new tryToSendMail();

    @BeforeClass
    public static void setup() {
      //  WebDriverContainer.getInstance().setDrivers();
    }

    @Test
    public void userLogin() {

    }
    @Test
    public void use() throws  UnirestException {
        String searchQueryApi = "https://petstore.swagger.io/v2/store/inventory";


      /*  JsonNode body = Unirest.get(searchQueryApi)
                .asJson()
                .getBody();
        System.out.println(body);
*/
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/index.twig");
        JtwigModel model = JtwigModel.newModel()
                .with("id", 2)
                .with("petId", 1)
                .with("quantity", 1);

        template.render(model);// gives the full json response

        String postApi = " https://petstore.swagger.io/v2/store/order";



        HttpResponse <String> jsonResponse = Unirest.post(postApi)
                .header("accept", "application/xml")
                .header("Content-Type", "application/json")
                .header("Content-Type", "application/json")
                .body("{ \"id\": 5, \"petId\":6, \"quantity\": 4, \"shipDate\": \"2019-01-31T08:04:56.297Z\", \"status\": \"placed\", \"complete\": false}")
                .asString();
        System.out.println(jsonResponse.getStatus() + " " +jsonResponse.getStatusText()+ " " +jsonResponse.getBody() );

    }



    @AfterClass
    public static void tearDown() {
      //  WebDriverContainer.getInstance().closeDriver();
    }
}
