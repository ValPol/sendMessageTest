import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class tryToSendMail {
    public static void main(String[] args) throws IOException {
        try {
            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            URL url = new URL(null,"https://petstore.swagger.io/v2/store/order",  new sun.net.www.protocol.https.Handler());
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setRequestMethod("POST");
            connection.setFollowRedirects(true);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/index.twig");
            JtwigModel model = JtwigModel.newModel()
                    .with("id", 2)
                    .with("petId", 1)
                    .with("quantity", 1);

            String query = template.render(model);// gives the full json response

            connection.setRequestProperty("accept", "application/xml");
            connection.setRequestProperty("Content-Type", "application/json");

            DataOutputStream output = new DataOutputStream(connection.getOutputStream());


           output.writeBytes(query);
            output.close();

            System.out.println("Resp Code:" + connection.getResponseCode());
            System.out.println("Resp Message:" + connection.getResponseMessage());

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();


   /*         String httpsURL = "https://petstore.swagger.io/v2/store/order";

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


 //       HttpURLConnection httpConn = (HttpURLConnection)connection;
        InputStream is;
        if (con.getResponseCode() >= 400) {
            is = con.getErrorStream();
        } else {
            is = con.getInputStream();
        }
        //DataInputStream input = new DataInputStream(con.getInputStream());


      /*  for (int c = input.read(); c != -1; c = input.read())
            System.out.print((char) c);
        input.close();*/

     /*   System.out.println("Resp Code:" + con.getResponseCode());
        System.out.println("Resp Message:" + con.getResponseMessage());*/
        }
    }
}