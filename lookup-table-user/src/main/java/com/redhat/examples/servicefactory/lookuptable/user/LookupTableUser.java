package com.redhat.examples.servicefactory.lookuptable.user;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.examples.servicefactory.lookuptable.LookupTable;

@Component(configurationPolicy = ConfigurationPolicy.REQUIRE, servicefactory = true)
public class LookupTableUser {

	Logger LOGGER = LoggerFactory.getLogger(LookupTableUser.class);
	
	private LookupTable lookupTable;
	
	@Activate
	public void start(final Map<String, String> properties) {
		
		LOGGER.info("XYZ is set to : " + lookupTable.getPropertyValue("XYZ"));
		
	}

	@Reference
	public void setLookupTable(LookupTable lookupTable) {
		this.lookupTable = lookupTable;
	}
	
	
}
