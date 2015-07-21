package com.redhat.examples.servicefactory.lookuptable;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ManagedServiceFactoryComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagedServiceFactoryComponent.class);

    private List<LookupTable> services = new ArrayList<LookupTable>();

    @Activate
    public void start() {
        LOGGER.info("Starting the service");
    }

    @Deactivate
    public void stop() {
        LOGGER.info("Stopping the service");
    }

    private void printCurrentConfigList() {
        LOGGER.info("Printing the current configuration list: ");
        for (LookupTable lookupTable : services) {
            LOGGER.info("  Name: " + lookupTable.getPropertyValue("XYZ"));
        }
    }

    private void printUpdatedConfigList() {
        LOGGER.info("Printing the updated configuration list: ");
        for (LookupTable lookupTable : services) {
            LOGGER.info("  Name: " + lookupTable.getPropertyValue("XYZ"));
        }
    }

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public synchronized void setManagedService(LookupTable lookupTable) {
        if (!services.contains(lookupTable)) {
            printCurrentConfigList();
            LOGGER.info("Setting new configuration: " + lookupTable.getPropertyValue("XYZ"));
            services.add(lookupTable);
            printUpdatedConfigList();
        } else {
            LOGGER.info("lookupTable already exists: " + services.toString());
        }
    }

    public synchronized void unsetManagedService(LookupTable lookupTable) {
        if (services.contains(lookupTable)) {
            printCurrentConfigList();
            LOGGER.info("Removing configuration: " + lookupTable.getPropertyValue("XYZ"));
            services.remove(lookupTable);
            printUpdatedConfigList();
        } else {
            LOGGER.info("No matching lookupTable found: " + services.toString());
        }
    }
}
