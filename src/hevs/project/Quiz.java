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
import android.content.res.Resources;
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
	private int[] answers;
	private String[] questions;
	private String[] answersA;
	private String[] answersB;
	private String[] answersC;
	private Resources res;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        res=getResources();
        currentSet=0;
        
        questions=res.getStringArray(R.array.quiz_questions);
        answersA=res.getStringArray(R.array.quiz_answersA);
        answersB=res.getStringArray(R.array.quiz_answersB);
        answersC=res.getStringArray(R.array.quiz_answersC);
        answers=res.getIntArray(R.array.quiz_answers);
    }
    
    private void nextQuizSet()
    {
    	question.setText(questions[currentSet]);
    	answerA.setText(answersA[currentSet]);
    	answerB.setText(answersB[currentSet]);
    	answerC.setText(answersC[currentSet]);
    	currentSet++;
    }
    
    public class ButtonListener implements OnClickListener
    {
	    public void onClick(View v)
	    {
	    	if(v==findViewById(R.id.btnA))
	    	{
	    		if(answers[currentSet]==1)
	    			correct++;
	    		nextQuizSet();
	    	}
	    	if(v==findViewById(R.id.btnB))
	    	{
	    		if(answers[currentSet]==2)
	    			correct++;
	    		nextQuizSet();
	    	}
	    	if(v==findViewById(R.id.btnC))
	    	{
	    		if(answers[currentSet]==3)
	    			correct++;
	    		nextQuizSet();
	    	}
	    }    
	}
}