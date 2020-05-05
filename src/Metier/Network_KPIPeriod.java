package Metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.Network;
import Entity.RCS;
import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

public class Network_KPIPeriod {// WHERE Historique.Datetime BETWEEN '2016-06-19' AND '2016-06-21'

	public List<Network> getAll(Context context, String DateStart, String DateEnd){
		List<Network> list = new ArrayList<>();
		try {
			Statement state = connexion(context).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet query = state.executeQuery("SELECT Historique.Datetime, SUM(Erlang) AS traffic, SUM(Revenu) AS revenu, SUM(CDR) AS cdr, SUM(CSSR) AS cssr, SUM(Avail) AS avail FROM Historique WHERE Historique.Datetime BETWEEN '"+DateStart+"' AND '"+DateEnd+"' GROUP BY Historique.Datetime");
			
			while (query.next()) {
				Network network = new Network();
				network.setDateTime(query.getString("Datetime"));
				//network.setNetwork(query.getString("Network"));
				network.setErlang(query.getDouble("traffic"));
				//network.setData(query.getInt("data"));
				network.setCDR(query.getDouble("cdr"));
				network.setCSSR(query.getDouble("cssr"));
				network.setAvail(query.getDouble("avail"));
				network.setRevenu(query.getDouble("revenu"));
				
				list.add(network);
			}
			// Toast.makeText(context, "Nombre ===> " +list.size(), Toast.LENGTH_LONG).show();
			return list;
		} catch (Exception e) {
			Toast.makeText(context, "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		}
	}
	
	public List<Network> get2GKPI(Context context){
		List<Network> list = new ArrayList<>();
		try {
			Statement state = connexion(context).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet query = state.executeQuery("SELECT Historique.Datetime, SUM(Erlang) AS traffic, SUM(Revenu) AS revenu, SUM(CDR) AS cdr, SUM(CSSR) AS cssr, SUM(Avail) AS avail FROM Historique WHERE Network = '2G' AND Historique.Datetime BETWEEN DATEADD(DAY, -31, GETDATE()) AND GETDATE() GROUP BY Historique.Datetime");
			
			while (query.next()) {
				Network network = new Network();
				network.setDateTime(query.getString("Datetime"));
				//network.setNetwork(query.getString("Network"));
				network.setErlang(query.getDouble("traffic"));
				//network.setData(query.getInt("data"));
				network.setCDR(query.getDouble("cdr"));
				network.setCSSR(query.getDouble("cssr"));
				network.setAvail(query.getDouble("avail"));
				network.setRevenu(query.getDouble("revenu"));
				
				list.add(network);
			}
			 // Toast.makeText(context, "Nombre ===> " +list.size(), Toast.LENGTH_LONG).show();
			return list;
		} catch (Exception e) {
			Toast.makeText(context, "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		}
	}
	
	public List<Network> get3gKPI(Context context){
		List<Network> list = new ArrayList<>();
		try {
			Statement state = connexion(context).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet query = state.executeQuery("SELECT Historique.Datetime, SUM(Erlang) AS traffic, SUM(Revenu) AS revenu, SUM(CDR) AS cdr, SUM(CSSR) AS cssr, SUM(Avail) AS avail FROM Historique WHERE Network = '3G' AND Historique.Datetime BETWEEN DATEADD(DAY, -31, GETDATE()) AND GETDATE() GROUP BY Historique.Datetime");
			
			while (query.next()) {
				Network network = new Network();
				network.setDateTime(query.getString("Datetime"));
				//network.setNetwork(query.getString("Network"));
				network.setErlang(query.getDouble("traffic"));
				//network.setData(query.getInt("data"));
				network.setCDR(query.getDouble("cdr"));
				network.setCSSR(query.getDouble("cssr"));
				network.setAvail(query.getDouble("avail"));
				network.setRevenu(query.getDouble("revenu"));
				
				list.add(network);
			}
			 // Toast.makeText(context, "Nombre ===> " +list.size(), Toast.LENGTH_LONG).show();
			return list;
		} catch (Exception e) {
			Toast.makeText(context, "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		}
	}
	
	public List<Network> getRCSKPI(Context context){
		List<Network> list = new ArrayList<>();
		try {
			Statement state = connexion(context).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet query = state.executeQuery("SELECT Historique.Datetime, SUM(Erlang) AS traffic, SUM(Revenu) AS revenu, SUM(CDR) AS cdr, SUM(CSSR) AS cssr, SUM(Avail) AS avail FROM Historique WHERE Network = 'RCS' AND Historique.Datetime BETWEEN DATEADD(DAY, -31, GETDATE()) AND GETDATE() GROUP BY Historique.Datetime");
			
			while (query.next()) {
				Network network = new Network();
				network.setDateTime(query.getString("Datetime"));
				//network.setNetwork(query.getString("Network"));
				network.setErlang(query.getDouble("traffic"));
				//network.setData(query.getInt("data"));
				network.setCDR(query.getDouble("cdr"));
				network.setCSSR(query.getDouble("cssr"));
				network.setAvail(query.getDouble("avail"));
				network.setRevenu(query.getDouble("revenu"));
				
				list.add(network);
			}
			 // Toast.makeText(context, "Nombre ===> " +list.size(), Toast.LENGTH_LONG).show();
			return list;
		} catch (Exception e) {
			Toast.makeText(context, "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		}
	}
	public List<RCS> getRCS(Context context){
		List<RCS> list = new ArrayList<>(); 
		try {
			Statement state = connexion(context).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet query = state.executeQuery("SELECT Historique.Datetime, SUM(Erlang) AS traffic, SUM(Revenu) AS revenu FROM Historique WHERE Network = 'RCS' AND Historique.Datetime BETWEEN DATEADD(DAY, -31, GETDATE()) AND GETDATE() GROUP BY Historique.Datetime");  
			
			while (query.next()) {
				RCS rcs = new RCS();
				rcs.setDateTime(query.getString("Datetime"));
				rcs.setErlang(query.getDouble("traffic"));
				rcs.setRevenu(query.getDouble("revenu"));
				list.add(rcs);
			}
			// Toast.makeText(context, "Nombre ===> " +list.size(), Toast.LENGTH_LONG).show();
			return list;
		} catch (Exception e) {
			Toast.makeText(context, "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		}
	}
	
	
	public String getDateUpdate(Context context){
		String dUpdate = ""; 
		try {
			Statement state = connexion(context).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet query = state.executeQuery("SELECT datejour FROM miseAjour GROUP BY datejour HAVING datejour = MAX(datejour)"); //  WHERE datejour = MAX(datejour) 
			
			while (query.next()) {
				
				dUpdate = query.getString("datejour");
			}
			return dUpdate;
		} catch (Exception e) {
			Toast.makeText(context, "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		}
	}
	
	public Connection connexion(Context context){
		Connection DbConn = null;
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
		      StrictMode.ThreadPolicy police = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		      StrictMode.setThreadPolicy(police);
		     DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://10.200.13.30;databaseName=NETPM;user=sa;password=Vodacom01;");
		      
		       return DbConn;    
		} catch (Exception e) {
			Toast.makeText(context, "Erreur de Connexion", Toast.LENGTH_LONG).show();
			return null;
		}
		      
		}  
}
