package com.nmc.dashperform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Erreur extends Activity {

	Button again;
	String ERREUR = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_erreur);
		again = (Button) findViewById(R.id.btAgain);
		Toast.makeText(Erreur.this, "Please enable mobile network", Toast.LENGTH_LONG).show();
		
		again.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle id = getIntent().getExtras();
				if(id != null || ERREUR.equals("")){
					Intent intent = new Intent(Erreur.this, Accueil.class);
					startActivity(intent);
					finish();
				} else {
				Intent intent = new Intent(Erreur.this, MainActivity.class);
				startActivity(intent);
				finish();
				}
				
			}
		});
		
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK){
		Bundle bundle = data.getExtras();
		ERREUR = bundle.getString("ERREUR");
		}
	}
}
