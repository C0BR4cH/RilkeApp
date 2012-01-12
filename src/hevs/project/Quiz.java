/* RilkeApp
 * 
 * 
 * Quiz.java
 * 
 * Matthias Hurni
 * Created: 11.01.2012 14:04:17
 * Edit: 
 */
package hevs.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Quiz extends Activity
{
	private TextView score;
	private TextView question;
	private TextView answerA;
	private TextView answerB;
	private TextView answerC;
	private Button btnOk;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
    }
    
    public class ButtonListener implements OnClickListener
    {
	    public void onClick(View v)
	    {
	    	if(v==findViewById(R.id.btnOk))
	    	{
	    		
	    	}
	    }    
	}
}