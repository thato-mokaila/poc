package za.co.eltengroup.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import sun.misc.BASE64Encoder;
import za.co.eltengroup.domain.airtime.Credentials;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author thato on 2016/08/05.
 */
public class HttpMessageInterceptor implements ClientHttpRequestInterceptor{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT = "Accept";
    public static final String AUTHORIZATION = "Authorization";
    public static final String REALM = "WSSE realm";
    public static final String X_WSSE = "X-WSSE";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        String nonce = buildNonce();
        String createdAt = buildCreated();
        Credentials credentials = new Credentials();
        credentials.setSpId("1000010000820");
        credentials.setSpPassword("Ayo1234");
        credentials.setAlgorithm("MD5");

        HttpHeaders headers = request.getHeaders();
        headers.remove(CONTENT_TYPE);
        headers.add(CONTENT_TYPE, "application/xml");
        headers.add(ACCEPT, "application/xml");
        headers.add(AUTHORIZATION, REALM+"=\"SDP\", profile=\"UsernameToken\"");
        headers.add(X_WSSE, "UsernameToken Username=\""+credentials.getSpId()+"\", PasswordDigest=\""+digestPassword(nonce, createdAt, credentials.getSpPassword())+"\", Nonce=\""+nonce+"\", Created=\""+createdAt+"\"");

        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);

        return response;
    }

    public String buildNonce() {
        try {
            java.security.SecureRandom random = java.security.SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(System.currentTimeMillis());
            byte[] nonceBytes = new byte[16];
            random.nextBytes(nonceBytes);
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(nonceBytes), "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String  buildCreated() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df.format(Calendar.getInstance().getTime());
    }

    public String digestPassword(String nonce, String dateTime, String password) {

        try {

            //From the spec: Password_Digest = Base64 ( SHA-1 ( nonce + created + password ) )
            byte[] nonceBytes = nonce.getBytes("UTF-8");
            byte[] createdDateBytes = dateTime.getBytes("UTF-8");
            byte[] passwordBytes = password.getBytes("UTF-8");

            //SHA-1
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(nonceBytes);
            baos.write(createdDateBytes);
            baos.write(passwordBytes);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digestedPassword = md.digest(baos.toByteArray());

            //Encode the password
            return (new BASE64Encoder()).encode(digestedPassword);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        logger.info("*************************************** request *************************************** ");
        logger.info("## Headers : " + request.getHeaders());
        logger.info("## URI : " + request.getURI());
        logger.info("## Method : " + request.getMethod());
        logger.info(XmlFormatter.format(new String(body, "UTF-8")));
        logger.info("*************************************** request *************************************** ");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {

        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }

        logger.info("*************************************** response *************************************** ");
        logger.info("status code: " + response.getHeaders());
        logger.info("status code: " + response.getStatusCode());
        logger.info("status text: " + response.getStatusText());
        logger.info("payload: " + inputStringBuilder.toString());
        logger.info("*************************************** response *************************************** ");
    }
}
