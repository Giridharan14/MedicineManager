package com.giridharan.medicine_manager;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class c_add extends ActionBarActivity {
	TextToSpeech et1;
	EditText t1;
	Button b1,b2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c_add);
		t1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			
			public void onClick(View v) {
			String	name= t1.getText().toString();
			RegisterAdapter sdba = new RegisterAdapter(c_add.this);
			sdba.open();
			long value = sdba.cn_insert(name);
			sdba.close();
			if (value != 0) {

				System.out.println("Added Successfully...!");

			} else

			{
				System.out.println("Added Failed...!");

			}


			}
		});
	}

}
