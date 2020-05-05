package Dao;

import java.util.List;

import Database.DatabaseHelper;
import Entity.Network;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public abstract class Dao <T>{
	protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context mContext;
    
    public Dao(Context context) {
    	
            this.mContext = context;
            dbHelper = DatabaseHelper.getHelper(mContext);
            open();
	}
    
    public SQLiteDatabase open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DatabaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
        return database;
    }


	public abstract List<Network> find(Context context);
	
	/**
	 * Permet de créer une entrée dans la base de données
	 * par rapport à un objet
	 * @param obj
	 */
	public abstract boolean create(Context context);
	
	/**
	 * Permet de mettre à jour les données d'une entrée dans la base 
	 * @param obj
	 */
	public abstract int update();
	
	/**
	 * Permet la suppression d'une entrée de la base
	 * @param obj
	 */
	public abstract int delete(Context context);

}
