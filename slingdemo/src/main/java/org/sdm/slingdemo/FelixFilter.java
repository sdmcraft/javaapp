package org.sdm.slingdemo;

import org.apache.felix.http.api.ExtHttpService;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.osgi.framework.BundleContext;

@Component
public class FelixFilter {
	
	@Reference
	ExtHttpService extHttpService;
	
	@Activate
	public void activate(BundleContext ctx)
	{
		//extHttpService.registerFilter(new SlingFilter(), arg1, arg2, arg3, arg4)
	}

}
