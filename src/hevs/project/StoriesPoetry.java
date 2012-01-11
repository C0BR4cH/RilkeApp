package hevs.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StoriesPoetry extends Activity{
	
	private TextView title;
	private TextView storyTitle;
	private TextView storyTxt;
	private TextView authorTxt;
	private Button btnPrev;
	private Button btnHome;
	private Button btnNext;
	private OnClickListener btnListener;
	private Bundle bundle;
	private String parameter;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stories_poetry);
        
        //save parameter
        bundle = this.getIntent().getExtras();
        parameter = bundle.getString("param1");
        
        //add references to each item
        title = (TextView)findViewById(R.id.story_Title);
        storyTitle = (TextView)findViewById(R.id.story_StoryTitle);
        storyTxt = (TextView)findViewById(R.id.story_StoryText);
        authorTxt = (TextView)findViewById(R.id.story_author);
        btnPrev=(Button)findViewById(R.id.story_btnPrev);
        btnHome=(Button)findViewById(R.id.story_btnHome);
        btnNext=(Button)findViewById(R.id.story_btnNext);
        
        //create listener and check which menu was called
        if(parameter.equals("poetry")){
        	title.setText(R.string.poetry_Title);
        	storyTitle.setText(R.string.poetry_Poetry1);
        	storyTxt.setText(R.string.poetry_Text1);
        	authorTxt.setText(R.string.poetry_author1);
        	btnListener = new ButtonListenerPoetry();
        }else
        	btnListener = new ButtonListenerStories();
        
        //add listener
        btnPrev.setOnClickListener(btnListener);
        btnHome.setOnClickListener(btnListener);
        btnNext.setOnClickListener(btnListener);
    }
	//ButtonListener for poetry
    public class ButtonListenerPoetry implements OnClickListener{

		@Override
	    public void onClick(View v)
	    {
	    	if(v==findViewById(R.id.story_btnPrev))
	    	{
	    	}
	    	if(v==findViewById(R.id.story_btnHome))
	    	{
	    		finish();
	    	}
	    	if(v==findViewById(R.id.story_btnNext))
	    	{
	    	}
	    }
    }
    //ButtonListener for stories
    public class ButtonListenerStories implements OnClickListener{

		@Override
	    public void onClick(View v)
	    {
	    	if(v==findViewById(R.id.story_btnPrev))
	    	{
	    	}
	    	if(v==findViewById(R.id.story_btnHome))
	    	{
	    		finish();
	    	}
	    	if(v==findViewById(R.id.story_btnNext))
	    	{
	    	}
	    }
    }
}
