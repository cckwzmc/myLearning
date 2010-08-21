package com.lyxmq.osgi.remote.dictquery;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.lyxmq.osgi.dictquery.query.QueryService;

public class Activator implements BundleActivator {
	private ServiceRegistration sr = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		sr = context.registerService(QueryService.class.getName(), new RemoteDictQueryServiceImpl(), null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		sr.unregister();
	}

}
