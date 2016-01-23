package com.sevenshreding.ctws;

import java.net.*;
import java.io.*;

//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;

public class Connection {
	public Connection(){}
	
	private final String RestUrlBase = "http://dev.virtualearth.net/REST/v1/Routes/Driving";
	private final String bingMapsKey = "Au_pU3dmpp8AOg23ltYz5_F80ABalBJD33ZFVycDp1d7ILUQxguXDL699S-KTmAj";

	private URL requestURL;
	private String url = RestUrlBase;
	
	public String sayHello()
	{
		try {
			connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "hello";
	}
	
	public void connect() throws IOException
	{
		try{
			buildRequest();
			URLConnection connection = requestURL.openConnection();
			InputStream inStream = connection.getInputStream();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(inStream));
			String inputLine, result= "";
			while((inputLine = bufReader.readLine()) != null)
			{
				result += inputLine;
			}
			bufReader.close();
			inStream.close();
		}
		catch(IOException ex)
		{
			System.out.print("Error in connection with Bing Server. " + ex.toString());
		}
	}
	
	private void buildRequest()
	{
		Route route = new Route();
		//output
		url += "?o=xml";
		url += "&";
		//Route
		url += route.getRoute();
		url += "&";
		//Key
		url += "key=" + bingMapsKey;
	}
}