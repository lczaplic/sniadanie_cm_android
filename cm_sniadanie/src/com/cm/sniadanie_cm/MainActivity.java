package com.cm.sniadanie_cm;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

import com.cm.sniadanie_cm.json.Relic;
import com.cm.sniadanie_cm.json.RelicJsonWrapper;
import com.cm.sniadanie_cm.networking.OtwarteZabytkiApi;
import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView title;
    EditText name;
    EditText place;

    EditText et_from;
    EditText et_to;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        title = (TextView) findViewById(R.id.title);
        title.setText("Nasz napis Otwarte zabytki");
        
        name = (EditText) findViewById(R.id.name);
        
        place = (EditText) findViewById(R.id.place);
        
        et_from = (EditText) findViewById(R.id.from);
        
        et_to = (EditText) findViewById(R.id.to);

            
        Button searchBtn = (Button) findViewById(R.id.search_btn);
        
        searchBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
            	
            	
            	//get values
            	String strName = name.getText().toString();
            	
            	String strPlace = place.getText().toString();
            	
            	String strFrom = et_from.getText().toString();
            	
            	String strTo = et_to.getText().toString();
            	
            	

            	OtwarteZabytkiApi api = getApi();
            	
            	
            	Callback<RelicJsonWrapper> cb = new Callback<RelicJsonWrapper>() {

					@Override
					public void failure(RetrofitError rError) {
						Log.e("network", "failure on network request");		
						Log.e("network", rError.getLocalizedMessage());					

					}

					@Override
					public void success(RelicJsonWrapper relicWrapper, Response response) {
						Log.d("network", "succes !!!!!!!! ");
						
						for (Relic r : relicWrapper.relics){
							Log.d("relic name", r.identification);
							
						}
						
						
						
					}
            		
				};
            	
            	
            	api.getRelics(strPlace, strName, strFrom, strTo, cb);
            	
            	
            	
            	
                
                
                
                
                
            }
        } );
        
    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public OtwarteZabytkiApi getApi(){
    	RestAdapter restAdapter = new RestAdapter.Builder()
    		.setServer("http://otwartezabytki.pl/api/v1")
    		.setConverter( new GsonConverter( new Gson()))
    		.build();
    	
    	OtwarteZabytkiApi api = restAdapter.create( OtwarteZabytkiApi.class);
    	
    	return api;

    	
    	
//    	RestAdapter restAdapter = new RestAdapter.Builder()
//        .setServer("http://otwartezabytki.pl/api/v1")
//        .setConverter(new GsonConverter(new Gson()))
//        .build();
//
//    	OtwarteZabytkiApi api = restAdapter.create(OtwarteZabytkiApi.class);
    }
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
