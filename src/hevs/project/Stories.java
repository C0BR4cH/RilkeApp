package hevs.project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Stories extends Activity{
	
	TextView title;
	TextView storyTitle;
	TextView storyTxt;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stories);
        title = (TextView)findViewById(R.id.story_Title);
        storyTitle = (TextView)findViewById(R.id.story_StoryTitle);
        storyTxt = (TextView)findViewById(R.id.story_StoryText);
        
        title.setText(R.string.poetry_Title);
    }
}
