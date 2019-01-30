import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class tryToSendMail {
    public static void main(String[] args) throws IOException {
        String httpsURL = "https://petstore.swagger.io/v2/store/order";

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/index.twig");
        JtwigModel model = JtwigModel.newModel()
                .with("id", 2)
                .with("petId", 1)
                .with("quantity", 1);
        // byte[] postData = template.render(model).toString().getBytes();

        String data = template.render(model);// gives the full json response

        URL myurl = new URL(httpsURL);
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("accept", "application/xml");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);

        DataOutputStream output = new DataOutputStream(con.getOutputStream());


        output.writeBytes(data);

        output.close();

        DataInputStream input = new DataInputStream(con.getInputStream());


        for (int c = input.read(); c != -1; c = input.read())
            System.out.print((char) c);
        input.close();

        System.out.println("Resp Code:" + con.getResponseCode());
        System.out.println("Resp Message:" + con.getResponseMessage());
    }
}
