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
	private TextView question;
	private TextView answerA;
	private TextView answerB;
	private TextView answerC;
	private Button btnA;
	private Button btnB;
	private Button btnC;
	private int answer;
	private int correct;
	private int currentSet;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
    }
    
    private void nextQuizSet()
    {
    	question.setText("blubb");
    }
    
    public class ButtonListener implements OnClickListener
    {
	    public void onClick(View v)
	    {
	    	if(v==findViewById(R.id.btnA))
	    	{
	    		
	    	}
	    	if(v==findViewById(R.id.btnB))
	    	{
	    		
	    	}
	    	if(v==findViewById(R.id.btnC))
	    	{
	    		
	    	}
	    }    
	}
}