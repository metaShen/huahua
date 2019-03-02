package cn.edu.modules.test.test;
/*package cn.gov.modules.test.test;

import org.apache.soap.*;
import org.apache.soap.rpc.*;

import java.net.*;
import java.util.Vector;

public class CaService {
	public static void main(String[] args) {
		System.out.println(getService("fff"));
	}

	public static String getService(String user) {
		URL url = null;
		try {
			url = new URL("http://222.76.243.149:9010/esb/esbproxy");
		} catch (MalformedURLException mue) {
			return mue.getMessage();
		}
		// This is the main SOAP object
		Call soapCall = new Call();
		// Use SOAP encoding
		soapCall.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
		// This is the remote object we're asking for the price
		soapCall.setTargetObjectURI("gov.xm.lb.queryZyjs_V_CB80");
		// This is the name of the method on the above object
		soapCall.setMethodName("gov.xm.si.ybgrzhye");
		Vector<Parameter> soapParams = new Vector<Parameter>();

		Parameter param1 = new Parameter("id0000", String.class, "3504037802033011",
				null);
		Parameter param2 = new Parameter("userpwd", String.class, "admin123",
				null);
//		Parameter param3 = new Parameter("aac001", String.class, "",
//				null);
//		Parameter param4 = new Parameter("rows", Integer.class, 1,
//				null);
//		Parameter param5 = new Parameter("cpage", Integer.class, 1,
//				null);
//		soapParams.addElement(isbnParam);
		soapParams.addElement(param1);
		soapParams.addElement(param2);
//		soapParams.addElement(param3);
//		soapParams.addElement(param4);
//		soapParams.addElement(param5);
	
		
		soapCall.setParams(soapParams);
		try {
			Response soapResponse = soapCall.invoke(url, "");
			
			System.out.println("soapResponse.getHeader();"+soapResponse.getHeader());
			System.out.println("soapResponse.getSOAPContext();"+soapResponse.getSOAPContext());
//			soapResponse.s
			if (soapResponse.generatedFault()) {
				Fault fault = soapResponse.getFault();
				String f = fault.getFaultString();
				return f;
			} else {
				// read result
				Parameter soapResult = soapResponse.getReturnValue();
				return soapResult.getValue().toString();
			}
		} catch (SOAPException se) {
			return se.getMessage();
		}
	}
}*/