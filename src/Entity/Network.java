package Entity;

public class Network {

	private String DateTime;
	private String network;
	private double Erlang;
	private double data;
	private double CDR;
	private double CSSR;
	private double avail;
	private double revenu;
	
	
	public double getErlang() {
		return Erlang;
	}
	public void setErlang(double erlang) {
		Erlang = erlang;
	}
	public double getData() {
		return data;
	}
	public void setData(double data) {
		this.data = data;
	}
	public double getCDR() {
		return CDR;
	}
	public void setCDR(double cDR) {
		CDR = cDR;
	}
	public double getCSSR() {
		return CSSR;
	}
	public void setCSSR(double cSSR) {
		CSSR = cSSR;
	}
	public double getAvail() {
		return avail;
	}
	public void setAvail(double avail) {
		this.avail = avail;
	}
	public double getRevenu() {
		return revenu;
	}
	public void setRevenu(double revenu) {
		this.revenu = revenu;
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}	
	
}
