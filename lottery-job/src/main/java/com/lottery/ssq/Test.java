package com.lottery.ssq;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class Test {
    public static void main(String[] argv) throws Exception {
        //指定rul
        URL url = new URL(
                "http://topic.csdn.net/u/20090521/11/db336c07-2dbc-4732-8229-cb99fcb9d10e.html");
       
        HttpURLConnection connection = (java.net.HttpURLConnection)url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();
        DOMParser parser = new DOMParser();
        //这行代码等同于html页面中的<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        parser.setProperty("http://cyberneko.org/html/properties/default-encoding","utf-8");
        parser.parse(new InputSource(stream));
        Document doc = parser.getDocument();
        Node myNode= doc.getElementById("reply57194353_body");
        print(myNode, "");
    } 

    public static void print(Node node, String indent) {
        System.out.println(node.getTextContent());
    } 

}