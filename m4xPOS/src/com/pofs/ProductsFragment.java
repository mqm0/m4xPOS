package com.pofs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import com.mqm0.m4xPOS.R;
import com.pofs.adapters.ProductAdapter;
import com.pofs.metiers.DBAdapter;
import com.pofs.metiers.Products;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class ProductsFragment extends Fragment {
	
	public static int[] quantityProductsYaourt= new int[100] ;
	public static int[] quantityProductsFromage= new int[100] ;
	public static int[] quantityProductsLait= new int[100] ;
	
	
	
	public static long[] rowIdsYaourt= new long[50] ;
	public static long[] rowIdsFromage= new long[50] ;
	public static long[] rowIdsLait= new long[50] ;
	
	public static String[] famille = new String[] { "Yaourt", "Fromage", "Lait"};
	public static String[] trace= new String[50];
	
	static int countTrace= 0;
	
	static String family= "";
	
	ListCommandesFragment LCF= new ListCommandesFragment();
	
	static boolean isExist= false;
	
	static long rowId= 0;
	
	static GridView  gridView;
	
	DBAdapter myDB;
	
	AlertDialog.Builder alertUpDialog, initDialog;
	
	Intent productActivityIntent, toClockInIntent;

	
	@SuppressLint({ "NewApi", "SimpleDateFormat" }) @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

		
		final View view = inflater.inflate(R.layout.produits_fragment, container, false);

		openDB();
		myDB.deleteAllFromCommands();
		
		//Init---------------------------------------------------------------------------------
				initDialog = new AlertDialog.Builder(getActivity());
				initDialog.setTitle("Initialazing");
				initDialog.setMessage("Do you wanna to initialize the table of products ?");
				
				initDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				
				initDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						toClockInIntent= new Intent(getActivity(), Initializing.class);
						getActivity().startActivity(toClockInIntent);
					}
				});

        GradientDrawable background = (GradientDrawable) getResources()
                .getDrawable(R.drawable.rounded_rect);
        background.setColor(getResources().getColor(R.color.android_green));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LayoutParams.FILL_PARENT, 3);
        lp.setMargins(2, 4, 4, 4);
        view.setLayoutParams(lp);
        GridView arial=(GridView) view.findViewById(R.id.arial);

        arial.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_expandable_list_item_1, famille));
        
        arial.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id){
				switch(position){
				default:{
					family= famille[position];
					gridView.setAdapter(new ProductAdapter(gridView, getProducts()));
					gridView.setAdapter(new ProductAdapter(gridView, getProducts(position)));
				}break;
				}
			}
		});
        
        alertUpDialog = new AlertDialog.Builder(getActivity());
		alertUpDialog.setTitle("Upload");
		alertUpDialog.setMessage("Do you wanna really to upload data to server ?");
		
		
		alertUpDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		
		alertUpDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
			}
		});
        
        gridView=(GridView) view.findViewById(R.id.gridView1);
		gridView.setAdapter(new ProductAdapter(gridView, getProducts()));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id){
				switch(position){
				case 0:{
					if(family.equals(famille[0]))
					setToCommands(50, position);
					else if(family.equals(famille[1]))
						setToCommands(60, position+10);
					else if(family.equals(famille[2]))
						setToCommands(70, position+20);
				}break;
                case 1:{
                	if(family.equals(famille[0]))
    					setToCommands(51, position);
    					else if(family.equals(famille[1]))
    						setToCommands(61, position+10);
    					else if(family.equals(famille[2]))
    						setToCommands(71, position+20);
                }break;
                case 2:{
                	if(family.equals(famille[0]))
    					setToCommands(52, position);
    					else if(family.equals(famille[1]))
    						setToCommands(62, position+10);
    					else if(family.equals(famille[2]))
    						setToCommands(72, position+20);
                }break;
                case 3:{
                	if(family.equals(famille[0]))
    					setToCommands(53, position);
    					else if(family.equals(famille[1]))
    						setToCommands(63, position+10);
    					else if(family.equals(famille[2]))
    						setToCommands(73, position+20);
                }break;
                case 4:{
                	if(family.equals(famille[0]))
    					setToCommands(54, position);
    					else if(family.equals(famille[1]))
    						setToCommands(64, position+10);
    					else if(family.equals(famille[2]))
    						setToCommands(74, position+20);
                }break;
				}
			}
		});

		Button init= (Button)view.findViewById(R.id.Init);
		init.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				initDialog.show();
			}
		});
		
		Button exportDB= (Button)view.findViewById(R.id.buttonExp);
		exportDB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

						//alertUpDialog.show();
				
		    	
				Intent myIntent= new Intent(getActivity(), Uploading.class);
				getActivity().startActivity(myIntent);
				
			}
		});
		
		Button Validate = (Button)view.findViewById(R.id.valid);
		Validate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				int code, newQuantity, pastSellQuantity;
				float newPrice, pastSellPrice;
				try {
					Cursor c= myDB.getAllRowsFromCommands(), 
							cursorRemaining,
							sells;
					
					if(c.moveToFirst()){
						do{
							
							cursorRemaining= myDB.getAllRowsFromProducts();
							cursorRemaining.moveToFirst();
							sells= myDB.getAllRowsFromSells();
							sells.moveToFirst();
							isExist= false;
							
							while(!cursorRemaining.isAfterLast()&& isExist!= true){
								if(c.getInt(DBAdapter.COL_CODE)== cursorRemaining.getInt(DBAdapter.COL_CODE))
									isExist= true;
								else
								cursorRemaining.moveToNext();
							}
							
							isExist= false;
							while(!sells.isAfterLast()&& isExist!= true){
								if(c.getInt(DBAdapter.COL_CODE)== sells.getInt(DBAdapter.COL_CODE)){
									rowId= sells.getLong(DBAdapter.COL_ROWID);
									isExist= true;
								}
								else
								sells.moveToNext();
							}
							
							code= c.getInt(DBAdapter.COL_CODE);
							newPrice= c.getFloat(DBAdapter.COL_PRICE);
							newQuantity= c.getInt(DBAdapter.COL_QUANTITY);
							newPrice*= newQuantity;
							
							int rem= cursorRemaining.getInt(DBAdapter.COL_QUANTITY), 
									toSoustrat= c.getInt(DBAdapter.COL_QUANTITY),
									quantityToSoustract;
							
							quantityToSoustract= rem- toSoustrat;
							
							myDB.updateRowOnProducts(
									cursorRemaining.getLong(DBAdapter.COL_ROWID), 
									cursorRemaining.getInt(DBAdapter.COL_CODE), 
									cursorRemaining.getString(DBAdapter.COL_NAME), 
									cursorRemaining.getFloat(DBAdapter.COL_PRICE), 
									quantityToSoustract, 
									cursorRemaining.getString(DBAdapter.COL_FAMILY));
							
							
							if(isExist== false){
								if(code < 60){
									myDB.insertRowIntoSells(
											c.getInt(DBAdapter.COL_CODE), 
											c.getString(DBAdapter.COL_NAME), 
											newPrice, 	
											c.getInt(DBAdapter.COL_QUANTITY),
											famille[0]);
							}
							else if(code >= 60 && code < 70){
								myDB.insertRowIntoSells(
										c.getInt(DBAdapter.COL_CODE), 
										c.getString(DBAdapter.COL_NAME), 
										newPrice, 	
										c.getInt(DBAdapter.COL_QUANTITY),
										famille[1]);
							}
							else if(code >= 70){
								myDB.insertRowIntoSells(
										c.getInt(DBAdapter.COL_CODE), 
										c.getString(DBAdapter.COL_NAME), 
										newPrice, 	
										c.getInt(DBAdapter.COL_QUANTITY),
										famille[2]);
							}
							}
							else{
								pastSellPrice= sells.getFloat(DBAdapter.COL_PRICE);
								pastSellQuantity= sells.getInt(DBAdapter.COL_QUANTITY);
								newPrice+= pastSellPrice;
								newQuantity+= pastSellQuantity;
								if(code < 60){
									myDB.updateRowOnSells(rowId,
											sells.getInt(DBAdapter.COL_CODE), 
											sells.getString(DBAdapter.COL_NAME), 
											newPrice, 	
											newQuantity,
											famille[0]);
							}
							else if(code >= 60 && code < 70){
								myDB.updateRowOnSells(rowId,
										sells.getInt(DBAdapter.COL_CODE), 
										sells.getString(DBAdapter.COL_NAME), 
										newPrice, 	
										newQuantity,
										famille[1]);
							}
							else if(code >= 70){
								myDB.updateRowOnSells(rowId,
										sells.getInt(DBAdapter.COL_CODE), 
										sells.getString(DBAdapter.COL_NAME), 
										newPrice, 	
										newQuantity,
										famille[2]);
							}
							}
							sells.close();
							cursorRemaining.close();
							isExist= false;
						}while(c.moveToNext());
						c.close();
						cursorRemaining.close();
						Toast.makeText(getActivity(),"Sell Successfully validated", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		return view;
	}
	
	
	
	
	
	
	
	void getImage(Products product, int code){
		switch(code){
		case 50:
			product.setImage(R.drawable.actimel);
			break;
		case 51:
			product.setImage(R.drawable.danette);
			break;
		case 52:
			product.setImage(R.drawable.para228858);
			break;
		case 60:
			product.setImage(R.drawable.veloute);
			break;
		case 61:
			product.setImage(R.drawable.president);
			break;
		case 62:
			product.setImage(R.drawable.labuchefondante);
			break;
		case 63:
			product.setImage(R.drawable.brique);
			break;
		case 70:
			product.setImage(R.drawable.activialait);
			break;
		case 71:
			product.setImage(R.drawable.laitcandia);
			break;
		case 72:
			product.setImage(R.drawable.candychoco);
			break;
		case 73:
			product.setImage(R.drawable.candiasilhouette);
			break;
		}
    }
    
	
private List<Products> getProducts(int arg) {
	List<Products> produits=new ArrayList<Products>();
	
		Cursor c= myDB.getAllRowsFromProducts();
		if(c.moveToFirst()){
			do{
				if(c.getString(DBAdapter.COL_FAMILY).equals(famille[arg])){
					Products product=new Products();
					product.setCode(c.getInt(DBAdapter.COL_CODE));
					product.setFamily(c.getString(DBAdapter.COL_FAMILY));
					product.setName(c.getString(DBAdapter.COL_NAME));
					getImage(product, c.getInt(DBAdapter.COL_CODE));
					product.setPrice(c.getFloat(DBAdapter.COL_PRICE));
					produits.add(product);
				}
			}while(c.moveToNext());
		}
	c.close();
	
	return produits;
}



private List<Products> getProducts() {
	List<Products> produits=new ArrayList<Products>();
	
	return produits;
}


private void checkExistence(int code, String table) {
	Cursor c= null;
	try {
		if(table.equals(DBAdapter.DATABASE_TABLE_COMMANDS)){
			c= myDB.getAllRowsFromCommands();
			if(c.moveToFirst()&&!(isExist==true)){
				do{
					if(c.getInt(DBAdapter.COL_CODE)== code){
						isExist= true;
						rowId= c.getLong(DBAdapter.COL_ROWID);
					}
				}while(c.moveToNext());
			}
		}
		else if(table.equals(DBAdapter.DATABASE_TABLE_SELLS)){
			c= myDB.getAllRowsFromCommands();
			if(c.moveToFirst()&&!(isExist==true)){
				do{
					if(c.getInt(DBAdapter.COL_CODE)== code){
						isExist= true;
						rowId= c.getLong(DBAdapter.COL_ROWID);
					}
				}while(c.moveToNext());
			}
		}
		c.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void setToCommands(int code, int position){
	
	Products product= new Products();
	Boolean stop= false;
	Cursor c= myDB.getAllRowsFromProducts();
	if(c.moveToFirst()&&(!stop)){
		do{
			if(c.getInt(DBAdapter.COL_CODE)== code){
				product.setCode(c.getInt(DBAdapter.COL_CODE));
				product.setName(c.getString(DBAdapter.COL_NAME));
				product.setPrice(c.getFloat(DBAdapter.COL_PRICE));
				stop= true;
			}
		}while(c.moveToNext());
	}
	
	isExist= false;
	checkExistence(product.getCode(), DBAdapter.DATABASE_TABLE_COMMANDS);
	
	//Inserting for the first time
	if(isExist== false){
		if(product.getCode()< 60){
			trace[countTrace]= famille[0];
			countTrace++;
			rowIdsYaourt[position]= myDB.insertRowIntoCommands(product.getCode(), product.getName(), product.getPrice(), ++quantityProductsYaourt[position]);
			LCF.refresh(product.getCode(), product.getName(), product.getPrice(), quantityProductsYaourt[position]);
		}
		else if((product.getCode()< 70)&&(product.getCode()>= 60)){
			trace[countTrace]= famille[1];
			countTrace++;
			rowIdsFromage[position]= myDB.insertRowIntoCommands(product.getCode(), product.getName(), product.getPrice(), ++quantityProductsFromage[position]);
			LCF.refresh(product.getCode(), product.getName(), product.getPrice(), quantityProductsFromage[position]);
		}
		else if(product.getCode()>= 70){
			trace[countTrace]= famille[2];
			countTrace++;
			rowIdsLait[position]= myDB.insertRowIntoCommands(product.getCode(), product.getName(), product.getPrice(), ++quantityProductsLait[position]);
			LCF.refresh(product.getCode(), product.getName(), product.getPrice(), quantityProductsLait[position]);
		}
	}
	//Updating an existing product
    else{
    	if(product.getCode()< 60){
    		myDB.updateRowOnCommands(rowId, product.getCode(), product.getName(), product.getPrice(), ++quantityProductsYaourt[position]);
        	LCF.refresh(product.getCode(), product.getName(), product.getPrice(), quantityProductsYaourt[position]);
    	}
    	else if((product.getCode()< 70)&&(product.getCode()>= 60)){
    		myDB.updateRowOnCommands(rowId, product.getCode(), product.getName(), product.getPrice(), ++quantityProductsFromage[position]);
        	LCF.refresh(product.getCode(), product.getName(), product.getPrice(), quantityProductsFromage[position]);
    	}
   		else if(product.getCode()>= 70){
   			myDB.updateRowOnCommands(rowId, product.getCode(), product.getName(), product.getPrice(), ++quantityProductsLait[position]);
        	LCF.refresh(product.getCode(), product.getName(), product.getPrice(), quantityProductsLait[position]);
   		}
    	}
	c.close();
	
}
	
	private void openDB() {
		myDB= new DBAdapter(getActivity());
		myDB.open();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		myDB.close();
	};

}
