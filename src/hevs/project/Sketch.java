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
import android.content.Intent;
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
	
	private void startActivity(int i)
	{
		Bundle bundle;
		Intent intent;
		
		bundle=new Bundle();
		bundle.putInt("room",0);
		intent=new Intent(Sketch.this,SketchAgenda.class);
		intent.putExtras(bundle);
		Sketch.this.startActivity(intent);
		finish();
	}
	
	private class ImageListener implements OnTouchListener
	{
		public boolean onTouch(View v,MotionEvent event)
		{
			int action=event.getActionMasked();
			int x=(int)event.getX();
			int y=(int)event.getY();
			int col;
			
			if(action==MotionEvent.ACTION_DOWN)
			{
				col=getColor(x,y);
				switch(col)
				{
					case -524544:
						startActivity(0);
						return true;
					case -16711689:
						startActivity(1);
						return true;
					case -16711936:
						startActivity(2);
						return true;
					case -65536:
						startActivity(3);
						return true;
					case -14089985:
						startActivity(4);
						return true;
					case -65281:
						startActivity(5);
						return true;
					case -30208:
						startActivity(6);
						return true;
					case -48896:
						startActivity(7);
						return true;
					case -15168000:
						startActivity(8);
						return true;
					case -7077740:
						startActivity(9);
						return true;
					case -16711813:
						startActivity(10);
						return true;
					case -65437:
						startActivity(11);
						return true;
					case -4867584:
						startActivity(12);
						return true;
					default:
						return false;
				}
			}
			return false;
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