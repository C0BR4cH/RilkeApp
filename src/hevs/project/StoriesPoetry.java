package hevs.project;

import android.app.Activity;
import android.content.res.Resources;
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
	private int counter;
	private Resources res;
	private String[] arrayTitle;
	private String[] arrayTxt;
	private String[] arrayAuthor;
	
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
        
        //set counter and get resources
        counter = 0;
        res = getResources();
        
        //check which menu was called
        if(parameter.equals("poetry")){
        	//fill correct array
        	arrayTitle = res.getStringArray(R.array.poetry_poetryTitle);
        	arrayTxt = res.getStringArray(R.array.poetry_Text);
        	arrayAuthor = res.getStringArray(R.array.poetry_author);
        	//set correct title
        	title.setText(R.string.poetry_Title);
        }else{
        	//fill correct array
        	arrayTitle = res.getStringArray(R.array.story_storyTitle);
        	arrayTxt = res.getStringArray(R.array.story_text);
        	arrayAuthor = res.getStringArray(R.array.story_author);
        }
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
		storyTitle.setText(arrayTitle[pos]);
    	storyTxt.setText(arrayTxt[pos]);
    	authorTxt.setText(arrayAuthor[pos]);
	}

    //ButtonListener for buttons on bottom
    public class ButtonListener implements OnClickListener{

		@Override
	    public void onClick(View v)
	    {
	    	//show previous story or poetry
			if(v==findViewById(R.id.story_btnPrev))
	    	{
	    		counter--;
	    		if(counter<0)
	    			counter=arrayTitle.length-1;
	    		setText(counter);
	    	}
			//go back to the menu
	    	if(v==findViewById(R.id.story_btnHome))
	    	{
	    		finish();
	    	}
	    	//show next story or poetry
	    	if(v==findViewById(R.id.story_btnNext))
	    	{
	    		counter++;
	    		if(counter>=arrayTitle.length)
	    			counter=0;
	    		setText(counter);
	    	}
	    }
    }
}
