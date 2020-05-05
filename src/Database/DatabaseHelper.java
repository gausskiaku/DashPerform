package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static final int VERSION = 1;
	public static final String DATABASE_NAME = "Kpi.db";
	
	// TABLE REVENU
	public static final String TABLE_REVENU = "Revenu";
	public static final String REVENU_CHAMP_DATE = "date";
	public static final String REVENU_CHAMP_REVENU_ALL = "revenu_all";
	public static final String REVENU_CHAMP_TRAFFIC_ALL = "traffic_all";
	public static final String REVENU_CHAMP_TRAFFIC_RCS = "traffic_rcs";
	public static final String REVENU_CHAMP_REVENU_RCS = "revenu_rcs";
	
	String CREATE_TABLE_REVENU = "CREATE TABLE " + TABLE_REVENU + " (" 
									+ REVENU_CHAMP_DATE + " TEXT PRIMARY KEY NOT NULL, "
									+ REVENU_CHAMP_TRAFFIC_ALL + " INTEGER, "
									+ REVENU_CHAMP_REVENU_ALL + " INTEGER, "
									+ REVENU_CHAMP_TRAFFIC_RCS + " INTEGER, "
									+ REVENU_CHAMP_REVENU_RCS + " INTEGER"
									+ ")";
	
	// TABLE 2G KPI
	public static final String TABLE_TWOG = "TwoG";
	public static final String TWOG_CHAMP_DATE = "date";
	public static final String TWOG_CHAMP_CALL_DROP = "call_drop";
	public static final String TWOG_CHAMP_CALL_SSR = "call_ssr";
	public static final String TWOG_CHAMP_AVAILABILITY = "availability";
	
	
	String CREATE_TABLE_TWOG = "CREATE TABLE " + TABLE_TWOG + " (" 
			+ TWOG_CHAMP_DATE + " TEXT PRIMARY KEY NOT NULL, "
			+ TWOG_CHAMP_CALL_DROP + " INTEGER, "
			+ TWOG_CHAMP_CALL_SSR + " INTEGER, "
			+ TWOG_CHAMP_AVAILABILITY + " INTEGER"
			+ ")";
	
	// TABLE 3G KPI
	public static final String TABLE_THREEG = "ThreeG";
	public static final String THREEG_CHAMP_DATE = "date";
	public static final String THREEG_CHAMP_CALL_DROP = "call_drop";
	public static final String THREEG_CHAMP_CALL_SSR = "call_ssr";
	public static final String THREEG_CHAMP_AVAILABILITY = "availability";
	
	String CREATE_TABLE_THREEG = "CREATE TABLE " + TABLE_THREEG + " (" 
			+ THREEG_CHAMP_DATE + " TEXT PRIMARY KEY NOT NULL, "
			+ THREEG_CHAMP_CALL_DROP + " INTEGER, "
			+ THREEG_CHAMP_CALL_SSR + " INTEGER, "
			+ THREEG_CHAMP_AVAILABILITY + " INTEGER"
			+ ")";
	
	// TABLE RCS
	public static final String TABLE_RCS = "Rcs";
	public static final String RCS_CHAMP_DATE = "date";
	public static final String RCS_CHAMP_CALL_DROP = "call_drop";
	public static final String RCS_CHAMP_CALL_SSR = "call_ssr";
	public static final String RCS_CHAMP_AVAILABILITY = "availability";
	
	String CREATE_TABLE_RCS = "CREATE TABLE " + TABLE_RCS + " (" 
			+ RCS_CHAMP_DATE + " TEXT PRIMARY KEY NOT NULL, "
			+ RCS_CHAMP_CALL_DROP + " INTEGER, "
			+ RCS_CHAMP_CALL_SSR + " INTEGER, "
			+ RCS_CHAMP_AVAILABILITY + " INTEGER"
			+ ")";
	
	public static final String TABLE_NETWORK_KPI = "Network_KPI";
	public static final String NETWORK_KPI_CHAMP_DATE = "date";
	public static final String NETWORK_KPI_CHAMP_ERLANG= "erlang";
	//public static final String NETWORK_KPI_CHAMP_DATA= "data";
	public static final String NETWORK_KPI_CHAMP_NETWORK = "network";
	public static final String NETWORK_KPI_CHAMP_CALL_DROP = "call_drop";
	public static final String NETWORK_KPI_CHAMP_CALL_SSR = "call_ssr";
	public static final String NETWORK_KPI_CHAMP_AVAILABILITY = "availability";
	public static final String NETWORK_KPI_CHAMP_REVENU = "revenu";
	
	String CREATE_TABLE_NETWORK_KPI = "CREATE TABLE " + TABLE_NETWORK_KPI + " ("
			+ NETWORK_KPI_CHAMP_DATE + " TEXT, "
			+ NETWORK_KPI_CHAMP_NETWORK + " TEXT, "
			+ NETWORK_KPI_CHAMP_ERLANG + " REAL, "
		//	+ NETWORK_KPI_CHAMP_DATA + " REAL, "
			+ NETWORK_KPI_CHAMP_CALL_DROP + " REAL, "
			+ NETWORK_KPI_CHAMP_CALL_SSR + " REAL, "
			+ NETWORK_KPI_CHAMP_AVAILABILITY + " REAL, "
			+ NETWORK_KPI_CHAMP_REVENU + " REAL "
			+")";
			
	
	
	private static DatabaseHelper instance;
	 
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }

	

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	//	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_NETWORK_KPI);
		// db.execSQL(CREATE_TABLE_REVENU);
		// db.execSQL(CREATE_TABLE_TWOG);
		// db.execSQL(CREATE_TABLE_THREEG);
		//db.execSQL(CREATE_TABLE_RCS);
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("DROP TABLE IF EXISTS " +  TABLE_REVENU);
		//db.execSQL("DROP TABLE IF EXISTS " +  TABLE_TWOG);
		//db.execSQL("DROP TABLE IF EXISTS " +  TABLE_THREEG);
		//db.execSQL("DROP TABLE IF EXISTS " +  TABLE_RCS);
		db.execSQL("DROP TABLE IF EXISTS " +  TABLE_NETWORK_KPI);
		onCreate(db);
		
	}
	
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}
	
	
	
	
	
//	public Cursor getAllData() {
		
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NETWORK_KPI, null);
//		return res;
//	}

}
