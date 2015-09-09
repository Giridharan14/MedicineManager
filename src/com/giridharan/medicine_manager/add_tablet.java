package com.giridharan.medicine_manager;



import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class add_tablet extends ActionBarActivity {
	TextToSpeech et1;
	EditText t1,t2,t3;
	RadioButton r1,r2;
	CheckBox c1,c2,c3;
	RadioGroup radiobaGroup;
	RadioButton radiobaButton;
	Button b1;
	String name,mor,aft,nig,bf;
	String tot_tab,amt,count1;
	int selectedId=0,count=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_tablet);

		  et1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
		         @Override
		         public void onInit(int status) {
		            if(status != TextToSpeech.ERROR) {
		               et1.setLanguage(Locale.UK);
		            }
		         }
		      });
	    		t1=(EditText)findViewById(R.id.editText1);	
	t2=(EditText)findViewById(R.id.editText2);
	t3=(EditText)findViewById(R.id.editText3);
	c1=(CheckBox)findViewById(R.id.checkBox1);
	c2=(CheckBox)findViewById(R.id.checkBox2);
	c3=(CheckBox)findViewById(R.id.checkBox3);
	b1 = (Button)findViewById(R.id.button1);
	radiobaGroup=(RadioGroup)findViewById(R.id.radioGroup1);
	b1.setOnClickListener(new View.OnClickListener() {
		@Override
		
		public void onClick(View v) {
			StringBuffer result = new StringBuffer();
			if(c1.isChecked()){mor="1";count++;}else{mor="0";}
			if(c2.isChecked()){aft="1";count++;}else{aft="0";}
			if(c3.isChecked()){nig="1";count++;}else{nig="0";}
			selectedId=radiobaGroup.getCheckedRadioButtonId();
			radiobaButton=(RadioButton)findViewById(selectedId);
			
			name= t1.getText().toString();
			bf = (String) radiobaButton.getText();
			tot_tab= (t2.getText().toString());
			amt=t3.getText().toString();
			count1= ""+count;
			//here
			String toSpeak ="You have added the Tablet  "+name+" have to take "+bf+" Total number of tablet you have "+tot_tab+"and have to take "+count+"Times per day "; 
	        
	       // t1.setPitch((float) 0.6);
	        et1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
			
			//to here
			result.append(" Tablet Name "+name).append(" have to take ").append(bf).append(" Total number of tablet you have "+tot_tab).append("and have to take "+count+"Times per day ");
			Toast.makeText(add_tablet.this,result,Toast.LENGTH_LONG).show();
			getprferences();
			getprferences1();
			}
			});
	
	}

	private void getprferences() {
		// TODO Auto-generated method stub


				
				RegisterAdapter sdba = new RegisterAdapter(add_tablet.this);
				sdba.open();
				long value = sdba.insert(name,bf,mor,aft,nig,tot_tab,amt,count1);
				sdba.close();
				if (value != 0) {

					System.out.println("Added Successfully...!");

				} else

				{
					System.out.println("Added Failed...!");

				}


	}
	private void getprferences1() {
		// TODO Auto-generated method stub


				
				RegisterAdapter sdba = new RegisterAdapter(add_tablet.this);
				sdba.open();
				long value = sdba.lp_insert(name,"1",tot_tab);
				sdba.close();
				if (value != 0) {

					System.out.println("Added Successfully...!");

				} else

				{
					System.out.println("Added Failed...!");

				}


	}

	public void onPause(){
	      if(et1 !=null){
	         et1.stop();
	         et1.shutdown();
	      }
	      super.onPause();
	   }
}
