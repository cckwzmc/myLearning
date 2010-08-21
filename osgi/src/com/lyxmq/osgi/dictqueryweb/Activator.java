package com.lyxmq.osgi.dictqueryweb;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

import com.lyxmq.osgi.dictqueryweb.http.servlet.DictQueryHttpServlet;

public class Activator implements BundleActivator, ServiceListener {
	private ServiceReference serviceRef = null;
	private BundleContext bundleContext = null;
	private Servlet servlet;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		servlet = new DictQueryHttpServlet(this.bundleContext);
		registerServlet();
		context.addServiceListener(this, "(objectClass=" + HttpService.class.getName() + ")");
	}

	private void registerServlet() {
		if (serviceRef == null) {
			serviceRef = this.bundleContext.getServiceReference(HttpService.class.getName());
		}
		if (serviceRef != null) {
			HttpService service = (HttpService) bundleContext.getService(serviceRef);
			if (service != null) {
				try {
					service.registerServlet("/demo/dictquery", servlet, null, null);
					service.registerResources("/demo/pages", "pages", null);
					System.out.println("已启动字典查询web模块，请通过/demo/pages/dictquery.htm访问");
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (NamespaceException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		try {
			// 注销Servlet
			unregisterServlet();
		} catch (Throwable t) {
			t.printStackTrace();
		}

		servlet = null;
		bundleContext = null;
		serviceRef = null;
	}

	private void unregisterServlet() {
		if (serviceRef != null) {
			try {
				HttpService http = (HttpService) bundleContext.getService(serviceRef);
				if (null != http) {
					http.unregister("/demo/dictquery");
					http.unregister("/demo/page");
					System.out.println("已卸载字典查询web模块！");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		switch (event.getType()) {
		case ServiceEvent.REGISTERED:
			// HttpService注册到OSGi容器的时候，进行Servlet的注册
			registerServlet();
			break;

		case ServiceEvent.UNREGISTERING:
			// HttpService从OSGi容器注销的时候，注销Servlet
			unregisterServlet();
			break;
		}
	}

}
