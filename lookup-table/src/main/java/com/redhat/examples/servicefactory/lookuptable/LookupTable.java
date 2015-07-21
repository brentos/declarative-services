package com.redhat.examples.servicefactory.lookuptable;

public interface LookupTable {
	
	boolean propertyExists(String key);
	
	String getPropertyValue(String key);
	
}
