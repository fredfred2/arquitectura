# Using jaxp.properties with SOAP

This file documents the steps needed to enable the processing of external XML schemas by the JVM. If you are using JDK 7 u40 or later, you must set the accessExternalSchema property to enable external schema processing. A detailed description of the options is documented at the following URL.

http://docs.oracle.com/javase/8/docs/api/javax/xml/XMLConstants.html#ACCESS_EXTERNAL_SCHEMA

If the property is not set, when you deploy a SOAP application you will encounter an error message similar to the following:

    Failed to read schema document 'xjc.xsd', because 'file' access is not allowed due to restriction set by the accessExternalSchema property.

## Using the jaxp.properties File

To set the accessExternalSchema property for a JVM, create a jaxp.properties file with the following key/value pair in the file.

javax.xml.accessExternalSchema=all


This file, must then copied to the following path: 
$JAVA_HOME/lib

For example, if the $JAVA_HOME was set to /usr/java/default/jre then the path to the jaxp.properties file would be: /usr/java/default/jre/lib/jaxp.properties.

For this class, the jaxp.properties file is included in the root directory for practice 5 and in the resources directory. Copy the file to the correct location on your system to proceed with the lab.

## Determining the Location of $JAVA_HOME

To determine the location of $JAVA_HOME for NetBeans, create something similar to the following example Main class.

public class Main {
    public static void main(String[] args) {
        System.out.println("Home: " + System.getProperty("java.home"));
    }  
}