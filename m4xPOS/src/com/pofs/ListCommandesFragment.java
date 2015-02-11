package com.pofs;


import java.util.ArrayList;
import java.util.List;

import com.mqm0.m4xPOS.R;
import com.pofs.adapters.CommandeAdapter;
import com.pofs.metiers.Commands;
import com.pofs.metiers.DBAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


@SuppressLint("NewApi") public class ListCommandesFragment extends Fragment {

	DBAdapter myDB;
	CommandeAdapter creatingAdapter;
	CommandeAdapter CA;
	
	static ListView listCommandes;
	
	static float total= 0;
	static float numb= 0;
	static long rowIds= 0;
	
	Editable str;
	
	String Str;
	
	AlertDialog.Builder alertDialog;
	
	EditText totalHint;
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
 
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		openDB();

		final View view = inflater.inflate(R.layout.list_commandes_fragment,container, false);
		totalHint= (EditText)view.findViewById(R.id.editTextTotal);
		
		//Alert dialog calling for deleting something after long click
		alertDialog = new AlertDialog.Builder(getActivity());
		alertDialog.setTitle("DELETE ITEM");
		alertDialog.setMessage("Do you wanna really delete this command ?");
		
		alertDialog.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				myDB.deleteRowFromCommands(rowIds);
				rowIds= 0;
				refresh();
				Toast.makeText(getActivity(), "Succesfully deleted", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
			}
		});
		
		

		
		totalHint= (EditText)view.findViewById(R.id.editTextTotal);
		totalHint.setHint(Float.toString(0)+ " DZD");
		
		str= totalHint.getText();

		
		Button clrEditTxt= (Button)view.findViewById(R.id.crossIcon);
		clrEditTxt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
                 
				totalHint.setText(null);
				totalHint.setHint(null);
			}
		});
		
		
		//Buttons
		Button add=(Button)view.findViewById(R.id.ButtonAdd);
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Editable str= totalHint.getText();
				String Str= str.toString();
				if(rowIds!= 0){
					int newQuantity= Integer.parseInt(Str);
						Cursor cursor= myDB.getRowFromCommands(rowIds), cursorRemaining= myDB.getAllRowsFromProducts();
						cursorRemaining.moveToFirst();
						while(!cursorRemaining.isAfterLast()&& cursor.getInt(DBAdapter.COL_CODE)!= cursorRemaining.getInt(DBAdapter.COL_CODE)){
							cursorRemaining.moveToNext();
						}
						if(newQuantity >= cursorRemaining.getInt(DBAdapter.COL_QUANTITY))
							Toast.makeText(getActivity(), Integer.toString(cursorRemaining.getInt(DBAdapter.COL_QUANTITY))+ " Reamaining !", Toast.LENGTH_SHORT).show();
						else{
							myDB.updateRowOnCommands(rowIds, cursor.getInt(DBAdapter.COL_CODE), cursor.getString(DBAdapter.COL_NAME), cursor.getFloat(DBAdapter.COL_PRICE), ++newQuantity);
							totalHint.setText(Integer.toString(newQuantity));
						}
						
						
					}
					else
						Toast.makeText(getActivity(), "Select an Item to update", Toast.LENGTH_SHORT).show();
					refresh();
					
				}
		});
		
		
		Button soustract=(Button)view.findViewById(R.id.ButtonSoustract);
		soustract.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Editable str= totalHint.getText();
				String Str= str.toString();
				if(rowIds!= 0){
				    int newQuantity= Integer.parseInt(Str);
					if(newQuantity > 0){
							Cursor cursor= myDB.getRowFromCommands(rowIds);
							myDB.updateRowOnCommands(rowIds, cursor.getInt(DBAdapter.COL_CODE), cursor.getString(DBAdapter.COL_NAME), cursor.getFloat(DBAdapter.COL_PRICE), --newQuantity);
							totalHint.setText(Integer.toString(newQuantity));
					}
					else
						alertDialog.show();
				    	refresh();
				}
				else
					Toast.makeText(getActivity(), "Select an Item to update", Toast.LENGTH_SHORT).show();
				
			}
		});

		Button zero=(Button)view.findViewById(R.id.Button00);
		zero.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				str= totalHint.getText();
				Str= str.toString();
				totalHint.setText(Str+"0");
			}
		});	
		
		Button one=(Button)view.findViewById(R.id.Button01);
		one.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				str= totalHint.getText();
				Str= str.toString();
				totalHint.setText(Str+"1");
			}
		});
		
		Button two=(Button)view.findViewById(R.id.Button02);
		two.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				str= totalHint.getText();
				Str= str.toString();
				totalHint.setText(Str+"2");
			}
		});
		
		Button three=(Button)view.findViewById(R.id.Button03);
		three.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				str= totalHint.getText();
				Str= str.toString();
				totalHint.setText(Str+"3");
			}
		});
		
		Button four=(Button)view.findViewById(R.id.Button04);
		four.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				str= totalHint.getText();
				Str= str.toString();
				totalHint.setText(Str+"4");
			}
		});
		
		Button five=(Button)view.findViewById(R.id.Button05);
		five.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				str= totalHint.getText();
				Str= str.toString();
				totalHint.setText(Str+"5");
			}
		});
		
		Button six=(Button)view.findViewById(R.id.Button06);
		six.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				str= totalHint.getText();
				Str= str.toString();
				totalHint.setText(Str+"6");
			}
		});
		
		Button seven=(Button)view.findViewById(R.id.Button07);
		seven.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				str= totalHint.getText();
				Str= str.toString();
				totalHint.setText(Str+"7");
			}
		});
		
		Button eight=(Button)view.findViewById(R.id.Button08);
		eight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				str= totalHint.getText();
				Str= str.toString();
				totalHint.setText(Str+"8");
			}
		});
		
		Button nine=(Button)view.findViewById(R.id.Button09);
		nine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				str= totalHint.getText();
				Str= str.toString();
				totalHint.setText(Str+"9");
			}
		});
		
		Button clear=(Button)view.findViewById(R.id.ButtonPurge);
		clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				clear();
			}
		});
		
		
		Button check= (Button)view.findViewById(R.id.ButtonCheck);
		check.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				refresh();
				totalHint.setText(null);
				totalHint.setHint(Float.toString(calculateTotal())+ " DZD");
			}
		});
		
		
		
		listCommandes = (ListView)view.findViewById(R.id.list_pays);

		creatingAdapter= new CommandeAdapter(listCommandes, getCommands());
		listCommandes.setAdapter(creatingAdapter);
		creatingAdapter.notifyDataSetChanged();
		
		GradientDrawable background = (GradientDrawable) getResources()
		                .getDrawable(R.drawable.rounded_rect);
	    background.setColor(getResources().getColor(R.color.android_green));
		        
	    
	    //Validation of sells 
	    final MediaPlayer mp= MediaPlayer.create(getActivity(), R.raw.bell);
	    Button validate= (Button)view.findViewById(R.id.buttonValidate);
	    validate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Editable str= totalHint.getText();
				String Str= str.toString();
				if(Str.isEmpty()){
					Toast.makeText(getActivity(), "You've to set a value", Toast.LENGTH_SHORT).show();
				}
				else{
					int newQuantity= Integer.parseInt(Str);
					if(rowIds!= 0){
						Cursor cursor= myDB.getRowFromCommands(rowIds), cursorRemaining= myDB.getAllRowsFromProducts();
						cursorRemaining.moveToFirst();
							while(!cursorRemaining.isAfterLast()&& cursor.getInt(DBAdapter.COL_CODE)!= cursorRemaining.getInt(DBAdapter.COL_CODE)){
								cursorRemaining.moveToNext();
							}
						if(newQuantity > cursorRemaining.getInt(DBAdapter.COL_QUANTITY)){
							
							Toast.makeText(getActivity(), Integer.toString(cursorRemaining.getInt(DBAdapter.COL_QUANTITY))+ " Reamaining !", Toast.LENGTH_SHORT).show();
							mp.start();
						}
							
						else{
							myDB.updateRowOnCommands(rowIds, 
									cursor.getInt(DBAdapter.COL_CODE), 
									cursor.getString(DBAdapter.COL_NAME), 
									cursor.getFloat(DBAdapter.COL_PRICE), 
									newQuantity);
						}
						totalHint.setText(null);
						totalHint.setHint(Float.toString(calculateTotal())+ " DZD");
						rowIds= 0;
						refresh();
					}
					else
						Toast.makeText(getActivity(), "Select an Item to update", Toast.LENGTH_SHORT).show();
					
				}
			}
		});
	    
	    listCommandes.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
				
				Cursor c= myDB.getAllRowsFromCommands();
				int count= 0;
				if(c!= null && c.moveToFirst()){
					while(count!= position){
						count++;
						c.moveToNext();
					}
					
				rowIds= c.getLong(DBAdapter.COL_ROWID);
				
				if(c.getInt(DBAdapter.COL_CODE)< 60 && ProductsFragment.quantityProductsYaourt[position]!= 0)
					ProductsFragment.quantityProductsYaourt[position]= 0;
				else if(c.getInt(DBAdapter.COL_CODE)>= 60 && c.getInt(DBAdapter.COL_CODE)< 70 && ProductsFragment.quantityProductsFromage[position]!= 0)
					ProductsFragment.quantityProductsFromage[position]= 0;
				else if(c.getInt(DBAdapter.COL_CODE)> 70 && ProductsFragment.quantityProductsLait[position]!= 0)
					ProductsFragment.quantityProductsLait[position]= 0;
				c.close();
				}
				
				alertDialog.show();

				return false;
			}
		});	   
	    
	    
		listCommandes.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id){
				
				Cursor cursor= null;
				int count= 0;
				switch (position) {
				default:{
					if(ProductsFragment.trace[position].equals(ProductsFragment.famille[0])){
						cursor= myDB.getAllRowsFromCommands();
						if(cursor!= null && cursor.moveToFirst()){
							while(count!= position){
								count++;
								cursor.moveToNext();
							}
							
						rowIds= cursor.getLong(DBAdapter.COL_ROWID);
					}
					}
					else if(ProductsFragment.trace[position].equals(ProductsFragment.famille[1])){
							cursor= myDB.getAllRowsFromCommands();
							if(cursor!= null && cursor.moveToFirst()){
								while(count!= position){
									count++;
									cursor.moveToNext();
								}
								
							rowIds= cursor.getLong(DBAdapter.COL_ROWID);
						}
					}
					else if(ProductsFragment.trace[position].equals(ProductsFragment.famille[2])){
							cursor= myDB.getAllRowsFromCommands();
							if(cursor!= null && cursor.moveToFirst()){
								while(count!= position){
									count++;
									cursor.moveToNext();
								}
								
							rowIds= cursor.getLong(DBAdapter.COL_ROWID);
						}	
					}
				}break;
				}
				totalHint.setText(Integer.toString(cursor.getInt(DBAdapter.COL_QUANTITY)));
				
			}
		});      


		return view;
	}
	

	//Refresh after insertion
public void refresh(int code, String name, float price, int quantity) {
	CA= new CommandeAdapter();
	creatingAdapter= new CommandeAdapter(listCommandes, CA.addItem((ArrayList<Commands>) getCommands(quantity), code, name, price, quantity));
	listCommandes.setAdapter(creatingAdapter);
	creatingAdapter.notifyDataSetChanged();
	}

//Refresh
public void refresh() {
	creatingAdapter= new CommandeAdapter(listCommandes, getCommands());
	listCommandes.setAdapter(creatingAdapter);
	creatingAdapter.notifyDataSetChanged();
	}

	

private List<Commands> getCommands(int quantity) {
	List<Commands> commands=new ArrayList<Commands>();

	try {
		
		Cursor cursor= myDB.getAllRowsFromCommands();
		if(cursor!= null && cursor.moveToFirst()){
			do{
				Commands command= new Commands();
				command.setCode(cursor.getInt(DBAdapter.COL_CODE));
				command.setName(cursor.getString(DBAdapter.COL_NAME));
				command.setPrice(cursor.getFloat(DBAdapter.COL_PRICE));
				command.setQuantity(quantity);
				commands.add(command);
			}while(cursor.moveToNext());
		}
		cursor.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return commands;
}

private List<Commands> getCommands() {
	List<Commands> commands=new ArrayList<Commands>();

	try {
		
		Cursor cursor= myDB.getAllRowsFromCommands();
		if(cursor!= null && cursor.moveToFirst()){
			do{
				Commands command= new Commands();
				command.setCode(cursor.getInt(DBAdapter.COL_CODE));
				command.setName(cursor.getString(DBAdapter.COL_NAME));
				command.setPrice(cursor.getFloat(DBAdapter.COL_PRICE));
				command.setQuantity(cursor.getInt(DBAdapter.COL_QUANTITY));
				commands.add(command);
			}while(cursor.moveToNext());
		}
		cursor.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return commands;
}

private void clear() {
	
	myDB.deleteAllFromCommands();
	totalHint.setText(null);
	totalHint.setHint(Float.toString(calculateTotal())+ " DZD");
	for(int loop= 0; loop< 100; loop++){
		ProductsFragment.quantityProductsYaourt[loop]= 0;
		ProductsFragment.quantityProductsFromage[loop]= 0;
		ProductsFragment.quantityProductsLait[loop]= 0;
	}
	for(int loop= 0; loop< 50; loop++){
		ProductsFragment.rowIdsYaourt[loop]= 0;
		ProductsFragment.rowIdsFromage[loop]= 0;
		ProductsFragment.rowIdsLait[loop]= 0;
	}
	refresh();
	
}

private Float calculateTotal(){
	
	total= 0;
	Cursor cursor= myDB.getAllRowsFromCommands();
	if(cursor.moveToFirst()){
		do{
			total+= cursor.getFloat(DBAdapter.COL_PRICE) * cursor.getInt(DBAdapter.COL_QUANTITY);
		}while(cursor.moveToNext());
	}
	cursor.close();
	
		return total;
}
	

private void openDB() {
		// TODO Auto-generated method stub
	myDB= new DBAdapter(getActivity());
	myDB.open();
	}

@Override
public void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	myDB.close();
}
	

}
