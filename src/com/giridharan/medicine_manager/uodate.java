package com.giridharan.medicine_manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class uodate extends Activity {
Button b1,b2;
EditText et1,et2,et3,et4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uodate_delete);
		Bundle bundle = getIntent().getExtras();
		String message1 = bundle.getString("message1");
		String message2 = bundle.getString("message2");
		String message3 = bundle.getString("message3");
		String message4 = bundle.getString("message4");
		System.out.println(message1+" "+message2+" "+message3+" "+message4);

		et1=(EditText)findViewById(R.id.editText1);
		et2=(EditText)findViewById(R.id.editText2);
		et3=(EditText)findViewById(R.id.editText3);
		et4=(EditText)findViewById(R.id.editText4);
		et1.setText(message1);
		et2.setText(message2);
		et4.setText(message3);
		et3.setText(message4);
		
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RegisterAdapter sdba = new RegisterAdapter(uodate.this);
				sdba.open();
				long value = sdba.delete(et4.getText().toString());
				value =sdba.lp_delete(et4.getText().toString());
				sdba.close();

			}
			});
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RegisterAdapter sdba = new RegisterAdapter(uodate.this);
				sdba.open();
				long value = sdba.update(et4.getText().toString(),et1.getText().toString(),et3.getText().toString(),et4.getText().toString());
				value = sdba.lp_update(et4.getText().toString(),et1.getText().toString(),et3.getText().toString(),et4.getText().toString());
				sdba.close();

			}
			});

	
	}

}
