package hevs.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainMenu extends Activity
{
	private String[] buttons;
	private Resources res;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);

		//fill array with all titles
		res = getResources();
		buttons = res.getStringArray(R.array.menu_titles);

		//fill gridview with buttons
		GridView gridview = (GridView) findViewById(R.id.GridView_Buttons);
		gridview.setAdapter(new ButtonAdapter(this)); 
	}
	
	//check if user has internet connection
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
	
	//Display a message
	public void MessageBox(String message){
	    Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
	}

	public class ButtonAdapter extends BaseAdapter {  
		private Context mContext;  

		// Gets the context so it can be used later  
		public ButtonAdapter(Context c) {  
			mContext = c;  
		}  

		// Total number of things contained within the adapter  
		public int getCount() {  
			return buttons.length;  
		}  

		// Require for structure, not really used in my code.  
		public Object getItem(int position) {  
			return null;  
		}  

		// Require for structure, not really used in my code. Can  
		// be used to get the id of an item in the adapter for  
		// manual control.  
		public long getItemId(int position) {  
			return position;  
		}  

		public View getView(int position, View convertView, ViewGroup parent) {  
			Button btn;  
			if (convertView == null) {  
				// if it's not recycled, initialize some attributes  
				btn = new Button(mContext);  
				btn.setLayoutParams(new GridView.LayoutParams(100, 55));  
				btn.setPadding(6, 6, 6, 6);  
			}  
			else {  
				btn = (Button) convertView;  
			}   
			btn.setText(buttons[position]);  
			// filenames is an array of strings  
			btn.setTextColor(Color.WHITE);  
			btn.setBackgroundResource(R.drawable.button); 
			btn.setId(position);
			// Set the onclicklistener so that pressing the button fires an event  
			// We will need to implement this onclicklistner.  
			btn.setOnClickListener(new ButtonListener(position)); 
			return btn;  
		} 
	}  
	class ButtonListener implements OnClickListener  
	{  
		private final int position;

		public ButtonListener(int position)  
		{  
			this.position = position;  
		}  

		public void onClick(View v)  
		{  
			if(position==0)
			{
				if(isOnline()){
					Intent news=new Intent(MainMenu.this,News.class);
					MainMenu.this.startActivity(news);
				}else
				{
					MessageBox("No Internet avaiable!");
				}
			}
			if(position==1)
			{
				Intent gallery=new Intent(MainMenu.this,Gallery.class);
				MainMenu.this.startActivity(gallery);
			}
			if(position==2)
			{
				Bundle bundle = new Bundle();
				bundle.putString("param1", "stories");
				Intent stories = new Intent(MainMenu.this,StoriesPoetry.class);
				stories.putExtras(bundle);
				MainMenu.this.startActivity(stories);
			}
			if(position==3)
			{
				Intent video=new Intent(MainMenu.this,Video.class);
				MainMenu.this.startActivity(video);
			}
			if(position==4)
			{
				Bundle bundle = new Bundle();
				bundle.putString("param1", "poetry");
				Intent poetry = new Intent(MainMenu.this,StoriesPoetry.class);
				poetry.putExtras(bundle);
				MainMenu.this.startActivity(poetry);
			}
			if(position==5)
			{
				Intent sketch=new Intent(MainMenu.this,Sketch.class);
				MainMenu.this.startActivity(sketch);
			}
			if(position==6)
			{
				Intent quiz = new Intent(MainMenu.this,Quiz.class);
				MainMenu.this.startActivity(quiz);
			}
			if(position==7)
			{
				Intent route=new Intent(MainMenu.this,Route.class);
				MainMenu.this.startActivity(route);
			}
		}  
	} 
}
