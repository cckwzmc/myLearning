package org.webservice.client;
import org.cyberneko.html.parsers.DOMParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.InputStream;
import java.io.IOException;

public class Html2XML {
    private int connectionTimeout = 5000;
    private int soTimeout = 12000;
    private String proxyHost = null;
    private int proxyPort;

    public  Document getDocument(String url) {
        HttpClient client = new HttpClient();
        if (proxyHost != null) {
            client.getHostConfiguration().setProxy(proxyHost, proxyPort);
        }
        client.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
        client.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
        GetMethod method = new GetMethod(url);
        method.addRequestHeader("Content-Type", "text/html; charset=utf-8");
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                throw new HttpException("HttpStatusCode : " + statusCode);
            }
            InputStream is = method.getResponseBodyAsStream();
            DOMParser parser = new DOMParser();
            parser.setProperty("http://cyberneko.org/html/properties/default-encoding", "utf-8");
            parser.parse(new InputSource(is));
            return (parser.getDocument());
        } catch (HttpException he) {
            he.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
    	Html2XML test=new Html2XML();
    	Document doc=test.getDocument("http://www.sina.com.cn");
    	System.out.println(doc.toString());
    	
	}
}