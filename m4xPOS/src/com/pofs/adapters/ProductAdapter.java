package com.pofs.adapters;

import java.util.List;

import com.mqm0.m4xPOS.R;
import com.pofs.metiers.Products;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


public class ProductAdapter extends BaseAdapter {

	private final List<Products> produits;
private View gridView;
	public ProductAdapter(GridView gridView, List<Products> produits) {
		this.gridView = gridView;
		this.produits = produits;
		
	}

	@SuppressLint("NewApi") public View getView(int position, View convertView, ViewGroup parent) {





if (convertView == null) {
Products produit=produits.get(position);
		Context context=gridView.getContext();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// get layout from mobile.xml

		gridView = inflater.inflate(R.layout.product, null);
		GradientDrawable background = (GradientDrawable) context.getResources().getDrawable(R.drawable.rounded_item);
		        
		background.setColor(context.getResources().getColor(R.color.dark_grey));

		gridView.setBackground(background);
		
		// set value into textview
		TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
		textView.setText(produit.getName());
		
		
        TextView prixTextView=(TextView) gridView.findViewById(R.id.prix_item);
        prixTextView.setText(""+produit.getPrice());
		
        
        // set image based on selected text
		ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);

        imageView.setImageResource(produit.getImage());

		} 

		return gridView;
	}

	@Override
	public int getCount() {
		return produits.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
