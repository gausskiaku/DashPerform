package com.nmc.dashperform;



import java.util.ArrayList;
import java.util.List;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import Entity.Network;
import Entity.RCS;
import Metier.Network_KPI;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Rev_Traf extends Activity {

	
	LineChart lineChart = null;
	LineChart lineChartRCS = null;
	Network_KPI network_KPI = new Network_KPI();
	TextView dateUpdate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rev__traf);
		
		lineChart = (LineChart) findViewById(R.id.chart);
		lineChartRCS = (LineChart) findViewById(R.id.chartRCS);
		dateUpdate = (TextView) findViewById(R.id.txtDateUpdateRev);
		
		try {
			ConnectivityManager cmanager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
			NetworkInfo ninfo = cmanager.getActiveNetworkInfo();
			if(ninfo != null && ninfo.isConnected()){
				
				List<Network> listNet = network_KPI.getAll();
			    int ii = 0;
			    ArrayList<Entry> entries = new ArrayList<>();
			    ArrayList<Entry> entriesREVENU = new ArrayList<>();
			    ArrayList<String> labels = new ArrayList<String>();
			    
			    lineChart.setAutoScaleMinMaxEnabled(false);
			    double trafficMontant = new Long(0);
			    double revenuMontant = new Long(0);
			    String date = null;
			    
			    
				for(Network nwork : listNet){
			        entries.add(new Entry((float) nwork.getErlang(),ii));
			        entriesREVENU.add(new Entry((float) nwork.getRevenu(),ii));
			        trafficMontant = nwork.getErlang();
			        revenuMontant = nwork.getRevenu();
			        date = nwork.getDateTime();
			        ii++;
			        
			        String[] lab = nwork.getDateTime().split("00:00:00.0");
			        labels.add(lab[0]);  
			        
				}
				
		        LineDataSet dataset = new LineDataSet(entries, "Traffic : " + (int) trafficMontant + " Erl");
		        dataset.setColor(Color.RED);
		        dataset.setDrawValues(false);
		        dataset.setDrawFilled(false);
		        dataset.setCircleSize(0); // Enlever les Point en mattant la taille 0
		        dataset.setDrawCubic(true); // Forme du cube
		      //dataset.setColors(ColorTemplate.VORDIPLOM_COLORS); //Couleur de la courbe
		        
		        lineChart.getAxisLeft().setDrawGridLines(false); // Enlever les grid Verticale
		        lineChart.getXAxis().setDrawGridLines(false);
		        
		        lineChart.getAxisRight().setDrawGridLines(false); // Enlever les grid Horinzotale
		        lineChart.getXAxis().setDrawGridLines(false);
		        
		       // YAxis yAxisRight = lineChart.getAxisRight(); // Enlever les grid reference a droite
		       // yAxisRight.setEnabled(false); 
		        
		        lineChart.getXAxis().setLabelRotationAngle(90); // Tourner le txtAxis
		        lineChart.getXAxis().setTextSize(8f); // Taille des ecrit X
		        
		        lineChart.getAxisLeft().setTextSize(8f); // La taille de Axis de gauche
		        lineChart.getAxisRight().setTextSize(8f); // La taille de Axis de gauche
		       
		        lineChart.getXAxis().setPosition(XAxisPosition.BOTTOM);
		       // lineChart.getAxisLeft().setMinWidth(0);
		        lineChart.getAxisLeft().setAxisMinValue(0); // Value Min
		        lineChart.getAxisRight().setAxisMinValue(0);
		        //lineChart.getAxisLeft().setAxisMaxValue(800000); // Value Max
		        //lineChart.getAxisLeft().(600000);
		        lineChart.setDoubleTapToZoomEnabled(false); // Desactiver le Zoom
		        
		        
		        
		        LineData data = new LineData(labels, dataset);
		        List<ILineDataSet> lines = new ArrayList<ILineDataSet> ();

		 // ---------------------------------------------------------------------------------------------- //
		        LineDataSet datasetTwo = new LineDataSet(entriesREVENU, "Revenue : " + revenuMontant + " $");
		        datasetTwo.setColor(Color.BLUE);
		        datasetTwo.setDrawValues(false);
		        datasetTwo.setDrawFilled(false);
		        datasetTwo.setCircleSize(0); // Enlever les Point en mattant la taille 0
		        datasetTwo.setDrawCubic(true);
		        datasetTwo.setAxisDependency(AxisDependency.RIGHT);
		        LineData dataTwo = new LineData(labels, datasetTwo);
		        
		        
		        
		       // lineChart.get
		        
		        // dataset.setDrawFilled(true);
		        
		        lines.add(dataset);
		        lines.add(datasetTwo);
		        lineChart.setData(new LineData(labels, lines));
		        lineChart.setDescription(""); // Donner la description
		        
		        lineChart.animateY(5000); // Activer l'animation
		        
		//------------------------------------------------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------------------------------------------
				// RCS
		        
		        List<RCS> listRCS = network_KPI.getRCS(Rev_Traf.this);
			    int i = 0;
			    ArrayList<Entry> entriesRCS = new ArrayList<>();
			    ArrayList<Entry> entriesRevenuRCS = new ArrayList<>();
			    ArrayList<String> labelsRcs = new ArrayList<String>();
			    
			    lineChartRCS.setAutoScaleMinMaxEnabled(false);
			    double trafficMontantRCS = 0;
			    double revenuMontantRCS = 0;
				for(RCS rcs : listRCS){
			        entriesRCS.add(new Entry((float) rcs.getErlang(),i));
			        entriesRevenuRCS.add(new Entry((float) rcs.getRevenu(),i));
			        trafficMontantRCS = rcs.getErlang();
			        revenuMontantRCS = rcs.getRevenu();
			        i++;
			        String[] labRcs = rcs.getDateTime().split("00:00:00.0");
			        labelsRcs.add(labRcs[0]);  
			        
				}
				
		        LineDataSet datasetRcs = new LineDataSet(entriesRCS, "Traffic : " +(int) trafficMontantRCS +" Erl");
		        datasetRcs.setColor(Color.RED);
		        datasetRcs.setDrawValues(false);
		        datasetRcs.setDrawFilled(false);
		        datasetRcs.setCircleSize(0); // Enlever les Point en mattant la taille 0
		        datasetRcs.setDrawCubic(true);//Couleur de la courbe
		        
		        lineChartRCS.getAxisLeft().setDrawGridLines(false); // Enlever les grid Verticale
		        lineChartRCS.getXAxis().setDrawGridLines(false);
		        
		        lineChartRCS.getAxisRight().setDrawGridLines(false); // Enlever les grid Horinzotale
		        lineChartRCS.getXAxis().setDrawGridLines(false);
		        
		        lineChartRCS.getXAxis().setLabelRotationAngle(90); // Tourner le txtAxis
		        lineChartRCS.getXAxis().setTextSize(8f); // Taille des ecrit X
		        
		        lineChartRCS.getAxisLeft().setTextSize(8f); // La taille de Axis de gauche
		        lineChartRCS.getAxisRight().setTextSize(8f); // La taille de Axis de gauche
		       
		        lineChartRCS.getXAxis().setPosition(XAxisPosition.BOTTOM);
		        lineChartRCS.getAxisLeft().setAxisMinValue(0); // Value Min
		        lineChartRCS.getAxisRight().setAxisMinValue(0);
		        lineChartRCS.setDoubleTapToZoomEnabled(false); // Desactiver le Zoom
		        
		        LineData dataRCS = new LineData(labelsRcs, datasetRcs);
		        List<ILineDataSet> linesRCS = new ArrayList<ILineDataSet> ();

		 // ---------------------------------------------------------------------------------------------- //
		        LineDataSet datasetTwoRCS = new LineDataSet(entriesRevenuRCS, "Revenue : " + revenuMontantRCS + " $");
		        datasetTwoRCS.setColor(Color.BLUE);
		        datasetTwoRCS.setDrawValues(false);
		        datasetTwoRCS.setDrawFilled(false);
		        datasetTwoRCS.setCircleSize(0); // Enlever les Point en mattant la taille 0
		        datasetTwoRCS.setDrawCubic(true);
		        datasetTwoRCS.setAxisDependency(AxisDependency.RIGHT);
		        LineData dataTwoRCS = new LineData(labelsRcs, datasetTwoRCS);
		        
		        linesRCS.add(datasetRcs);
		        linesRCS.add(datasetTwoRCS);
		        lineChartRCS.setData(new LineData(labelsRcs, linesRCS));
		        lineChartRCS.setDescription(""); // Donner la description
		        
		        
		        lineChart.setPinchZoom(false); // Interdir le Zooooom
		        lineChart.setScaleEnabled(false); // Interdir le Zooooom
		        
		        lineChartRCS.setPinchZoom(false); // Interdir le Zooooom
		        lineChartRCS.setScaleEnabled(false); // Interdir le Zooooom
		        
		        
		        lineChart.setTouchEnabled(false);
		        lineChartRCS.setTouchEnabled(false);
		        
		        
		        lineChartRCS.animateY(5000); // Activer l'animation
		        
		        String[] dateOnly = date.split("00:00:00.0");
		        dateUpdate.setText("Last update : " + dateOnly[0].toString());
				
			} else {
				Log.e("ERREUR", "Iciiiiiiiii");
				Toast.makeText(Rev_Traf.this, "Please enable mobile network", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getBaseContext(), Erreur.class);
				startActivity(intent);
				finish();
				
			}
			
		} catch (Exception e) {
			Intent intent = new Intent(getBaseContext(), Erreur.class);
			startActivity(intent);
			finish();
			Log.e("ERREUR", e.getMessage());
		//	Toast.makeText(getBaseContext(), "Probleme : " + e.getMessage(), Toast.LENGTH_LONG).show()
			}
}
	
}
