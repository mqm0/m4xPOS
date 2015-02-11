package com.pofs.metiers;



import com.pofs.Initializing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


// TO USE:
// Change the package (at top) to match your project.
// Search for "TODO", and make the appropriate changes.
public class DBAdapter {

	/////////////////////////////////////////////////////////////////////
	//	Constants & Data
	/////////////////////////////////////////////////////////////////////
	// For logging:
	private static final String TAG = "DBAdapter";

	// DB Fields
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	public static final String KEY_CODE = "code";
	public static final String KEY_NAME = "name";
	public static final String KEY_PRICE = "price";
	public static final String KEY_QUANTITY = "quantity";
	public static final String KEY_FAMILY = "family";

	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int COL_CODE = 1;
	public static final int COL_NAME = 2;
	public static final int COL_PRICE = 3;
	public static final int COL_QUANTITY = 4;
	public static final int COL_FAMILY = 5;


	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_CODE, KEY_NAME, KEY_PRICE, KEY_QUANTITY};
	public static final String[] ALL_KEYS_S = new String[] {KEY_ROWID, KEY_CODE, KEY_NAME, KEY_PRICE, KEY_QUANTITY, KEY_FAMILY};
	public static final String[] ALL_KEYS_P = new String[] {KEY_ROWID, KEY_CODE, KEY_NAME, KEY_PRICE, KEY_QUANTITY, KEY_FAMILY};

	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "myDB.db";
	public static final String DATABASE_TABLE_COMMANDS = "Commands";
	public static final String DATABASE_TABLE_SELLS = "Sells";
	public static final String DATABASE_TABLE_PRODUCTS = "Products";
	// Track DB version if a new version of your app changes the format.
	public static final int DATABASE_VERSION = 1;	

	private static final String DATABASE_CREATE_SQL_COMMANDS = 
			"create table " + DATABASE_TABLE_COMMANDS 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ KEY_CODE + " integer not null, "
			+ KEY_NAME + " text not null, "
			+ KEY_PRICE + " float not null, "
			+ KEY_QUANTITY + " integer not null"
			
			// Rest  of creation:
			+ ");";
	
	private static final String DATABASE_CREATE_SQL_SELLS = 
			"create table " + DATABASE_TABLE_SELLS 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ KEY_CODE + " integer not null, "
			+ KEY_NAME + " text not null, "
			+ KEY_PRICE + " float not null, "
			+ KEY_QUANTITY + " integer not null, "
			+ KEY_FAMILY + " text not null"
			
			// Rest  of creation:
			+ ");";

	private static final String DATABASE_CREATE_SQL_PRODUCTS = 
			"create table " + DATABASE_TABLE_PRODUCTS 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ KEY_CODE + " integer not null, "
			+ KEY_NAME + " text not null, "
			+ KEY_PRICE + " float not null, "
			+ KEY_QUANTITY + " integer not null, "
			+ KEY_FAMILY + " text not null"
			
			// Rest  of creation:
			+ ");";
	
	// Context of application who uses us.
	private final Context context;

	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	/////////////////////////////////////////////////////////////////////
	//	Public methods:
	/////////////////////////////////////////////////////////////////////

	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}

	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}

	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}

	// Add a new set of values to the database. 
	public long insertRowIntoCommands(int code, String name, float price, int quantity) {
		/*
		 * CHANGE 3:
		 */		
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_CODE, code);
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_PRICE, price);
		initialValues.put(KEY_QUANTITY, quantity);

		// Insert it into the database.
		return db.insert(DATABASE_TABLE_COMMANDS, null, initialValues);
	}
	
	public long insertRowIntoSells(int code, String name, float price, int quantity, String family) {
		/*
		 * CHANGE 3:
		 */		
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_CODE, code);
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_PRICE, price);
		initialValues.put(KEY_QUANTITY, quantity);
		initialValues.put(KEY_FAMILY, family);

		// Insert it into the database.
		return db.insert(DATABASE_TABLE_SELLS, null, initialValues);
	}
	
	public long insertRowIntoProducts(int code, String name, float price, int quantity, String family) {
		/*
		 * CHANGE 3:
		 */		
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_CODE, code);
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_PRICE, price);
		initialValues.put(KEY_QUANTITY, quantity);
		initialValues.put(KEY_FAMILY, family);

		// Insert it into the database.
		return db.insert(DATABASE_TABLE_PRODUCTS, null, initialValues);
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRowFromCommands(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE_COMMANDS, where, null) != 0;
	}
	
	public boolean deleteRowFromSells(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE_SELLS, where, null) != 0;
	}
	
	public boolean deleteRowFromProducts(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE_PRODUCTS, where, null) != 0;
	}

	public void deleteAllFromCommands() {
		Cursor c = getAllRowsFromCommands();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRowFromCommands(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	public void deleteAllFromSells() {
		Cursor c = getAllRowsFromSells();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRowFromSells(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	public void deleteAllFromProducts() {
		Cursor c = getAllRowsFromProducts();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRowFromProducts(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}

	// Return all data in the database.
	public Cursor getAllRowsFromCommands() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE_COMMANDS, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor getAllRowsFromSells() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE_SELLS, ALL_KEYS_S, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor getAllRowsFromProducts() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE_PRODUCTS, ALL_KEYS_P, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRowFromCommands(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE_COMMANDS, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor getRowFromSells(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE_SELLS, ALL_KEYS_S, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor getRowFromProducts(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE_PRODUCTS, ALL_KEYS_P, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Change an existing row to be equal to new data.
	public boolean updateRowOnCommands(long rowId, int code, String name, float price, int quantity) {
		String where = KEY_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_CODE, code);
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_PRICE, price);
		newValues.put(KEY_QUANTITY, quantity);

		// Insert it into the database.
		return db.update(DATABASE_TABLE_COMMANDS, newValues, where, null) != 0;
	}
	
	public boolean updateRowOnSells(long rowId, int code, String name, float price, int quantity, String family) {
		String where = KEY_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_CODE, code);
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_PRICE, price);
		newValues.put(KEY_QUANTITY, quantity);
		newValues.put(KEY_FAMILY, family);

		// Insert it into the database.
		return db.update(DATABASE_TABLE_SELLS, newValues, where, null) != 0;
	}
	
	public boolean updateRowOnProducts(long rowId, int code, String name, float price, int quantity, String family) {
		String where = KEY_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_CODE, code);
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_PRICE, price);
		newValues.put(KEY_QUANTITY, quantity);
		newValues.put(KEY_FAMILY, family);

		// Insert it into the database.
		return db.update(DATABASE_TABLE_PRODUCTS, newValues, where, null) != 0;
	}
	




	/////////////////////////////////////////////////////////////////////
	//	Private Helper Classes:
	/////////////////////////////////////////////////////////////////////

	/**
	 * Private class which handles database creation and upgrading.
	 * Used to handle low-level database access.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			try {
				_db.execSQL(DATABASE_CREATE_SQL_COMMANDS);
				_db.execSQL(DATABASE_CREATE_SQL_SELLS);
				_db.execSQL(DATABASE_CREATE_SQL_PRODUCTS);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");

			// Destroy old database:
			try {
				_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_COMMANDS);
				_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_SELLS);
				_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PRODUCTS);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}


			// Recreate new database:
			onCreate(_db);
		}
	}
}
