package com.example.testdatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeDetails extends Activity{
	
	TextView tvRecipeDetailName;
	TextView tvRecipeDetailIngredient;
	TextView tvRecipeDetailDetail;
	
	int recipeid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_details);
		
			tvRecipeDetailName = (TextView) findViewById(R.id.tvRecipeDetailName);
			tvRecipeDetailIngredient = (TextView) findViewById(R.id.tvRecipeDetailIngredient);
			tvRecipeDetailDetail = (TextView) findViewById(R.id.tvRecipeDetailDetail);
			
			
			// Geting customer id from previous intent
			Intent intent = getIntent();
			recipeid = intent.getIntExtra("recipeid", -1);
			
			// correct customer id
			if(recipeid > 0)
			{
				new GetRecipeDetail().execute(new ApiConnector());
				
			}
			
			
	}
	
	private class GetRecipeDetail extends AsyncTask<ApiConnector, Long, JSONArray>
	{
		ProgressDialog pdialog; // creates progress dialog
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			// set progress dialog propertied
			pdialog = new ProgressDialog(RecipeDetails.this);
			pdialog.setMessage("Fetching Recipe Details");
			pdialog.setIndeterminate(false);
			pdialog.setCancelable(false);
			
			pdialog.show();
		}


		@Override
		protected JSONArray doInBackground(ApiConnector... params) {
			
			return params[0].getRecipeDetail(recipeid);
		}
		
		
		@Override
		protected void onPostExecute(JSONArray result) {
			
			try {
				JSONObject jobject = result.getJSONObject(0);
				
				tvRecipeDetailName.setText(jobject.getString("name"));
				tvRecipeDetailIngredient.setText(jobject.getString("ingredient"));
				tvRecipeDetailDetail.setText(jobject.getString("detail"));
				
				pdialog.dismiss();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

}
