package com.lyxmq.osgi.dictqueryweb.http.servlet;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.lyxmq.osgi.dictquery.query.QueryService;

public class DictQueryHttpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2485589374632805039L;
	private BundleContext context = null;

	public DictQueryHttpServlet(BundleContext context) {
		this.context = context;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String queryWord = request.getParameter("query_word");
		response.setContentType("tex/html");
		ServletOutputStream output = response.getOutputStream();

		// 获取queryservice
		QueryService queryService = null;
		ServiceReference serviceRef = context.getServiceReference(QueryService.class.getName());
		if (serviceRef != null) {
			queryService = (QueryService) context.getService(serviceRef);
		}
		if (queryService == null) {
			output.println("No available dictquery service");
			output.close();
			return;
		}

		try {
			output.println("Result is " + queryService.queryWord(queryWord));
			output.close();
		} catch (Exception e) {
			output.println("Error occurs");
			output.println(e.toString());
			output.close();
			return;
		}
	}
}
