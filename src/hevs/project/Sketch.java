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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Sketch extends Activity
{
	private Button btnHome;
	private OnClickListener btnListener;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sketch);
		
		 // Init Listener, Buttons and add Listener to Buttons
		btnListener=new ButtonListener();
		btnHome=(Button)findViewById(R.id.btnHome);
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