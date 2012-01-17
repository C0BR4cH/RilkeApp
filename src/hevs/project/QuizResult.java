/* RilkeApp
 * 
 * 
 * QuizResult.java
 * 
 * Matthias Hurni
 * Created: 17.01.2012 14:25:02
 * Edit: 
 */
package hevs.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class QuizResult extends Activity
{
	private Intent intent;
	private Button btnRHome;
	private Button btnAgain;
	private TextView rCorrectNum;
	private OnClickListener btnListener;

	public QuizResult(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_result);
		
		// Init TextViews
		rCorrectNum=(TextView)findViewById(R.id.txtRCorrectNum);
		rCorrectNum.setText("blubb");
		
		// Init Listener, Buttons and add Listener to Buttons
		btnListener=new ButtonListener();
		btnRHome=(Button)findViewById(R.id.btnRHome);
		btnAgain=(Button)findViewById(R.id.btnAgain);
		btnRHome.setOnClickListener(btnListener);
		btnAgain.setOnClickListener(btnListener);
	}

	private class ButtonListener implements OnClickListener
	{
		public void onClick(View v)
		{
			if(v==findViewById(R.id.btnHome))
				finish();
			if(v==findViewById(R.id.btnAgain))
			{
				intent = getIntent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				overridePendingTransition(0, 0);
				finish();

				overridePendingTransition(0, 0);
				startActivity(intent);
			}
		}  
	}
}
