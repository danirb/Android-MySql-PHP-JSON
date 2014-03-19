package com.example.testdatabase;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddRecipeActivity extends Activity {
	

	Context context = this;
	
	EditText etName;
	EditText etIngredient;
	EditText etDetail;
	
	Button btnAdd;

	String name;
	String ingredient;
	String detail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_recipe);

		etName = (EditText) findViewById(R.id.etName);
		etIngredient = (EditText) findViewById(R.id.etIngredient);
		etDetail = (EditText) findViewById(R.id.etDetails);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				name = etName.getText().toString();
				ingredient = etIngredient.getText().toString();
				detail = etDetail.getText().toString();
				
				ApiConnector apiconnector = new ApiConnector();
				apiconnector.setValues(name, ingredient, detail);
				new InsertRecipe().execute(apiconnector);
				
				
				//new InsertRecipe().execute(new ApiConnector());
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_recipe, menu);
		return true;
	}

	private class InsertRecipe extends AsyncTask<ApiConnector, Long, JSONObject>
	{

		ProgressDialog pdialog; // creates progress dialog
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			// set progress dialog propertied
			pdialog = new ProgressDialog(context);
			pdialog.setMessage("Creating Recipe");
			pdialog.setIndeterminate(false);
			pdialog.setCancelable(false);
			
			pdialog.show();
		}

		@Override
		protected JSONObject doInBackground(ApiConnector... params) {
			
			
			return params[0].insertRecipe();
			
		}
		
		
		@Override
		protected void onPostExecute(JSONObject jsonobject) {
			
			try{
			String result = "";
			
			JSONObject jobject = null;
			
				result = jsonobject.getString("success");
				//jobject= jsonarray.getJSONObject(0);
				
				Log.d("Operation Result", result);
				
				pdialog.dismiss(); // dismiss progress dialog
				
				AlertDialog.Builder alert = new AlertDialog.Builder(context );
				
				alert.setTitle("Confirmation");
				alert.setMessage("Recipe added successfully!!!");
				

				alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						
						Intent intent = new Intent(context , WelcomeActivity.class);
						startActivity(intent);
					}
				});
				
				AlertDialog dialog = alert.create();
				dialog.show();
			}
			
			catch(JSONException e)
			{
				e.printStackTrace();
			}
			
		}
		
	}


}
