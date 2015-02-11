package com.pofs;


import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;






import com.mqm0.m4xPOS.R;
import com.pofs.metiers.DBAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;

public class Uploading extends Activity {

	
	
	
	public static String ID_NUMBER;
	public static String DATE;
	
    public static String dateText,dateNum;
	
    static String []urlProds= new String[15];
    static String []urlSells= new String[15];
	
	EditText idNumber;
	EditText date;
	
	Button confirm;
	Button cancel;
	
	static int mark;
	static int indexOfProds;
	
	private boolean isFormatting;
	private boolean deletingHyphen;
	private int hyphenStart;
	private boolean deletingBackward;
	
	Button btnStartProgress;
	
	ProgressDialog progressBar;
	
	static int progress= 0;
	
	DBAdapter myDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asynctask);
		
		idNumber= (EditText)findViewById(R.id.editTextAlertId);
		date= (EditText)findViewById(R.id.editTextAlertDate);
		
		confirm= (Button)findViewById(R.id.buttonConfirm);
		cancel= (Button)findViewById(R.id.buttonCancel);
		
		confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ID_NUMBER= idNumber.getText().toString();
				DATE= date.getText().toString();
				String month= DATE.substring(0, 2), day= DATE.substring(3, 5), year= DATE.substring(6, 10);
				dateNum= year+"/"+convertMonthToNumb(month)+"/"+day;
				dateText=convertMonthToText(month)+"_"+year;
				

				
				
				mark= 0;
				openDB();
				Cursor pntr= myDB.getAllRowsFromSells();
				myDB.close();
				try {
					if(pntr.moveToFirst()){
						do{
							urlProds[mark]= ("http://10.0.3.2/m4xp0s/products_async.php?db="+dateText+
									"&code="+pntr.getInt(DBAdapter.COL_CODE)+
									"&quantity="+pntr.getInt(DBAdapter.COL_QUANTITY));
							urlSells[mark]= ("http://10.0.3.2/m4xp0s/sells_async.php?db="+dateText+
									"&code="+pntr.getInt(DBAdapter.COL_CODE)+
									"&name="+pntr.getString(DBAdapter.COL_NAME)+
									"&price="+pntr.getFloat(DBAdapter.COL_PRICE)+
									"&quantity="+pntr.getInt(DBAdapter.COL_QUANTITY))+
									"&family="+pntr.getString(DBAdapter.COL_FAMILY);
							mark++;
						}while(pntr.moveToNext());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				pntr.close();
				
				indexOfProds= mark;
				
				Context context= getApplicationContext();
				
				progressBar= new ProgressDialog(arg0.getContext());
				
				AsynchroneTask backGroundTask= new AsynchroneTask();
				
				backGroundTask.execute(context);
				
				
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		date.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				if (isFormatting)
			        return;

			    // Make sure user is deleting one char, without a selection
			    final int selStart = Selection.getSelectionStart(s);
			    final int selEnd = Selection.getSelectionEnd(s);
			    if (s.length() > 1 // Can delete another character
			            && count == 1 // Deleting only one character
			            && after == 0 // Deleting
			            && s.charAt(start) == '-' // a hyphen
			            && selStart == selEnd) { // no selection
			        deletingHyphen = true;
			        hyphenStart = start;
			        // Check if the user is deleting forward or backward
			        if (selStart == start + 1) {
			            deletingBackward = true;
			        } else {
			            deletingBackward = false;
			        }
			    } else {
			        deletingHyphen = false;
			    }
				
			}
			
			@Override
			public void afterTextChanged(Editable text) {
				if (isFormatting)
			        return;

			    isFormatting = true;

			    // If deleting hyphen, also delete character before or after it
			    if (deletingHyphen && hyphenStart > 0) {
			        if (deletingBackward) {
			            if (hyphenStart - 1 < text.length()) {
			                text.delete(hyphenStart - 1, hyphenStart);
			            }
			        } else if (hyphenStart < text.length()) {
			            text.delete(hyphenStart, hyphenStart + 1);
			        }
			    }
			    if (text.length() == 2 || text.length() == 5) {
			        text.append('-');
			    }

			    isFormatting = false;
			}
		});
	}
	
	
	private String convertMonthToText(String month) {
		if(month.equals("01"))
			month= "January";
		else if(month.equals("02"))
			month= "February";
		else if(month.equals("03"))
			month= "March";
		else if(month.equals("04"))
			month= "April";
		else if(month.equals("05"))
			month= "May";
		else if(month.equals("06"))
			month= "June";
		else if(month.equals("07"))
			month= "July";
		else if(month.equals("08"))
			month= "August";
		else if(month.equals("09"))
			month= "September";
		else if(month.equals("10"))
			month= "October";
		else if(month.equals("11"))
			month= "November";
		else if(month.equals("12"))
			month= "December";
		return month;
	}
	
	private String convertMonthToNumb(String month) {
		if(month.equals("January"))
			month= "01";
		else if(month.equals("February"))
			month= "02";
		else if(month.equals("March"))
			month= "03";
		else if(month.equals("April"))
			month= "04";
		else if(month.equals("May"))
			month= "05";
		else if(month.equals("June"))
			month= "06";
		else if(month.equals("July"))
			month= "07";
		else if(month.equals("August"))
			month= "08";
		else if(month.equals("September"))
			month= "09";
		else if(month.equals("October"))
			month= "10";
		else if(month.equals("November"))
			month= "11";
		else if(month.equals("December"))
			month= "12";
		return month;
	}
	


	private class AsynchroneTask extends AsyncTask<Context, Integer, String>{

		
		@Override
		protected String doInBackground(Context... arg0) {
			
			while(progress<= 100){
				if(progress<= 10){
					
					  try{
						  mark= 1;
						  upload();
							Thread.sleep(1000);
							progress+= 11;
						}catch(InterruptedException e){
							e.printStackTrace();
						}
						
						publishProgress(progress);
						
						if(isCancelled())break;
					  
				}
				else if(progress<= 20 && progress> 10){
					
					  try{
							mark= 2;
						    upload();
						    Thread.sleep(1000);
						    progress+= 11;
						}catch(InterruptedException e){
							e.printStackTrace();
						}
						
						publishProgress(progress);
						
						if(isCancelled())break;
					  
				}
				else if(progress<= 30 && progress> 20){
					
					try{
						mark= 0;
						for(int i= 1; i<= indexOfProds; i++){
							prodsUpload();
							Thread.sleep(1000);
							
							mark++;
						}
						progress+= 20;
						
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					
					publishProgress(progress);
					
					if(isCancelled())break;
				}
				else if(progress<= 50 && progress> 40){
					try{
						
						mark= 0;
						for(int i= 1; i<= indexOfProds; i++){
							sellsUpload();
							Thread.sleep(1000);
							mark++;
						}
						progress+= 60;
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					
					publishProgress(progress);
					
					if(isCancelled())break;
					
				}
			}
			
			return "Comlpeted";
		}
		
		@Override
		protected void onPostExecute(String result) {

			progressBar.dismiss();
			progress= 0;
			Context context= getApplicationContext();
			int duration= Toast.LENGTH_LONG;
			Toast toast= Toast.makeText(context, result, duration);
			toast.show();
			super.onPostExecute(result);
		}
		
		@Override
		protected void onPreExecute() {

			CharSequence message= ("Uploading to Server...");
			
			
			progressBar.setCancelable(true);
			progressBar.setMessage(message);
			progressBar.setIcon(R.drawable.ic_action_send);
			progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressBar.setProgress(0);
			progressBar.setMax(100);
			progressBar.show();
			super.onPreExecute();
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {

			progressBar.incrementProgressBy(progress);
			super.onProgressUpdate(values);
		}
	}
	
	private void sellsUpload(){
		HttpClient httpclient= new DefaultHttpClient();
		HttpPost httppost;
		
		switch(mark){
		
	//----------------------Sells Asynch-----------------------------
		default:{
				try {
					httpclient= new DefaultHttpClient();
					httppost= new HttpPost(urlSells[mark]);
					httpclient.execute(httppost);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}break;
	}
	}	
	
	private void prodsUpload(){
		HttpClient httpclient= new DefaultHttpClient();
		HttpPost httppost;
		
		switch(mark){
		
	//----------------------Prods Asynch-----------------------------
		default:{
				try {
					httpclient= new DefaultHttpClient();
					httppost= new HttpPost(urlProds[mark]);
					httpclient.execute(httppost);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}break;
	}
	}	
	private void upload(){
		HttpClient httpclient= new DefaultHttpClient();
		HttpPost httppost;
		
		switch(mark){
		case 1:{
			String urlCreateDb= "http://10.0.3.2/m4xp0s/db_create.php?db="+dateText;
			try {
				httppost= new HttpPost(urlCreateDb);
				httpclient.execute(httppost);
				
			} catch (Exception e) {

			}
		}break;
		case 2:{
			String urlCreateClient= "http://10.0.3.2/m4xp0s/client_create.php?" +
					"db="+dateText+
					"&id="+ID_NUMBER+
					"&date="+dateNum+
					"&mark=OK";
			try {
				httppost= new HttpPost(urlCreateClient);
				httpclient.execute(httppost);
			} catch (Exception e) {

			}
		}break;
		}
	}
	
	private void openDB() {
		myDB= new DBAdapter(Uploading.this);
		myDB.open();
	}
	
	

}
