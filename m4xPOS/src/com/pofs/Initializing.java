package com.pofs;


import com.mqm0.m4xPOS.R;
import com.pofs.metiers.DBAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Initializing extends Activity {

	public static int[] initQuantityProducts= new int[12];
	
	Editable Str;
	
	String str;
	
	int newQuantity, check= 0;
	
	
	EditText idNumber;
	EditText date;
	EditText actimel;
	EditText danette;
	EditText activia;
	EditText veloute;
	EditText president;
	EditText laBuche;
	EditText brique;
	EditText activiaLait;
	EditText candia;
	EditText candy;
	EditText silhouette;
	
	Button actimelAdd;
	Button actimelSous;
	Button danetteAdd;
	Button danetteSous;
	Button activiaAdd;
	Button activiaSous;
	Button velouteAdd;
	Button velouteSous;
	Button presidentAdd;
	Button presidentSous;
	Button laBucheAdd;
	Button laBucheSous;
	Button briqueAdd;
	Button briqueSous;
	Button vivaAdd;
	Button vivaSous;
	Button candiaAdd;
	Button candiaSous;
	Button candyAdd;
	Button candySous;
	Button silhouetteAdd;
	Button silhouetteSous;
	Button valid;
	
	DBAdapter myDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_clock_in);
		
		openDB();
		for(int loop= 0; loop<=11; loop++)
			initQuantityProducts[loop]= 200;
		
		valid= (Button)findViewById(R.id.initValidate);
		valid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				openDB();
				myDB.deleteAllFromProducts();
				myDB.deleteAllFromSells();
				makeProducts();
				finish();
			}
		});
		
		
		actimel= (EditText)findViewById(R.id.EditTextActimel);
		actimel.setText(Integer.toString(initQuantityProducts[0]));
		
		danette= (EditText)findViewById(R.id.EditTextDanette);
		danette.setText(Integer.toString(initQuantityProducts[1]));
		
		activia= (EditText)findViewById(R.id.EditTextActivia);
		activia.setText(Integer.toString(initQuantityProducts[2]));
		
		veloute= (EditText)findViewById(R.id.EditTextVeloute);
		veloute.setText(Integer.toString(initQuantityProducts[3]));
		
		
		president= (EditText)findViewById(R.id.EditTextPresident);
		president.setText(Integer.toString(initQuantityProducts[4]));
		
		laBuche= (EditText)findViewById(R.id.EditTextLaBuche);
		laBuche.setText(Integer.toString(initQuantityProducts[5]));
		
		brique= (EditText)findViewById(R.id.EditTextBrique);
		brique.setText(Integer.toString(initQuantityProducts[6]));
		
		activiaLait= (EditText)findViewById(R.id.EditTextActiviaLait);
		activiaLait.setText(Integer.toString(initQuantityProducts[7]));
		
		candia= (EditText)findViewById(R.id.EditTextCandia);
		candia.setText(Integer.toString(initQuantityProducts[8]));
		
		candy= (EditText)findViewById(R.id.EditTextCandy);
		candy.setText(Integer.toString(initQuantityProducts[9]));
		
		silhouette= (EditText)findViewById(R.id.EditTextSilhouette);
		silhouette.setText(Integer.toString(initQuantityProducts[10]));
		
		
		//Buttons----------------------------------------------------------
		//Actimel
		
		actimelAdd=(Button)findViewById(R.id.actimelAdd);
		actimelAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Str= actimel.getText();
				str= Str.toString();
				newQuantity= Integer.parseInt(str);
				newQuantity++;
				initQuantityProducts[0]= newQuantity;
				actimel.setText(Integer.toString(initQuantityProducts[0]));
			}
		});
		
		actimelSous=(Button)findViewById(R.id.actimelSous);
		actimelSous.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Str= actimel.getText();
				str= Str.toString();
				newQuantity= Integer.parseInt(str);
				if(newQuantity<= 0){
					newQuantity= 0;
					initQuantityProducts[0]= newQuantity;
					actimel.setText(Integer.toString(initQuantityProducts[0]));
				}
				else{
					newQuantity--;
					initQuantityProducts[0]= newQuantity;
					actimel.setText(Integer.toString(initQuantityProducts[0]));
				}
			}
		});
		
		//Danette----------------------------------------------------------------
		danetteAdd=(Button)findViewById(R.id.danetteAdd);
		danetteAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Str= danette.getText();
				str= Str.toString();
				newQuantity= Integer.parseInt(str);
				newQuantity++;
				initQuantityProducts[1]= newQuantity;
				danette	.setText(Integer.toString(initQuantityProducts[1]));
			}
		});
		
		danetteSous=(Button)findViewById(R.id.danetteSous);
		danetteSous.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Str= danette.getText();
				str= Str.toString();
				newQuantity= Integer.parseInt(str);
				if(newQuantity<= 0){
					newQuantity= 0;
					initQuantityProducts[1]= newQuantity;
					danette.setText(Integer.toString(initQuantityProducts[1]));
				}
				else{
					newQuantity--;
					initQuantityProducts[1]= newQuantity;
					danette.setText(Integer.toString(initQuantityProducts[1]));
				}
			}
		});
		
		//Activia----------------------------------------------------------------
		activiaAdd=(Button)findViewById(R.id.activiaAdd);
		activiaAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Str= activia.getText();
				str= Str.toString();
				newQuantity= Integer.parseInt(str);
				newQuantity++;
				initQuantityProducts[2]= newQuantity;
				activia	.setText(Integer.toString(initQuantityProducts[2]));
			}
		});
		
		activiaSous=(Button)findViewById(R.id.activiaSous);
		activiaSous.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Str= activia.getText();
				str= Str.toString();
				newQuantity= Integer.parseInt(str);
				if(newQuantity<= 0){
					newQuantity= 0;
					initQuantityProducts[2]= newQuantity;
					activia.setText(Integer.toString(initQuantityProducts[2]));
				}
				else{
					newQuantity--;
					initQuantityProducts[2]= newQuantity;
					activia.setText(Integer.toString(initQuantityProducts[2]));
				}
			}
		});
		
		//Veloute----------------------------------------------------------------
				velouteAdd=(Button)findViewById(R.id.velouteAdd);
				velouteAdd.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= veloute.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						newQuantity++;
						initQuantityProducts[3]= newQuantity;
						veloute	.setText(Integer.toString(initQuantityProducts[3]));
					}
				});
				
				velouteSous=(Button)findViewById(R.id.velouteSous);
				velouteSous.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= veloute.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						if(newQuantity<= 0){
							newQuantity= 0;
							initQuantityProducts[3]= newQuantity;
							veloute.setText(Integer.toString(initQuantityProducts[3]));
						}
						else{
							newQuantity--;
							initQuantityProducts[3]= newQuantity;
							veloute.setText(Integer.toString(initQuantityProducts[3]));
						}
					}
				});
				
				//President----------------------------------------------------------------
				presidentAdd=(Button)findViewById(R.id.presidentAdd);
				presidentAdd.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= president.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						newQuantity++;
						initQuantityProducts[4]= newQuantity;
						president.setText(Integer.toString(initQuantityProducts[4]));
					}
				});
				
				presidentSous=(Button)findViewById(R.id.presidentSous);
				presidentSous.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= president.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						if(newQuantity<= 0){
							newQuantity= 0;
							initQuantityProducts[2]= newQuantity;
							president.setText(Integer.toString(initQuantityProducts[4]));
						}
						else{
							newQuantity--;
							initQuantityProducts[4]= newQuantity;
							president.setText(Integer.toString(initQuantityProducts[4]));
						}
					}
				});
				
				//La Buche----------------------------------------------------------------
				laBucheAdd=(Button)findViewById(R.id.bucheAdd);
				laBucheAdd.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= laBuche.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						newQuantity++;
						initQuantityProducts[5]= newQuantity;
						laBuche	.setText(Integer.toString(initQuantityProducts[5]));
					}
				});
				
				laBucheSous=(Button)findViewById(R.id.bucheSous);
				laBucheSous.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= laBuche.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						if(newQuantity<= 0){
							newQuantity= 0;
							initQuantityProducts[5]= newQuantity;
							laBuche.setText(Integer.toString(initQuantityProducts[5]));
						}
						else{
							newQuantity--;
							initQuantityProducts[5]= newQuantity;
							laBuche.setText(Integer.toString(initQuantityProducts[5]));
						}
					}
				});
				
				//Brique----------------------------------------------------------------
				briqueAdd=(Button)findViewById(R.id.briqueAdd);
				briqueAdd.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= brique.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						newQuantity++;
						initQuantityProducts[6]= newQuantity;
						brique.setText(Integer.toString(initQuantityProducts[6]));
					}
				});
				
				briqueSous=(Button)findViewById(R.id.briqueSous);
				briqueSous.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= brique.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						if(newQuantity<= 0){
							newQuantity= 0;
							initQuantityProducts[6]= newQuantity;
							brique.setText(Integer.toString(initQuantityProducts[6]));
						}
						else{
							newQuantity--;
							initQuantityProducts[6]= newQuantity;
							brique.setText(Integer.toString(initQuantityProducts[6]));
						}
					}
				});
				
				//Candia Viva----------------------------------------------------------------
				vivaAdd=(Button)findViewById(R.id.activiaLaitAdd);
				vivaAdd.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= activiaLait.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						newQuantity++;
						initQuantityProducts[7]= newQuantity;
						activiaLait.setText(Integer.toString(initQuantityProducts[7]));
					}
				});
				
				vivaSous=(Button)findViewById(R.id.activiaLaitSous);
				vivaSous.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= activiaLait.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						if(newQuantity<= 0){
							newQuantity= 0;
							initQuantityProducts[7]= newQuantity;
							activiaLait.setText(Integer.toString(initQuantityProducts[7]));
						}
						else{
							newQuantity--;
							initQuantityProducts[7]= newQuantity;
							activiaLait.setText(Integer.toString(initQuantityProducts[7]));
						}
					}
				});
				
				//Candia----------------------------------------------------------------
				candiaAdd=(Button)findViewById(R.id.candiaAdd);
				candiaAdd.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= candia.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						newQuantity++;
						initQuantityProducts[8]= newQuantity;
						candia.setText(Integer.toString(initQuantityProducts[8]));
					}
				});
				
				candiaSous=(Button)findViewById(R.id.candiaSous);
				candiaSous.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= candia.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						if(newQuantity<= 0){
							newQuantity= 0;
							initQuantityProducts[9]= newQuantity;
							candia.setText(Integer.toString(initQuantityProducts[9]));
						}
						else{
							newQuantity--;
							initQuantityProducts[9]= newQuantity;
							candia.setText(Integer.toString(initQuantityProducts[9]));
						}
					}
				});
				
				//Candy Choco----------------------------------------------------------------
				candyAdd=(Button)findViewById(R.id.candyAdd);
				candyAdd.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= candy.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						newQuantity++;
						initQuantityProducts[10]= newQuantity;
						candy.setText(Integer.toString(initQuantityProducts[10]));
					}
				});
				
				candySous=(Button)findViewById(R.id.candySous);
				candySous.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= candy.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						if(newQuantity<= 0){
							newQuantity= 0;
							initQuantityProducts[2]= newQuantity;
							candy.setText(Integer.toString(initQuantityProducts[10]));
						}
						else{
							newQuantity--;
							initQuantityProducts[10]= newQuantity;
							candy.setText(Integer.toString(initQuantityProducts[10]));
						}
					}
				});
				
				//Silhouette----------------------------------------------------------------
				silhouetteAdd=(Button)findViewById(R.id.silhouetteAdd);
				silhouetteAdd.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= silhouette.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						newQuantity++;
						initQuantityProducts[11]= newQuantity;
						silhouette.setText(Integer.toString(initQuantityProducts[11]));
					}
				});
				
				silhouetteSous=(Button)findViewById(R.id.silhouetteSous);
				silhouetteSous.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Str= silhouette.getText();
						str= Str.toString();
						newQuantity= Integer.parseInt(str);
						if(newQuantity<= 0){
							newQuantity= 0;
							initQuantityProducts[11]= newQuantity;
							silhouette.setText(Integer.toString(initQuantityProducts[11]));
						}
						else{
							newQuantity--;
							initQuantityProducts[11]= newQuantity;
							silhouette.setText(Integer.toString(initQuantityProducts[11]));
						}
					}
				});
	}

	private void makeProducts(){
    	myDB.insertRowIntoProducts(50, "ACTIMEL", 300, Integer.parseInt(actimel.getText().toString()), "Yaourt");
    	myDB.insertRowIntoProducts(51, "DANNETTE", 480, Integer.parseInt(danette.getText().toString()), "Yaourt");
    	myDB.insertRowIntoProducts(52, "ACTIVIA", 480, Integer.parseInt(activia.getText().toString()), "Yaourt");
    	
    	myDB.insertRowIntoProducts(60, "VELOUTE", 750, Integer.parseInt(veloute.getText().toString()), "Fromage");
    	myDB.insertRowIntoProducts(61, "PRESIDENT", 970, Integer.parseInt(president.getText().toString()), "Fromage");
    	myDB.insertRowIntoProducts(62, "LABUCHE", 460, Integer.parseInt(laBuche.getText().toString()), "Fromage");
    	myDB.insertRowIntoProducts(63, "BRIQUE", 530, Integer.parseInt(brique.getText().toString()), "Fromage");
    	
    	myDB.insertRowIntoProducts(70, "ACTIVIA", 480, Integer.parseInt(activiaLait.getText().toString()), "Lait");
    	myDB.insertRowIntoProducts(71, "CANDIA", 380, Integer.parseInt(candia.getText().toString()), "Lait");
    	myDB.insertRowIntoProducts(72, "CANDY", 240, Integer.parseInt(candy.getText().toString()), "Lait");
    	myDB.insertRowIntoProducts(73, "SILHOUETTE", 760, Integer.parseInt(silhouette.getText().toString()), "Lait");
    }
	
	
	
	private void openDB() {
		myDB= new DBAdapter(Initializing.this);
		myDB.open();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		myDB.close();
	}
	

}
