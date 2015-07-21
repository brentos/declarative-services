package com.redhat.examples.servicefactory.lookuptable.impl;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.redhat.examples.servicefactory.lookuptable.LookupTable;

@Component(configurationPolicy = ConfigurationPolicy.REQUIRE, servicefactory = true)
public class LookupTableImpl implements LookupTable {

	private Map<String, String> serviceProperties;
	
	
	@Activate
	public void start(final Map<String, String> properties) {
		this.serviceProperties = properties;
	}
	
	@Override
	public boolean propertyExists(String key) {
		
		boolean returnVal = false;
		if(serviceProperties == null) {
			returnVal = false;
		} else if (!serviceProperties.isEmpty() && serviceProperties.containsKey(key)) {
			returnVal = true;
		}
		return returnVal;
	}

	@Override
	public String getPropertyValue(String key) {

		if(serviceProperties != null) {
			return serviceProperties.get(key);
		}
		else {
			return null;
		}
	}

	
	
}
