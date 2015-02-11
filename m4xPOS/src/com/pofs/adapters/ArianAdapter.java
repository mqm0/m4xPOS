package com.pofs.adapters;

import java.util.List;

import com.mqm0.m4xPOS.R;
import com.pofs.metiers.Commands;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ArianAdapter extends BaseAdapter{
private List<Commands> commandes;
private View view;
	public ArianAdapter(View view,List<Commands> commandes) {
	super();
	this.view=view;
	this.commandes = commandes;
}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return commandes.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return commandes.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return commandes.get(position).getCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {




if (convertView == null) {
Commands commande=commandes.get(position);
		Context context= view.getContext();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.commandes, null);
			
		
		// set value into textview
			TextView textView = (TextView) view.findViewById(R.id.product_name);
			textView.setText(commande.getName());
			

TextView prodQuantity= (TextView)view.findViewById(R.id.qtyField);
prodQuantity.setText(""+ commande.getQuantity());


TextView prixUtTextView=(TextView) view.findViewById(R.id.product_prix_ut);
prixUtTextView.setText("Unit.Price: "+commande.getPrice());


} 

		return view;

	}

}
