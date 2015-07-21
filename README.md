#declarative-services

run mvn clean install for both projects (could use a parent pom)

install through the fuse CLI:
install -s mvn:com.redhat.examples.servicefactory.lookuptable/lookuptable/0.0.1-SNAPSHOT

install -s mvn:com.redhat.examples.servicefactory.lookuptable.impl/lookuptableimpl/0.0.1-SNAPSHOT

run the following:
scr:list

You should see two new components, LookupTableImpl and ManagedServiceFactoryComponent. LookupTableImpl should be "UNSATISFIED" because no configuration file exists in the etc directory.

Create a new config file in etc named com.redhat.examples.servicefactory.lookuptable.impl.LookupTableImpl-001.cfg with the following command:

echo "XYZ=abc123" > com.redhat.examples.servicefactory.lookuptable.impl.LookupTableImpl-001.cfg

This will create a new lookuptable instance and will print to the logs the value of XYZ.

running scr:list should then show that LookupTableImpl is not "ACTIVE".

Create another config file:

echo "XYZ=xyz789" > com.redhat.examples.servicefactory.lookuptable.impl.LookupTableImpl-002.cfg

This will print additional information to the logs. In addition, running the following command should return two components, one with XYZ=abc123 and one with XYZ=xyz789

scr:details com.redhat.examples.servicefactory.lookuptable.impl.LookupTableImpl


