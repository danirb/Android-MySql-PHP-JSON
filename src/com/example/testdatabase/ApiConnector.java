package com.example.testdatabase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class ApiConnector {
	
	String url;
	
	String name;
	String ingredient;
	String detail;
	
	public JSONArray getAllUsers()
	{
		// String url = "http://10.0.2.2//phpacademy/android/Tutorial/getAllCustomers.php";
		// url = "http://192.168.1.100/phpacademy/android/Recipe/getAllRecipe.php";
		
		url = "http://surajdubey.byethost7.com/recipe/getAllRecipe.php";
		
		
		HttpEntity httpEntity = null;
		JSONArray jsonArray = null;
		
		try{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			 //HttpGet httpGet = new HttpGet(url);
			
			HttpPost httpGet = new HttpPost(url);
			
			HttpResponse httpResponse = httpclient.execute(httpGet);
			
			httpEntity = httpResponse.getEntity();
			
			
			if(httpEntity != null)
			{
				
					
					String entityResponse = EntityUtils.toString(httpEntity);
					
					Log.d("1Response: ",entityResponse);
					
					jsonArray = new JSONArray(entityResponse);
					
				
		
			}
		  } // try
		
		catch(ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ParseException e) {
				e.printStackTrace();
		}
		catch (JSONException e) {
				e.printStackTrace();
		}
		
		return jsonArray;
		
	}
	
	public JSONObject insertRecipe()
	{
		//JSONArray jsonarray = null;
		JSONObject jsonobject = null;
		// url = "http://192.168.1.100/phpacademy/android/Recipe/addRecipe.php";
		
		url = "http://surajdubey.byethost7.com/recipe/addRecipe.php";
		try{
			
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			
			List<NameValuePair> namevaluepair = new ArrayList<NameValuePair>();
			namevaluepair.add(new BasicNameValuePair("name", name));
			namevaluepair.add(new BasicNameValuePair("ingredient", ingredient));
			namevaluepair.add(new BasicNameValuePair("detail", detail));
			
			httppost.setEntity(new UrlEncodedFormEntity(namevaluepair));
			
			HttpResponse response = httpclient.execute(httppost);
			
			HttpEntity httpEntity = response.getEntity();
			
			if(httpEntity!=null)
			{
				String entityRespone = EntityUtils.toString(httpEntity);
				
				Log.d("Insert response",entityRespone);
				
				//jsonarray = new JSONArray(entityRespone);
				jsonobject = new JSONObject(entityRespone);
			}
			
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return jsonobject;
		
	}
	
	public void setValues(String name,String ingredient,String detail)
	{
		this.name = name;
		this.detail = detail;
		this.ingredient = ingredient;
		
	}
	
	public JSONArray getRecipeDetail(int id)
	{
		// String url = "http://10.0.2.2//phpacademy/android/Tutorial/getAllCustomers.php";
		//url = "http://192.168.1.100/phpacademy/android/Recipe/getRecipeDetail.php";
		
		url = "http://surajdubey.byethost7.com/recipe/getRecipeDetail.php";
		
		
		HttpEntity httpEntity = null;
		JSONArray jsonArray = null;
		
		try{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			 //HttpGet httpGet = new HttpGet(url);
			
			HttpPost httppost = new HttpPost(url);
			
			List<NameValuePair> namevalue = new ArrayList<NameValuePair>();
			
			namevalue.add(new BasicNameValuePair("recipeid", Integer.toString(id)));
			httppost.setEntity(new UrlEncodedFormEntity(namevalue));
			
			HttpResponse httpResponse = httpclient.execute(httppost);
			
			httpEntity = httpResponse.getEntity();
			
			
			if(httpEntity != null)
			{
				
					
					String entityResponse = EntityUtils.toString(httpEntity);
					
					Log.d("1Response: ",entityResponse);
					
					jsonArray = new JSONArray(entityResponse);
					
				
		
			}
		  } // try
		
		catch(ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ParseException e) {
				e.printStackTrace();
		}
		catch (JSONException e) {
				e.printStackTrace();
		}
		
		return jsonArray;
	}
	
	

}
