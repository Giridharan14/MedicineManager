package com.giridharan.medicine_manager;
import java.util.ArrayList;
import java.util.Locale;

import com.giridharan.medicine_manager.*;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;



public class view_tablet_list extends ActionBarActivity  {
	ListView list;
	int uu=0;
	String[] nameList,balance,times,tt;
    CustomAdapter adapter;
    public  view_tablet_list CustomListView = null;
    public  ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
    TextToSpeech t1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_tablet_list);
        CustomListView = this;
        getValuesFromDatabase(); 
        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();
         
        Resources res =getResources();
        list= ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )
         
        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
        list.setAdapter( adapter );
        //To set Tet to speech
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
	         @Override
	         public void onInit(int status) {
	            if(status != TextToSpeech.ERROR) {
	               t1.setLanguage(Locale.UK);
	            }
	         }
	      });
    }
 
    /****** Function to set data in ArrayList *************/
    public void setListData()
    {
         
        for (int i = 0; i < uu; i++) {
             
            final ListModel sched = new ListModel();
                 
              /******* Firstly take data in model object ******/
               sched.setCompanyName("Tablet Name : "+nameList[i]);
               sched.setImage(""+times[i]);
               sched.setUrl("balance "+balance[i]);
                
            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }
         
    }
    

   /*****************  This function used by adapter ****************/
    public int pp=-1,cc=0;
    public void onItemClick(int mPosition)
    {
    	if(mPosition == pp){
    		System.out.println("time 2");
    		Intent i = new Intent(view_tablet_list.this, uodate.class);
    	//here
    		
			i.putExtra("message1", nameList[pp]);
			i.putExtra("message2", balance[pp]);
			i.putExtra("message3", tt[pp]);
			i.putExtra("message4", mPosition);
			System.out.println(nameList[pp]+" "+balance[pp]+" "+tt[pp]+" "+mPosition);
			
    		//tohere
		startActivity(i);
		pp=-1;
		}
    	else if(mPosition != pp ){pp=mPosition;System.out.println("time 1");    	
        ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);
       // SHOW ALERT                  
        String toSpeak = "You have Selected the Tablet "+""+tempValues.getCompanyName() +". Balance Tablet number of tablet is "+tempValues.getUrl();
        Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
       // t1.setPitch((float) 0.6);
        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        Toast.makeText(CustomListView,
                ""+tempValues.getCompanyName()
                  +"Image:"+tempValues.getImage()
                  +"Url:"+tempValues.getUrl(),
                Toast.LENGTH_LONG)
        .show();
    	}
    }
    private void getValuesFromDatabase() {
		// TODO Auto-generated method stub

		RegisterAdapter sdba = new RegisterAdapter(view_tablet_list.this);

		sdba.open();
		Cursor cr = sdba.fetch();

		if (cr.getCount() > 0) {
			int i;
			nameList = new String[cr.getCount()];
			balance = new String[cr.getCount()];
			times =new String[cr.getCount()];
			tt=new String[cr.getCount()];
			for (i = 0; i < cr.getCount(); i++) {

				cr.moveToPosition(i);

				String userNameStr = cr.getString(cr.getColumnIndex(RegisterAdapter.UName));
				String Bal		= cr.getString(cr.getColumnIndex(RegisterAdapter.Tot));
				String M		= cr.getString(cr.getColumnIndex(RegisterAdapter.Mor));
				String Af		= cr.getString(cr.getColumnIndex(RegisterAdapter.Af));
				String N		= cr.getString(cr.getColumnIndex(RegisterAdapter.N));
				String c		= cr.getString(cr.getColumnIndex(RegisterAdapter.TT));
				nameList[i] = userNameStr;
				balance[i]=Bal;
				tt[i]=c;
				times[i] =""+M+"-"+Af+"-"+N; 
						}
uu=i;
		}

	}
    public void onPause(){
	      if(t1 !=null){
	         t1.stop();
	         t1.shutdown();
	      }
	      super.onPause();
	   }
}
