package za.co.eltengroup.interceptor;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author thato on 2016/08/05.
 */
public class XmlFormatter {

    public XmlFormatter() {
    }

    public static String format(String unformattedXml) {
        try
        {
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "3");

            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(parseXml(unformattedXml));
            transformer.transform(source, result);
            return result.getWriter().toString();
        } catch (Exception e)
        {
            e.printStackTrace();
            return unformattedXml;
        }
    }

    private static Node parseXml(String unformattedXml) {
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(unformattedXml));
            return db.parse(is);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
