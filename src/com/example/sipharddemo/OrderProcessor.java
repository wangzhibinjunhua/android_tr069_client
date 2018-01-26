//package com.example.sipharddemo;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Enumeration;
//import java.util.Hashtable;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Vector;
//
//// SOAP imports
//import org.apache.soap.Constants;
//import org.apache.soap.Envelope;
//import org.apache.soap.Fault;
//import org.apache.soap.SOAPException;
//import org.apache.soap.encoding.SOAPMappingRegistry;
//import org.apache.soap.encoding.soapenc.BeanSerializer;
//import org.apache.soap.rpc.Call;
//import org.apache.soap.rpc.Parameter;
//import org.apache.soap.rpc.Response;
//import org.apache.soap.rpc.SOAPContext;
//import org.apache.soap.util.xml.QName;
///**
//*
//* OrderProcessor is a web services client, and uses
//* SOAP-RPC to interact with web services. However, it is also a
//* web service, and receives SOAP messages and uses them to then
//* construct SOAP-RPC calls, as well as sending return messages to
//* its own web service clients.
//*
//*/
//public class OrderProcessor {
///** Mapping for CD class */
//private SOAPMappingRegistry registry;
///** The serializer for the CD class */
//private BeanSerializer serializer;
///** The RPC Call object */
//private Call call;
///** Parameters for call */
//private Vector params;
///** Response from RPC call */
//private Response rpcResponse;
///** The URL to connect to */
//private URL rpcServerURL;
///**
//*
//* This will set up initial default values.
//*
//*/
//public void initialize() {
//// Set up internal URL for SOAP-RPC
//try {
//rpcServerURL =
//new URL("http://localhost:8080/soap/servlet/rpcrouter");
//} catch (MalformedURLException neverHappens) {
//// ignored
//}
//// Set up a SOAP mapping to translate CD objects
//registry = new SOAPMappingRegistry();
//serializer = new BeanSerializer();
//registry.mapTypes(Constants.NS_URI_SOAP_ENC,
//new QName("urn:cd-catalog-demo", "cd"),
//CD.class, serializer, serializer);
//// Build a Call to the internal SOAP service
//call = new Call();
//call.setSOAPMappingRegistry(registry);
//call.setTargetObjectURI("urn:cd-catalog");
//call.setMethodName("getCD");
//call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
//// Set up input
//params = new Vector();
//}
///**
//*
//* This method receives SOAP messages addressed to this web service,
//* decodes those messages, and sends them to another web service
//* via SOAP-RPC.
//*
//*
//* @param env the SOAP envelope carrying the SOAP message.
//* @param req the SOAP request variable.
//* @param res the SOAP response variable.
//* @throws IOException - when errors result in connecting
//* to the SOAP-RPC service.
//* @throws MessagingException - when errors occur
//* in sending and receiving SOAP messages.
//*/
//public void purchaseOrder(Envelope env, SOAPContext req, SOAPContext res)
//throws IOException, MessagingException {
//// Set up SOAP environment
//initialize();
//// Set up list of CDs successfully ordered
//List orderedCDs = new LinkedList();
//// Set up hashtable of failed orders
//Hashtable failedCDs = new Hashtable();
//// Get the purchaseOrder element - always the first body entry
//Vector bodyEntries = env.getBody().getBodyEntries();
//Element purchaseOrder = (Element)bodyEntries.iterator().next();
//// In a real application, do something with the buyer information
//// Get the CDs ordered
//Element order =
//(Element)purchaseOrder.getElementsByTagName("order").item(0);
//NodeList cds = order.getElementsByTagName("cd");
//// Loop through each ordered CD from the PO request
//for (int i=0, len=cds.getLength(); i
//Element cdElement = (Element)cds.item(i);
//String artist = cdElement.getAttribute("artist");
//String title = cdElement.getAttribute("title");
//// Set up input
//params.clear();
//params.addElement(new Parameter("title", String.class, title, null));
//call.setParams(params);
//try {
//// Invoke the call
//rpcResponse = call.invoke(rpcServerURL, "");
//if (!rpcResponse.generatedFault()) {
//Parameter returnValue = rpcResponse.getReturnValue();
//CD cd = (CD)returnValue.getValue();
//// See if the CD is available
//if (cd == null) {
//failedCDs.put(title, "Requested CD is not available.");
//continue;
//}
//// Verify it's by the right artist
//if (cd.getArtist().equalsIgnoreCase(artist)) {
//// Add this CD to the successful orders
//orderedCDs.add(cd);
//} else {
//// Add this to the failed orders
//failedCDs.put(title, "Incorrect artist for specified CD.");
//}
//} else {
//Fault fault = rpcResponse.getFault();
//failedCDs.put(title, fault.getFaultString());
//}
//} catch (SOAPException e) {
//failedCDs.put(title, "SOAP Exception: " + e.getMessage());
//}
//}
//// At the end of the loop, return something useful to the client
//Do*****ent doc = new org.apache.xerces.dom.Do*****entImpl();
//Element response = doc.createElement("response");
//Element orderedCDsElement = doc.createElement("orderedCDs");
//Element failedCDsElement = doc.createElement("failedCDs");
//response.appendChild(orderedCDsElement);
//response.appendChild(failedCDsElement);
//// Add the ordered CDs
//for (Iterator i = orderedCDs.iterator(); i.hasNext(); ) {
//CD orderedCD = (CD)i.next();
//Element cdElement = doc.createElement("orderedCD");
//cdElement.setAttribute("title", orderedCD.getTitle());
//cdElement.setAttribute("artist", orderedCD.getArtist());
//cdElement.setAttribute("label", orderedCD.getLabel());
//orderedCDsElement.appendChild(cdElement);
//}
//// Add the failed CDs
//Enumeration keys = failedCDs.keys();
//while (keys.hasMoreElements()) {
//String title = (String)keys.nextElement();
//String error = (String)failedCDs.get(title);
////Element failedElement = doc.createElement("failedCD");
//failedElement.setAttribute("title", title);
//failedElement.appendChild(doc.createTextNode(error));
//failedCDsElement.appendChild(failedElement);
//}
//// Set this as the content of the envelope
//bodyEntries.clear();
//bodyEntries.add(response);
//StringWriter writer = new StringWriter();
//env.marshall(writer, null);
//// Send the envelope back to the client
//res.setRootPart(writer.toString(), "text/xml");
//}
//}