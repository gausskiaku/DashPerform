package Dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import Database.DatabaseHelper;
import Entity.Network;
import Metier.Network_KPI;

public class Network_KPIDAO extends Dao<Network>{

	public Network_KPIDAO(Context context) {
		super(context);
		
	}


	DatabaseHelper dbHelper;
	
	Network_KPI nKpi = new Network_KPI();
	 // = dbHelper.getWritableDatabase();
	public static final String TABLE_NETWORK_KPI = "Network_KPI";
	public static final String NETWORK_KPI_CHAMP_DATE = "date";
	public static final String NETWORK_KPI_CHAMP_ERLANG= "erlang";
	//public static final String NETWORK_KPI_CHAMP_DATA= "data";
	public static final String NETWORK_KPI_CHAMP_NETWORK = "network";
	public static final String NETWORK_KPI_CHAMP_CALL_DROP = "call_drop";
	public static final String NETWORK_KPI_CHAMP_CALL_SSR = "call_ssr";
	public static final String NETWORK_KPI_CHAMP_AVAILABILITY = "availability";
	public static final String NETWORK_KPI_CHAMP_REVENU = "revenu";


	public Cursor trouver(Context context){
		Cursor resultat;
		try {
			dbHelper = new DatabaseHelper(context);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			resultat = db.rawQuery("SELECT * FROM Network_KPI", null);
			return resultat; 
		} catch (Exception e) {
			return null;
		}
		 
	}
	
	public Cursor getAll(Context context){
		Cursor resultat;
		try {
			dbHelper = new DatabaseHelper(context);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			resultat = db.rawQuery("SELECT "+NETWORK_KPI_CHAMP_DATE+", SUM("+NETWORK_KPI_CHAMP_ERLANG+") AS traffic, SUM("+NETWORK_KPI_CHAMP_REVENU+") AS revenu, SUM("+NETWORK_KPI_CHAMP_CALL_DROP+") AS cdr, SUM("+NETWORK_KPI_CHAMP_CALL_SSR+") AS cssr, SUM("+NETWORK_KPI_CHAMP_AVAILABILITY+") AS avail FROM Network_KPI GROUP BY "+NETWORK_KPI_CHAMP_DATE+"", null);
			return resultat;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Cursor get2GKPI(Context context){
		Cursor resultat;
		try {
			dbHelper = new DatabaseHelper(context);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			resultat = db.rawQuery("SELECT "+NETWORK_KPI_CHAMP_DATE+", SUM("+NETWORK_KPI_CHAMP_ERLANG+") AS traffic, SUM("+NETWORK_KPI_CHAMP_REVENU+") AS revenu, AVG("+NETWORK_KPI_CHAMP_CALL_DROP+") AS cdr, AVG("+NETWORK_KPI_CHAMP_CALL_SSR+") AS cssr, AVG("+NETWORK_KPI_CHAMP_AVAILABILITY+") AS avail FROM Network_KPI WHERE Network = '2G' GROUP BY "+NETWORK_KPI_CHAMP_DATE+"", null);
			return resultat;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Cursor get3gKPI(Context context){
		Cursor resultat;
		try {
			dbHelper = new DatabaseHelper(context);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			resultat = db.rawQuery("SELECT "+NETWORK_KPI_CHAMP_DATE+", SUM("+NETWORK_KPI_CHAMP_ERLANG+") AS traffic, SUM("+NETWORK_KPI_CHAMP_REVENU+") AS revenu, AVG("+NETWORK_KPI_CHAMP_CALL_DROP+") AS cdr, AVG("+NETWORK_KPI_CHAMP_CALL_SSR+") AS cssr, AVG("+NETWORK_KPI_CHAMP_AVAILABILITY+") AS avail FROM Network_KPI WHERE Network = '3G' GROUP BY "+NETWORK_KPI_CHAMP_DATE+"", null);
			return resultat;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Cursor getRCSKPI(Context context){
		Cursor resultat;
		try {
			dbHelper = new DatabaseHelper(context);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			resultat = db.rawQuery("SELECT "+NETWORK_KPI_CHAMP_DATE+", SUM("+NETWORK_KPI_CHAMP_ERLANG+") AS traffic, SUM("+NETWORK_KPI_CHAMP_REVENU+") AS revenu, AVG("+NETWORK_KPI_CHAMP_CALL_DROP+") AS cdr, AVG("+NETWORK_KPI_CHAMP_CALL_SSR+") AS cssr, AVG("+NETWORK_KPI_CHAMP_AVAILABILITY+") AS avail FROM Network_KPI WHERE Network = 'RCS' GROUP BY "+NETWORK_KPI_CHAMP_DATE+"", null);
			return resultat;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Cursor getRCS(Context context){
		Cursor resultat;
		try {
			dbHelper = new DatabaseHelper(context);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			resultat = db.rawQuery("SELECT "+NETWORK_KPI_CHAMP_DATE+", SUM("+NETWORK_KPI_CHAMP_ERLANG+") AS traffic, SUM("+NETWORK_KPI_CHAMP_REVENU+") AS revenu FROM Network_KPI WHERE Network = 'RCS' GROUP BY "+NETWORK_KPI_CHAMP_DATE+"", null);
			return resultat;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Network> find(Context context) {
		Network network_KPI = new Network();
		
		List<Network> network_KPIs = new ArrayList<Network>();
		try {
			dbHelper = new DatabaseHelper(context);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor resultat = db.rawQuery("SELECT * FROM Network_KPI", null);
			while(resultat.moveToNext()){
			network_KPI.setDateTime(resultat.getString(0));
			network_KPI.setNetwork(resultat.getString(1));
			network_KPI.setErlang(resultat.getDouble(2));
		//	network_KPI.setData(resultat.getDouble(4));
			network_KPI.setCDR(resultat.getDouble(4));
			network_KPI.setCSSR(resultat.getDouble(5));
			network_KPI.setAvail(resultat.getDouble(6));
			network_KPI.setRevenu(resultat.getDouble(7));
		//	Toast.makeText(context, "Network  : " + resultat.getString(1) + " CDR : " + resultat.getDouble(4) + " AVAIL : " + resultat.getDouble(6), Toast.LENGTH_LONG).show();
			network_KPIs.add(network_KPI);
			
			}	
			return network_KPIs;
		} catch (Exception e) {
			network_KPIs.clear();
			return network_KPIs;
			
		}
		
		
	}

//	private void onOpen() {
//		db = dbHelper.getWritableDatabase();
//	}
	
	
	public boolean create(Context context) {
		// listNet = nKpi.getAll();
		delete(context);
		dbHelper = new DatabaseHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		List<Network> listNet = new ArrayList<>();
		listNet = nKpi.SqlServerToSqlite(context);
		//Toast.makeText(context, "Voir : " + listNet.size(), Toast.LENGTH_LONG).show();
		
		try {
			for(Network network : listNet){
				ContentValues values = new ContentValues();
				values.put(NETWORK_KPI_CHAMP_DATE, network.getDateTime());
				values.put(NETWORK_KPI_CHAMP_NETWORK, network.getNetwork());
				values.put(NETWORK_KPI_CHAMP_ERLANG, network.getErlang());
			//	values.put(NETWORK_KPI_CHAMP_DATA, network.getData());
				values.put(NETWORK_KPI_CHAMP_CALL_DROP, network.getCDR());
				values.put(NETWORK_KPI_CHAMP_CALL_SSR, network.getCSSR());
				values.put(NETWORK_KPI_CHAMP_AVAILABILITY, network.getAvail());
				values.put(NETWORK_KPI_CHAMP_REVENU, network.getRevenu());
			//	Toast.makeText(context, "Network : " + network.getNetwork() + " CDR : " + network.getCDR() + " AVAIL : " + network.getAvail(), Toast.LENGTH_LONG).show();
				db.insert(TABLE_NETWORK_KPI, null, values);
			//	values.clear();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public int update() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		return db.update(TABLE_NETWORK_KPI, values, null, null);
	}

	
	public int delete(Context context) {
		
		dbHelper = new DatabaseHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		return db.delete(TABLE_NETWORK_KPI, null,null);
		
	}



}
