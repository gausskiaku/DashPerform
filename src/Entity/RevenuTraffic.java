package Entity;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;


public class RevenuTraffic {

	private String date;
	private int revenu_all;
	private int traffic_all;
	private int traffic_rcs;
	private int revenu_rcs;
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRevenu_all() {
		return revenu_all;
	}

	public void setRevenu_all(int revenu_all) {
		this.revenu_all = revenu_all;
	}

	public int getTraffic_all() {
		return traffic_all;
	}

	public void setTraffic_all(int traffic_all) {
		this.traffic_all = traffic_all;
	}

	public int getTraffic_rcs() {
		return traffic_rcs;
	}

	public void setTraffic_rcs(int traffic_rcs) {
		this.traffic_rcs = traffic_rcs;
	}

	public int getRevenu_rcs() {
		return revenu_rcs;
	}

	public void setRevenu_rcs(int revenu_rcs) {
		this.revenu_rcs = revenu_rcs;
	}

	
}
