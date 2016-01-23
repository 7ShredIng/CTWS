package com.sevenshreding.ctws;

public class Route {
	private String Home = "Sempach, Hubelstrasse 10";
	private String Work = "Emmenbruecke,Unter%20Hueslen";
	private String Start, End = "";
	private boolean reverseRoute = false;
	
	public Route()
	{}
	
	public String getRoute()
	{
		if(!reverseRoute)
		{
			Start = Home;
			End = Work;
		}
		else
		{
			Start = Work;
			End = Home;
		}
		return "wp.0=" + Start + "&" + "wp.1=" + End;
	}

}