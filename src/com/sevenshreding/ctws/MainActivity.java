package com.sevenshreding.ctws;

import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Typeface;
import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	private String serverURL = "http://dev.virtualearth.net/REST/v1/Routes/Driving?o=xml&wp.0=Sempach&wp.1=Emmenbruecke&key=Au_pU3dmpp8AOg23ltYz5_F80ABalBJD33ZFVycDp1d7ILUQxguXDL699S-KTmAj";
	private HandleXML obj;
	EditText Duration, Distance;
	final Button GetServerData = (Button)findViewById(R.id.GetServerData);
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Duration = (EditText)findViewById(R.id.Duration);
		Distance = (EditText)findViewById(R.id.Distance);
		
		setContentView(R.layout.traffic);
		GetServerData.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// connect to Bing Map Server
				
				obj = new HandleXML(serverURL);
				obj.fetchXML();
				
				// data to view
				while(obj.parsingComplete);
				Distance.setText(obj.getDistance());
				Duration.setText(obj.getDuration());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
