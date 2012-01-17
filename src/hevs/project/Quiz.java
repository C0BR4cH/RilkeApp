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
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Quiz extends Activity
{
	private Intent quizResult;
	private TextView question;
	private TextView answerA;
	private TextView answerB;
	private TextView answerC;
	private TextView correctNum;
	private Button btnA;
	private Button btnB;
	private Button btnC;
	private Button btnHome;
	private int correct;
	private int currentSet;
	private int[] answers;
	private String[] questions;
	private String[] answersA;
	private String[] answersB;
	private String[] answersC;
	private Resources res;
	private OnClickListener btnListener;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        
        // Init current set and correct answers
        currentSet=-1;
        correct=0;
        
        // Init TextViews
        question=(TextView)findViewById(R.id.txtQuestion);
        answerA=(TextView)findViewById(R.id.txtAnswerA);
        answerB=(TextView)findViewById(R.id.txtAnswerB);
        answerC=(TextView)findViewById(R.id.txtAnswerC);
        correctNum=(TextView)findViewById(R.id.txtCorrectNum);
        
        // Init string resources
        res=getResources();
        questions=res.getStringArray(R.array.quiz_questions);
        answersA=res.getStringArray(R.array.quiz_answersA);
        answersB=res.getStringArray(R.array.quiz_answersB);
        answersC=res.getStringArray(R.array.quiz_answersC);
        answers=res.getIntArray(R.array.quiz_answers);
        
        // Init Listener, Buttons and add Listener to Buttons
        btnListener=new ButtonListener();
        btnA=(Button)findViewById(R.id.btnA);
        btnB=(Button)findViewById(R.id.btnB);
        btnC=(Button)findViewById(R.id.btnC);
        btnHome=(Button)findViewById(R.id.btnHome);
        btnA.setOnClickListener(btnListener);
        btnB.setOnClickListener(btnListener);
        btnC.setOnClickListener(btnListener);
        btnHome.setOnClickListener(btnListener);
        
        // Set first questions/answer set
        nextQuizSet();
    }
    
    private void nextQuizSet()
    {
    	currentSet++;
    	if(currentSet==11)
    	{
    		// Save Result to preferences
    		preferences=getSharedPreferences("Prefs",MODE_PRIVATE);
    		editor=preferences.edit();
    		editor.putString("CorrectNum",correctNum.getText().toString());
    		editor.commit();
    		
    		// Init Result Activity
    		quizResult=new Intent(Quiz.this,QuizResult.class);
			Quiz.this.startActivity(quizResult);
			
			// Finish Quiz Activity
    		finish();
    		return;
    	}
    	question.setText(questions[currentSet]);
    	answerA.setText("A) "+answersA[currentSet]);
    	answerB.setText("B) "+answersB[currentSet]);
    	answerC.setText("C) "+answersC[currentSet]);
    }
    
    private void refCorrect()
    {
    	correct++;
    	correctNum.setText(String.valueOf(correct));
    }
    
    private class ButtonListener implements OnClickListener
    {
	    public void onClick(View v)
	    {
	    	if(v==findViewById(R.id.btnA))
	    	{
	    		if(answers[currentSet]==1)
		    		refCorrect();
	    		nextQuizSet();
	    	}
	    	if(v==findViewById(R.id.btnB))
	    	{
	    		if(answers[currentSet]==2)
	    			refCorrect();
	    		nextQuizSet();
	    	}
	    	if(v==findViewById(R.id.btnC))
	    	{
	    		if(answers[currentSet]==3)
	    			refCorrect();
	    		nextQuizSet();
	    	}
	    	if(v==findViewById(R.id.btnHome))
	    		finish();
	    }    
	}
}