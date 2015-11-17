package com.example.calculatesample001;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	//onCreateÇÃíÜÇ…Ç¢ÇÍÇÈÇ◊Ç´ÅB
	protected Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0;
	protected Button clear,plus,minus,kakeru,waru,equal,bs;
	protected TextView textView;
	
	protected double buf = 0;
	protected String lastNum ="";
	protected String lastOperation = "";
	protected String textSituation = "initial";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ArrayList<Button> list = new ArrayList<Button>();
        ArrayList<Button> opelist = new ArrayList<Button>();
        
        this.b1		  = (Button)findViewById(R.id.b1); list.add(b1);
        this.b2		  = (Button)findViewById(R.id.b2); list.add(b2);
        this.b3 	  = (Button)findViewById(R.id.b3); list.add(b3);
        this.b4 	  = (Button)findViewById(R.id.b4); list.add(b4);
        this.b5 	  = (Button)findViewById(R.id.b5); list.add(b5);
        this.b6 	  = (Button)findViewById(R.id.b6); list.add(b6);
        this.b7 	  = (Button)findViewById(R.id.b7); list.add(b7);
        this.b8 	  = (Button)findViewById(R.id.b8); list.add(b8);
        this.b9 	  = (Button)findViewById(R.id.b9); list.add(b9);
        this.b0 	  = (Button)findViewById(R.id.b0); list.add(b0);
        this.clear 	  = (Button)findViewById(R.id.clear); list.add(b4);
        
        this.plus 	  = (Button)findViewById(R.id.bplus); 	opelist.add(plus);
        this.minus 	  = (Button)findViewById(R.id.bminus);	opelist.add(minus);
        this.kakeru   = (Button)findViewById(R.id.bkakeru);	opelist.add(kakeru);
        this.waru	  = (Button)findViewById(R.id.bwaru);   opelist.add(waru);
        this.equal 	  = (Button)findViewById(R.id.bequal);  opelist.add(equal);
        
        this.bs       = (Button)findViewById(R.id.bbs);
        
        this.textView = (TextView)findViewById(R.id.textView1);
        textView.setText("0");
        
        OnClickListener listener = new OnClickListener() {
			public void onClick(View v) {
				if( textSituation.equals("initial")){ 
					textView.setText("");
					textSituation = "adding";
				}
				if ( textSituation.equals("onClear")){
					clear();
					textSituation = "adding";
				}
				String number = "";
				Button button = (Button)v;
				
				number = button.getText().toString();
				if ( v.getId() == R.id.clear){ 
					textView.setText("0");
					buf = 0;
					textSituation = "onClear";
					lastOperation = "";
					System.out.println("buf(onClear) : "+ buf);
					return; 
				}
				String onView = textView.getText().toString() + number;
				textView.setText(onView);
			}
		};
		
		OnClickListener listener2 = new OnClickListener() {
			public void onClick(View v) {
				String str_number = textView.getText().toString();
				double number = 0;
				
				if(str_number == null || str_number.equals("0")){ number = 0;}
				else{
					number = Double.parseDouble(str_number);
				}
				
				if (! lastOperation.equals("equal")){
					if( v.getId() == R.id.bplus){ 
						if ( buf == 0 ){ buf = number; }
						else{buf = buf + number;}
						lastOperation = "plus"; 
						System.out.println("plus invoked");
					}
					if( v.getId() == R.id.bminus){  
						if ( buf == 0){ buf = number; }
						else{	buf = buf - number;	}
						lastOperation = "minus";
						System.out.println("minus invoked");
					}
					if( v.getId() == R.id.bkakeru){ 
						if ( buf == 0 ){ buf = number; }
						else{ buf = buf * number;}
						lastOperation = "kakeru";
					}
					if( v.getId() == R.id.bwaru){  
						if ( buf == 0){ buf = number; }
						else{buf = buf / number; }
						lastOperation = "waru";
					}
					System.out.println("buf :" + buf);
					
					textSituation = "onClear";
				}
				else{
					if ( v.getId() == R.id.bplus){ buf = number; lastOperation = "plus"; }
					if ( v.getId() == R.id.bminus){ buf = number; lastOperation = "minus"; }
					if ( v.getId() == R.id.bkakeru){ buf = number; lastOperation = "kakeru"; }
					if ( v.getId() == R.id.bwaru){ buf = number; lastOperation = "waru"; }
					
				}
				
				if( v.getId() == R.id.bequal){
					if(lastOperation.equals("plus")){ buf = buf + number;}
					if(lastOperation.equals("minus")){ buf = buf - number;}
					if(lastOperation.equals("kakeru")){ buf = buf * number;}
					if(lastOperation.equals("waru")){ buf = buf / number;}
					System.out.println("buf : " + buf);
					try{
						textView.setText(Double.toString(buf));
						buf = 0;
						lastOperation = "equal";
					}catch(NumberFormatException e){
						System.out.println("in equal onClick NumberFormatExcepition î≠ê∂");
					}
				}
				else{
					//textView.setText("");
				}
			}
			
		};
		
		OnClickListener listener3 = new OnClickListener() {
			public void onClick(View v) {
				if ( v.getId() == R.id.bbs){
					String text = textView.getText().toString();
					text = text.substring(0,text.length() - 1);
					textView.setText(text);
				}
			}
		};
       
		for(Button button : list){
			button.setOnClickListener(listener);
		}
		for(Button button : opelist){
			button.setOnClickListener(listener2);
		}
        
        bs.setOnClickListener(listener3);
        
        clear.setOnClickListener( listener );
    }
    
    
    private void clear(){
    	textView.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
