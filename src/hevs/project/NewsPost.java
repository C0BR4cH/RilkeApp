package hevs.project;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NewsPost extends Activity{
	
	private TextView newsTitle;
	private TextView newsTxt;
	private Button btnPrev;
	private Button btnHome;
	private Button btnNext;
	private Bundle bundle;
	private OnClickListener btnListener;
	private ArrayList<String> title;
	private ArrayList<String> content;
	private int counter;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_post);

		//save parameter
		bundle = this.getIntent().getExtras();
		title = bundle.getStringArrayList("title");
		content = bundle.getStringArrayList("content");
		counter = bundle.getInt("position");

		//add references to each item
		newsTitle = (TextView)findViewById(R.id.news_PostTitle);
		newsTxt = (TextView)findViewById(R.id.news_NewsText);
		btnPrev=(Button)findViewById(R.id.news_btnPrev);
		btnHome=(Button)findViewById(R.id.news_btnHome);
		btnNext=(Button)findViewById(R.id.news_btnNext);

		//set correct text
		setText(counter);
		//create and add listener
		btnListener = new ButtonListener();
		btnPrev.setOnClickListener(btnListener);
		btnHome.setOnClickListener(btnListener);
		btnNext.setOnClickListener(btnListener);
	}

	//method sets new Text in layout
	private void setText(int pos){
		newsTitle.setText(title.get(pos));
		newsTxt.setText(content.get(pos));
	}

	//ButtonListener for buttons on bottom
	public class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v)
		{
			//show previous story or poetry
			if(v==findViewById(R.id.news_btnPrev))
			{
				counter--;
				if(counter<0)
					counter=title.size()-1;
				setText(counter);
			}
			//go back to the menu
			if(v==findViewById(R.id.news_btnHome))
			{
				finish();
			}
			//show next story or poetry
			if(v==findViewById(R.id.news_btnNext))
			{
				counter++;
				if(counter>=title.size())
					counter=0;
				setText(counter);
			}
		}
	}

}
