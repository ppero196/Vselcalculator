package crakeron.vsel.calctest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class VselcalculatortestActivity extends Activity {
	private EditText freqbox1;
	private EditText freqbox2;
	private EditText freqbox3;
	private EditText freqbox4;
	private EditText voltbox1;
	private EditText voltbox2;
	private EditText voltbox3;
	private EditText voltbox4;
	private Spinner spinner;
	
	public int freq1;
    public int freq2;
    public int freq3;
    public int freq4;
    public int volt1;
    public int volt2;
    public int volt3;
    public int volt4;
    
    public boolean stop=false;
    public boolean freq4ornot=false;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        
        freqbox1 = (EditText) findViewById(R.id.freq1);
        freqbox2 = (EditText) findViewById(R.id.freq2);
        freqbox3 = (EditText) findViewById(R.id.freq3);
        freqbox4 = (EditText) findViewById(R.id.freq4);
        voltbox1 = (EditText) findViewById(R.id.volt1);
        voltbox2 = (EditText) findViewById(R.id.volt2);
        voltbox3 = (EditText) findViewById(R.id.volt3);
        voltbox4 = (EditText) findViewById(R.id.volt4);
        
        spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.spinner_choices, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
        			public void onItemSelected(AdapterView <?> adapter, View v, int pos, long lng) {
        				if (pos==0){freq4ornot=false;}
        				if (pos==1){freq4ornot=true;}
        				Toast.makeText(getApplicationContext(),"freq4ornot bool is " + freq4ornot, Toast.LENGTH_SHORT).show();
        			}
    	  
        			public void onNothingSelected(AdapterView <?> arg0) {
        				//nothing ATM
        			}
        });
    }
 
    
    public void button_pressed(View button) { 
    	voltbox1.setText("");
    	voltbox2.setText("");
    	voltbox3.setText("");
    	voltbox4.setText("");
    	stop=false;
    	// 1. Grab values in textboxes freq1,2,3 (and 4, depending on Spinner value?) and store their values
    	grab_values(freq4ornot);
    	// 2. Call calculate function with 3 (or 4) arguments
    	calculate(freq1, freq2, freq3, freq4, freq4ornot);
    	// 3. call function to display each result in correct box, if stop=true, then all boxes will be displayed empty
    	display_volt(freq4ornot);
    	if(stop==true) error_empty();
    }
    
    public void grab_values(boolean freq4ornot){
    	String freq1Value = freqbox1.getText().toString();//fetch what's in edittextbox and store it in a string
    	if(freq1Value.length()!=0){
    		freq1 = Integer.parseInt(freq1Value);//transform the string into an int and store it in our variable
    		}
    	if(freq1Value.length()==0){
    		stop=true;
    		freq1=0;
    		}
    	
    	String freq2Value = freqbox2.getText().toString();
    	if(freq2Value.length()!=0){
          	freq2 = Integer.parseInt(freq2Value);//transform the string into an int and store it in our variable
        	}
        	if(freq2Value.length()==0){//check if user entered a value in box, otherwise causes crash
        	stop=true;        		  		
        	freq2=0;
        	}
        	
    	String freq3Value = freqbox3.getText().toString();
    	if(freq3Value.length()!=0){
          	freq3 = Integer.parseInt(freq3Value);
        	}
        	if(freq3Value.length()==0){
        		stop=true;        		     	
        		freq3=0;
        	}
        	
    	if (freq4ornot==true){
    		String freq4Value = freqbox4.getText().toString();
    		if(freq4Value.length()!=0){
    	      	freq4 = Integer.parseInt(freq4Value);
    	    	}
    	    	if(freq4Value.length()==0){
    	    		stop=true;    	    		           		
    	    		freq4=0;
    	    	}
    	}
    }

    public void calculate(int freq1,int freq2, int freq3, int freq4, boolean freq4ornot){
    	volt1 = formula(freq1);
    	volt2 = formula(freq2);
    	volt3 = formula(freq3);
        if (freq4ornot==true){volt4 = formula(freq4);}
    }
    
    
    public int formula(int freq){
    	int volt = ((freq/20)+2);
    	return volt;
    }


    public void display_volt (boolean freq4ornot){
    	if(stop==false){
    		voltbox1.setText(String.valueOf(volt1));
    		voltbox2.setText(String.valueOf(volt2));
    		voltbox3.setText(String.valueOf(volt3));
    		if(freq4ornot==true){voltbox4.setText(String.valueOf(volt4));}
    	}
    	
    }

    
    public void error_empty(){
    	Toast.makeText(getApplicationContext(), "Please enter a frequency in all the boxes", Toast.LENGTH_LONG).show();
    	/* For debug purposes
    	Toast.makeText(getApplicationContext(),"stop bool is " + stop, Toast.LENGTH_LONG).show();*/
    }
    
    
    
    
}