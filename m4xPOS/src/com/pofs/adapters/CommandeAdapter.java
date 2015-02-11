package com.pofs.adapters;

import java.util.ArrayList;
import java.util.List;

import com.mqm0.m4xPOS.R;
import com.pofs.ListCommandesFragment;
import com.pofs.metiers.Commands;
import com.pofs.metiers.DBAdapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class CommandeAdapter extends BaseAdapter {
	ArrayList<Commands> data;
	private List<Commands> commandes;
	private View view;

	public CommandeAdapter(View view, List<Commands> commandes) {
		super();
		this.view = view;
		this.commandes = commandes;
	}

	public CommandeAdapter() {
		super();
	}

	public List<Commands> addItem(ArrayList<Commands> data, int code,
			String name, float price, int quantity) {
		// data= new ArrayList<Commands>();
		Commands cmd1 = new Commands();
		cmd1.setCode(code);
		cmd1.setName(name);
		cmd1.setPrice(price);
		cmd1.setQuantity(quantity);
		data.add(cmd1);
		return data;
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
			Commands commande = commandes.get(position);
			Context context = view.getContext();

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.commandes, null);

			// set value into textview
			TextView textView = (TextView) view.findViewById(R.id.product_name);
			textView.setText(commande.getName());


			// set image based on selected text
			TextView prodQuantity= (TextView)view.findViewById(R.id.qtyField);
			prodQuantity.setText(""+ commande.getQuantity());

			
			TextView prixUtTextView = (TextView) view
					.findViewById(R.id.product_prix_ut);
			prixUtTextView.setText("Unit.Price: " + commande.getPrice()+" DZD");

		}

		return view;

	}

}
