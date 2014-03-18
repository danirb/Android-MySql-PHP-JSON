package com.example.testdatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GetAllCustomerListViewAdapter extends BaseAdapter{
	
	JSONArray dataArray;
	Activity activity;
	
	private static LayoutInflater inflator = null;
	
	public GetAllCustomerListViewAdapter(JSONArray dataArray , Activity activity) 
	{
		this.dataArray = dataArray;
		this.activity = activity;
		
		inflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return dataArray.length();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ListCell cell;
		
		if(convertView == null)
		{
			convertView = inflator.inflate(R.layout.get_all_customer_list_view_cell, null);
			
			cell = new ListCell();
			
			cell.tvName = (TextView) convertView.findViewById(R.id.tvName);
			cell.tvIngredient = (TextView) convertView.findViewById(R.id.tvIngredient);
			
			convertView.setTag(cell);
		}
		
		else
		{
			cell = (ListCell) convertView.getTag();
			
		}
		
		// Changing data of cell
		try{
		JSONObject jobject = dataArray.getJSONObject(position);
		
		cell.tvName.setText(jobject.getString("name"));
		cell.tvIngredient.setText(jobject.getString("ingredient"));
		}
		
		catch(JSONException e)
		{
			e.printStackTrace();
			
		}
		
		
		return convertView;
	}
	
	private class ListCell
	{
		TextView tvName;
		TextView tvIngredient;
		
	}

}
