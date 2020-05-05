package Metier;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import Dao.Dao;
import Database.DatabaseHelper;
import Entity.Network;
import Entity.RevenuTraffic;

public class RevenuTrafficDAO extends Dao<RevenuTraffic> {
	

	public RevenuTrafficDAO(Context context) {
		super(context);
	}

	

	// Modifier after booleen
	public RevenuTraffic createTuples(RevenuTraffic obj) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.REVENU_CHAMP_DATE, obj.getDate());
		contentValues.put(DatabaseHelper.REVENU_CHAMP_TRAFFIC_ALL, obj.getTraffic_all());
		contentValues.put(DatabaseHelper.REVENU_CHAMP_REVENU_ALL, obj.getRevenu_all());
		contentValues.put(DatabaseHelper.REVENU_CHAMP_TRAFFIC_RCS, obj.getTraffic_rcs());
		contentValues.put(DatabaseHelper.REVENU_CHAMP_REVENU_RCS, obj.getRevenu_rcs());
		
		long result = database.insert(DatabaseHelper.TABLE_REVENU, null, contentValues);
		
		return obj;
	}

	@Override
	public boolean create(Context context) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int delete(Context context) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<Network> find(Context context) {
		// TODO Auto-generated method stub
		return null;
	}



	


}
