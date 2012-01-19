/* RilkeApp
 * 
 * 
 * SketchAgenda.java
 * 
 * Matthias Hurni
 * Created: 19.01.2012 13:17:10
 * Edit: 
 */
package hevs.project;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SketchAgenda extends Activity
{
	private TextView subTitle;
	private TextView text;
	private Button btnHome;
	private Button btnBack;
	private OnClickListener btnListener;
	private Resources res;
	private Bundle bundle;
	private Intent sketch;
	private int room;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sketch_agenda);
		
		// Get parameter
		bundle=this.getIntent().getExtras();
		room=bundle.getInt("room");
		
		// Get Resources
		res=getResources();
		
		// Init TextViews and set text
		subTitle=(TextView)findViewById(R.id.sketch_SubTitle);
		text=(TextView)findViewById(R.id.sketch_Text);
		subTitle.setText(res.getStringArray(R.array.sketch_subtitle)[room]);
		text.setText(res.getStringArray(R.array.sketch_text)[room]);
		
		// Init Listener, Buttons and add Listener to Buttons
        btnListener=new ButtonListener();
        btnHome=(Button)findViewById(R.id.btnHome);
        btnBack=(Button)findViewById(R.id.btnBack);
        btnHome.setOnClickListener(btnListener);
        btnBack.setOnClickListener(btnListener);
	}
	
	private class ButtonListener implements OnClickListener
	{
		public void onClick(View v)
		{
			if(v==btnHome)
				finish();
			if(v==btnBack)
			{
				sketch = new Intent(SketchAgenda.this,Sketch.class);
				SketchAgenda.this.startActivity(sketch);
				finish();
			}
		}
	}
}
