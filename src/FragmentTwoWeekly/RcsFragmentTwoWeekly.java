package FragmentTwoWeekly;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.nmc.dashperform.Erreur;
import com.nmc.dashperform.R;
import com.nmc.dashperform.Rcs;
import com.nmc.meter.SpeedometerView;
import Entity.Network;
import Metier.Network_KPI;
import Metier.Network_KPITwoWeekly;
import Metier.Network_KPIWeekly;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RcsFragmentTwoWeekly extends Fragment {
	LineChart lineChart = null;
	Network_KPITwoWeekly network_KPI = new Network_KPITwoWeekly();
	private SpeedometerView speedometerCDR;
	private SpeedometerView speedometerCSSR;
	private SpeedometerView speedometerAVAIL;
	private TextView txtCDR;
	private TextView txtCSSR;
	private TextView txtAVAIL;
	private ImageView img;
	private View view;
	ScaleGestureDetector SGD;
	Float scale = 1f;
	private TextView dateUpdate;
	private float mPosX = 0f;
	private float mPosY = 0f;

	private float ScaleX;
	
	private float mLastTouchX;
	private float mLastTouchY;
	private static final int INVALID_POINTER_ID = -1;
	private int mActivePointerId = INVALID_POINTER_ID;
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_rcs, container, false);
		
		lineChart = (LineChart) view.findViewById(R.id.chartRCS);
		speedometerCDR = (SpeedometerView) view.findViewById(R.id.speedometerRcsCDR);
		speedometerCSSR = (SpeedometerView) view.findViewById(R.id.speedometerRcsCSSR);
		speedometerAVAIL = (SpeedometerView) view.findViewById(R.id.speedometerRcsAVAIL);
		txtCDR = (TextView) view.findViewById(R.id.txtRcsCDR);
		txtCSSR = (TextView) view.findViewById(R.id.txtRcsCSSR);
		txtAVAIL = (TextView) view.findViewById(R.id.txtRcsAVAIL);
		dateUpdate = (TextView) view.findViewById(R.id.txtDateUpdateRCS);
		SGD = new ScaleGestureDetector(getActivity(), new ScaleListener());
		
//	------------------------------------ CDR -----------------------------------------------------		
		speedometerCDR.setLabelConverter(new SpeedometerView.LabelConverter() {
			
			@Override
			public String getLabelFor(double progress, double maxProgress) {
				
				return String.valueOf((int) Math.round(progress));
			}
		});
		
		// configure value range and ticks
		speedometerCDR.setMaxSpeed(1.166666666666667);
		speedometerCDR.setMajorTickStep(0.16);
		speedometerCDR.setMinorTicks(5);
		

		  // Configure value range colors
		speedometerCDR.addColoredRange(0.0, 0.2333333333333334, Color.rgb(51,153,51)); // Vert Gras 
		speedometerCDR.addColoredRange(0.2333333333333334, 0.4666666666666668, Color.rgb(140,191,38)); // Vert leger
		speedometerCDR.addColoredRange(0.4666666666666668, 0.7000000000000002, Color.YELLOW);
		speedometerCDR.addColoredRange(0.7000000000000002, 0.9333333333333336, Color.rgb(240,150,9)); // Orange
		speedometerCDR.addColoredRange(0.9333333333333336, 1.166666666666667, Color.RED);
		
//	-------------------------------- CSSR -----------------------------------------------------------		
		
		speedometerCSSR.setLabelConverter(new SpeedometerView.LabelConverter() {
			
			@Override
			public String getLabelFor(double progress, double maxProgress) {
				
				return String.valueOf((int) Math.round(progress));
			}
		});
		
		// configure value range and ticks
		speedometerCSSR.setMaxSpeed(1.166666666666667);
		speedometerCSSR.setMajorTickStep(0.16);
		speedometerCSSR.setMinorTicks(5);
		

		  // Configure value range colors
		speedometerCSSR.addColoredRange(0.0, 0.2333333333333334, Color.RED);
		speedometerCSSR.addColoredRange(0.2333333333333334, 0.4666666666666668, Color.rgb(240,150,9));
		speedometerCSSR.addColoredRange(0.4666666666666668, 0.7000000000000002, Color.YELLOW);
		speedometerCSSR.addColoredRange(0.7000000000000002, 0.9333333333333336, Color.rgb(140,191,38));
		speedometerCSSR.addColoredRange(0.9333333333333336, 1.166666666666667, Color.rgb(51,153,51));

//		-------------------------------- AVAIL -----------------------------------------------------------		
		
		speedometerAVAIL.setLabelConverter(new SpeedometerView.LabelConverter() {
				
				@Override
				public String getLabelFor(double progress, double maxProgress) {
					
					return String.valueOf((int) Math.round(progress));
				}
			});
			
			// configure value range and ticks
			speedometerAVAIL.setMaxSpeed(1.666666666666667);
			speedometerAVAIL.setMajorTickStep(0.22222222);
			speedometerAVAIL.setMinorTicks(5);
			

			  // Configure value range colors
			speedometerAVAIL.addColoredRange(0.0, 0.3333333333333333, Color.RED);
			speedometerAVAIL.addColoredRange(0.3333333333333333, 0.6666666666666666, Color.rgb(240,150,9));
			speedometerAVAIL.addColoredRange(0.6666666666666666, 0.9999999999999999, Color.YELLOW);
			speedometerAVAIL.addColoredRange(0.9999999999999999, 1.3333333333333333, Color.rgb(140,191,38));
			speedometerAVAIL.addColoredRange(1.3333333333333333, 1.6666666666666666, Color.rgb(51,153,51));
//		--------------------------------------------------------------------------------------------
		try {
			ConnectivityManager cmanager = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
			NetworkInfo ninfo = cmanager.getActiveNetworkInfo();
			if(ninfo != null && ninfo.isConnected()){
				List<Network> listNet = network_KPI.getRCSKPI(getActivity());
			    int ii = 0;
			    ArrayList<Entry> entriesCDR = new ArrayList<>();
			    ArrayList<Entry> entriesCSSR = new ArrayList<>();
			    ArrayList<Entry> entriesAVAIL = new ArrayList<>();
			    ArrayList<String> labels = new ArrayList<String>();
//			    Toast.makeText(getBaseContext(), "ok", Toast.LENGTH_LONG).show();
			    lineChart.setAutoScaleMinMaxEnabled(true);
			     double montantCDR = 0 ;
			    double montantCSSR = 0;
			    double montantAvail = 0;
			    String date = null;
			    
				for(Network nwork : listNet){
			        entriesCDR.add(new Entry(100 - (float) nwork.getCDR(),ii));
			        entriesCSSR.add(new Entry((float) nwork.getCSSR(),ii));
			        entriesAVAIL.add(new Entry((float) nwork.getAvail(),ii));
			        date = nwork.getDateTime();
			        
			        	montantAvail += nwork.getAvail();
				        montantCDR += nwork.getCDR();
				        montantCSSR += nwork.getCSSR();
					
			        
			        ii++;
			        
			        String[] lab = nwork.getDateTime().split("00:00:00.0");
			        labels.add(lab[0]);  
			        
				}
				
		        LineDataSet datasetCDR = new LineDataSet(entriesCDR, "Call Drop");
		        datasetCDR.setColor(Color.RED);
		        datasetCDR.setDrawValues(false);
		        datasetCDR.setDrawFilled(false);
		        datasetCDR.setCircleSize(0); // Enlever les Point en mattant la taille 0
		        datasetCDR.setDrawCubic(true);
		       // datasetCDR.setCubicIntensity(1f); // if gros line
		        
		        lineChart.getAxisLeft().setDrawGridLines(false); // Enlever les grid Verticale
		        lineChart.getXAxis().setDrawGridLines(false);
		        
		        lineChart.getAxisRight().setDrawGridLines(false); // Enlever les grid Horinzotale
		        lineChart.getXAxis().setDrawGridLines(false);
		        
		        lineChart.getXAxis().setLabelRotationAngle(90); // Tourner le txtAxis
		        lineChart.getXAxis().setTextSize(8f); // Taille des ecrit X
		        
		        lineChart.getAxisLeft().setTextSize(8f); // La taille de Axis de gauche
		        lineChart.getAxisRight().setTextSize(8f); // La taille de Axis de gauche
		       
		        lineChart.getXAxis().setPosition(XAxisPosition.BOTTOM);
		       // lineChart.getAxisLeft().setMinWidth(0);
		        lineChart.getAxisLeft().setAxisMinValue(0); // Value Min
		        lineChart.getAxisRight().setAxisMinValue(0);
		        lineChart.setDoubleTapToZoomEnabled(false);
		        lineChart.setEnabled(false); // Desactiver le Zoom
		        lineChart.setTouchEnabled(false); // Touch for cursor
		        
		        LineData data = new LineData(labels, datasetCDR);
		        List<ILineDataSet> lines = new ArrayList<ILineDataSet> ();

		 // ---------------------------------------------------------------------------------------------- //
		        LineDataSet datasetTwoCSSR = new LineDataSet(entriesCSSR, "Call SSR");
		        datasetTwoCSSR.setColor(Color.BLUE);
		        datasetTwoCSSR.setDrawValues(false);
		        datasetTwoCSSR.setDrawFilled(false);
		        datasetTwoCSSR.setCircleSize(0); // Enlever les Point en mattant la taille 0
		        datasetTwoCSSR.setDrawCubic(true);
		        datasetTwoCSSR.setAxisDependency(AxisDependency.LEFT);
		        LineData dataTwo = new LineData(labels, datasetTwoCSSR);
		        
		// ---------------------------------------------------------------------------------------------- //
		        LineDataSet datasetThreeAvail = new LineDataSet(entriesAVAIL, "Availablity");
		        datasetThreeAvail.setColor(Color.rgb(0,128,0));
		        datasetThreeAvail.setDrawValues(false);
		        datasetThreeAvail.setDrawFilled(false);
		        datasetThreeAvail.setCircleSize(0); // Enlever les Point en mattant la taille 0
		        datasetThreeAvail.setDrawCubic(true);
		        datasetThreeAvail.setAxisDependency(AxisDependency.LEFT);
		        LineData dataThree = new LineData(labels, datasetThreeAvail); 
		        
		        lines.add(datasetCDR);
		        lines.add(datasetTwoCSSR);
		        lines.add(datasetThreeAvail);
		        lineChart.setData(new LineData(labels, lines));
		        lineChart.setDescription(""); // Donner la description
		       // lineChart.getAxisLeft().setAxisMinValue(90);
		        YAxis yAxisRight = lineChart.getAxisRight(); // Enlever les grid reference a droite
		        yAxisRight.setEnabled(false);
		        
		        // For call drop
		        speedometerCDR.setSpeed((montantCDR/14));
		        txtCDR.setText("Call drop : " + new DecimalFormat("##.##").format((montantCDR/14)));
		        //txtCDR.setText(date);

		        String date_depart = listNet.get(0).getDateTime();
		        String[] dateLast = date.split("00:00:00.0");
		        String[] dateFirst = date_depart.split("00:00:00.0");
		        dateUpdate.setText("Last update : " + dateFirst[0].toString() + " to " + dateLast[0].toString());
		        
		        // For call Setup
		        try {
		        	speedometerCSSR.setSpeed((montantCSSR/14) - 98.83333333333333); // (0.2333333333333334 * 98.25) / 100
		            txtCSSR.setText("Call SSR : " + new DecimalFormat("##.##").format((montantCSSR/14)) + " %");
				} catch (Exception e) {
					speedometerCSSR.setSpeed(0); // (0.2333333333333334 * 98.25) / 100
		            txtCSSR.setText("Call SSR : " + new DecimalFormat("##.##").format((montantCSSR/14)) + " %");
		    //        txtCSSR.setTextColor(color)
				}
		        
		        // For AVAIL
		        try {
		        	speedometerAVAIL.setSpeed((montantAvail/14) - 98.33333333333333);
		        	txtAVAIL.setText("Available : " + new DecimalFormat("##.##").format((montantAvail/14)) + " %");
				} catch (Exception e) {
					speedometerAVAIL.setSpeed(0);
		        	txtAVAIL.setText("Available : " + new DecimalFormat("##.##").format((montantAvail/14)) + " %");
				}
		        lineChart.setPinchZoom(false); // Interdir le Zooooom
		        lineChart.setScaleEnabled(false); // Interdir le Zooooom
		        lineChart.animateY(2000); // Activer l'animation
			} else {
				Toast.makeText(getActivity(), "Please enable mobile network", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getActivity(), Erreur.class);
				startActivity(intent);
				getActivity().finish();
			}
			
		} catch (Exception e) {
			Intent intent = new Intent(getActivity(), Erreur.class);
			startActivity(intent);
			getActivity().finish();
		}
		
		view.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				SGD.onTouchEvent(event);
				// draw();
				 final int action = event.getAction();
			        switch (action & MotionEvent.ACTION_MASK) {
			        case MotionEvent.ACTION_DOWN: {
			            final float x = event.getX();
			            final float y = event.getY();
			            
			            mLastTouchX = x;
			            mLastTouchY = y;
			            mActivePointerId = event.getPointerId(0);
			            
			            
			            
			            final float dx = x - mLastTouchX;
		                final float dy = y - mLastTouchY;
		                mPosX += dx;
		                mPosY += dy;
		                Log.i("Touche DOWN", "mPosX : " + mPosX + "mPosY : " + mPosY);
		                
		                if(ScaleX > 1.0f){
			            v.setX(mPosX);
			            v.setY(mPosY);
			            }
			            break;
			        }

			        case MotionEvent.ACTION_MOVE: {
			            final int pointerIndex = event.findPointerIndex(mActivePointerId);
			            final float x = event.getX(pointerIndex);
			            final float y = event.getY(pointerIndex);

			            // Only move if the ScaleGestureDetector isn't processing a gesture.
			            if (!SGD.isInProgress()) {
			                final float dx = x - mLastTouchX;
			                final float dy = y - mLastTouchY;

			                mPosX += dx;
			                mPosY += dy;
			             //view.invalidate();
			            }

			            mLastTouchX = x;
			            mLastTouchY = y;

			            Log.i("Touche MOVE", "mPosX : " + mPosX + "mPosY : " + mPosY);
			            if(ScaleX > 1.0f){
				            v.setX(mPosX);
				            v.setY(mPosY);
				            }
			            
			            break;
			        }

			        case MotionEvent.ACTION_UP: {
			            mActivePointerId = INVALID_POINTER_ID;
			            break;
			        }

			        case MotionEvent.ACTION_CANCEL: {
			            mActivePointerId = INVALID_POINTER_ID;
			            break;
			        }

			        case MotionEvent.ACTION_POINTER_UP: {
			            final int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			            final int pointerId = event.getPointerId(pointerIndex);
			            if (pointerId == mActivePointerId) {
			                // This was our active pointer going up. Choose a new
			                // active pointer and adjust accordingly.
			                final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
			                mLastTouchX = event.getX(newPointerIndex);
			                mLastTouchY = event.getY(newPointerIndex);
			                mActivePointerId = event.getPointerId(newPointerIndex);
			            }
			            
			            
			            v.setX(0);
			            v.setY(0);
			            break;
			        }
			        }

				return true;
			}
		});
		
		return view;
	}
	

private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
		
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			scale = scale * detector.getScaleFactor();
			scale = Math.max(0.1f, Math.min(scale, 5f));
			// Toast.makeText(getApplicationContext(), "C bon : " + scale, Toast.LENGTH_LONG).show();
			float x = view.getScaleX();
			float y = view.getScaleY();
			
			Log.i("OYO", "Scale X : " + scale + "Scale Y : " + scale);
			if (scale < 0.9){
				view.setScaleX(1.0f);
				view.setScaleY(1.0f);	
			} else {
			view.setScaleX(scale);
			view.setScaleY(scale);
			ScaleX = scale;
			}
			view.invalidate();
			return true;
		}
		
		
	}
	
	
	
}
