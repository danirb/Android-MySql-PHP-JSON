package com.example.testdatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	//TextView tvDisplay;
	
	ListView getAllCustomerListView;
	
	private JSONArray jsonarray;
	
	EditText inputsearch;
	
	GetAllCustomerListViewAdapter adapter ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		inputsearch = (EditText) findViewById(R.id.inputSearch);
		//tvDisplay = (TextView) findViewById(R.id.tvDisplay);
		
		getAllCustomerListView = (ListView) findViewById(R.id.getAllCustomerListView);
		
		new GetAllUsers().execute(new ApiConnector());
		
		getAllCustomerListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				try{
				JSONObject itemclicked = jsonarray.getJSONObject(position);
				int recipeid = itemclicked.getInt("id");
				
				Intent intent = new Intent(MainActivity.this , RecipeDetails.class);
				intent.putExtra("recipeid", recipeid);
				startActivity(intent);
				}
				
				catch(JSONException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/*
	public void setTextToTextView(JSONArray jsonArray)
	{
		String result = "";
		try{
			JSONObject jobject = null;
			
				for(int i=0;i<jsonArray.length();i++)
				{
					
						jobject = jsonArray.getJSONObject(i);
						result = result+
							"id : "+ jobject.getString("id") + "\n" +
							"user : "+jobject.getString("name") + "\n" +
							"phone : "+jobject.getString("ingredient") + "\n" +
							"details "+jobject.getString("detail")+"\n\n";
						
				}

				//Log.d("Result: ", result);
				
				tvDisplay.setText(result);
		}
		
		catch(JSONException e)
		{
			//Log.d("Error Exception: ", e.getMessage());
			 e.printStackTrace();
		}
		
		catch(Exception e)
		{
			//Log.d("Error Exception: ", e.getMessage());
			 e.printStackTrace();
		}
		
		
	}
	*/

	private class GetAllUsers extends AsyncTask<ApiConnector, Long, JSONArray>
	{
		
		ProgressDialog pdialog; // creates progress dialog
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			// set progress dialog propertied
			pdialog = new ProgressDialog(MainActivity.this);
			pdialog.setMessage("Fetching Recipe List");
			
			
			pdialog.setIndeterminate(false);
			pdialog.setCancelable(false);
			
			pdialog.show();
		}

		

		@Override
		protected JSONArray doInBackground(ApiConnector... params) {
			
			return params[0].getAllUsers();
		}
		
		
		@Override
		protected void onPostExecute(JSONArray result) {
			
		//	setTextToTextView(result);
			
			
			setListAdapter(result);
			pdialog.dismiss();
			

		}
		
		

	}

	public void setListAdapter(JSONArray jsonarray) {
		this.jsonarray = jsonarray;
		
		adapter = new GetAllCustomerListViewAdapter(jsonarray, this);
		
		getAllCustomerListView.setAdapter(adapter);
		
		inputsearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		//getAllCustomerListView.setAdapter(new GetAllCustomerListViewAdapter(jsonarray, this));
		
		
	}



}
