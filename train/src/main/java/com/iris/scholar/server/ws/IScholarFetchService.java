
/*
 * 
 */

package com.iris.scholar.server.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2
 * Fri Jun 19 18:08:24 CST 2009
 * Generated source version: 2.2
 * 
 */


@WebServiceClient(name = "IScholarFetchService", 
                  wsdlLocation = "http://localhost:8089/scholarws/service/IScholarFetchService?wsdl",
                  targetNamespace = "http://ws.server.scholar.iris.com") 
public class IScholarFetchService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://ws.server.scholar.iris.com", "IScholarFetchService");
    public final static QName IScholarFetchServiceHttpPort = new QName("http://ws.server.scholar.iris.com", "IScholarFetchServiceHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8089/scholarws/service/IScholarFetchService?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://localhost:8089/scholarws/service/IScholarFetchService?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public IScholarFetchService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public IScholarFetchService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IScholarFetchService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns IScholarFetchServicePortType
     */
    @WebEndpoint(name = "IScholarFetchServiceHttpPort")
    public IScholarFetchServicePortType getIScholarFetchServiceHttpPort() {
        return super.getPort(IScholarFetchServiceHttpPort, IScholarFetchServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IScholarFetchServicePortType
     */
    @WebEndpoint(name = "IScholarFetchServiceHttpPort")
    public IScholarFetchServicePortType getIScholarFetchServiceHttpPort(WebServiceFeature... features) {
        return super.getPort(IScholarFetchServiceHttpPort, IScholarFetchServicePortType.class, features);
    }

}