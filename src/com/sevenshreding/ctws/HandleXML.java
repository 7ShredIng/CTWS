package com.sevenshreding.ctws;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HandleXML {
	private String Distance;
	private String Duration;
	
	private String Url = null;
	private XmlPullParserFactory xmlFactoryObject;
	public volatile boolean parsingComplete = true;
	
	public HandleXML(String Url)
	{
		this.Url = Url;
	}
	
	public String getDistance()
	{
		return Distance;
	}
	
	public String getDuration()
	{
		return Duration;
	}
	
	public void parseXMLAndStoreIt(XmlPullParser myParser)
	{
		int event;
		String text = null;
		
		try{
			event = myParser.getEventType();
			while(event != XmlPullParser.END_DOCUMENT)
			{
				String name = myParser.getName();
				switch(event)
				{
					case XmlPullParser.START_TAG:
						break;
					case XmlPullParser.TEXT:
						text = myParser.getText();
						break;
					case XmlPullParser.END_TAG:
						if(name.equals("Duration"))
						{
							Duration = text;
						}
						else if(name.equals("Distance"))
						{
							Distance = text;
						}
						else
						{}
						break;
				}
				event = myParser.next();
			}
			parsingComplete = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void fetchXML()
	{
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run()
			{
				try
				{
					URL url = new URL(Url);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					//connection.setReadTimeout(10000);
					//connection.setConnectTimeout(15000);
					//connection.setRequestMode("GET");
					//connection.setDoInput(true);
					//connection.connect();
					
		            InputStream stream = connection.getInputStream();
		            xmlFactoryObject = XmlPullParserFactory.newInstance();
		            XmlPullParser myparser = xmlFactoryObject.newPullParser();
		               
		            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
		            myparser.setInput(stream, null);
		            
		            parseXMLAndStoreIt(myparser);
		            
		            stream.close();
		            connection.disconnect();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
