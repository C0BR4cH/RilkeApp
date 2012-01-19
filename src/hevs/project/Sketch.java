/* RilkeApp
 * 
 * 
 * Sketch.java
 * 
 * Matthias Hurni
 * Created: 17.01.2012 18:02:15
 * Edit: 
 */
package hevs.project;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class Sketch extends Activity
{
	private ImageView plan;
	private ImageView planCol;
	private Button btnHome;
	private OnClickListener btnListener;
	private OnTouchListener imgListener;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sketch);
		
		// Init Listener, Images and add Listener to Images
		imgListener=new ImageListener();
		plan=(ImageView)findViewById(R.id.imgPlan);
		planCol=(ImageView)findViewById(R.id.imgPlanCol);
		plan.setOnTouchListener(imgListener);
		planCol.setOnTouchListener(imgListener);
		
		 // Init Listener, Buttons and add Listener to Buttons
		btnListener=new ButtonListener();
		btnHome=(Button)findViewById(R.id.btnHome);
		btnHome.setOnClickListener(btnListener);
	}
	
	private int getColor(int x,int y)
	{
		Bitmap hotspots;	
	    planCol.setDrawingCacheEnabled(true); 
	    hotspots=Bitmap.createBitmap(planCol.getDrawingCache()); 
	    planCol.setDrawingCacheEnabled(false);
	    return hotspots.getPixel(x, y);
	}
	
	private class ImageListener implements OnTouchListener
	{
		public boolean onTouch(View v,MotionEvent event)
		{
			System.out.println(getColor((int)event.getX(),(int)event.getY()));
			return true;
		}
	}
	
	private class ButtonListener implements OnClickListener
    {
	    public void onClick(View v)
	    {
	    	if(v==btnHome)
	    		finish();
	    }
    }
}