package hevs.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity
{
    private Button btnQuiz;
    private Button btnNews;
    private Button btnStories;
    private Button btnPoetry;
    public ButtonListener btnListener;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        
        //add references to each button
        btnQuiz = (Button)findViewById(R.id.btnQuiz);
        btnNews = (Button)findViewById(R.id.btnNews);
        btnStories = (Button)findViewById(R.id.btnStories);
        btnPoetry = (Button)findViewById(R.id.btnPeotry);
        
        //create and add listener
        btnListener = new ButtonListener();
        btnQuiz.setOnClickListener(btnListener);
        btnNews.setOnClickListener(btnListener);
        btnStories.setOnClickListener(btnListener);
        btnPoetry.setOnClickListener(btnListener);
    }
    
    
    public class ButtonListener implements OnClickListener{

		@Override
	    public void onClick(View v)
	    {
	    	if(v==findViewById(R.id.btnQuiz))
	    	{
				Intent settings = new Intent(MainMenu.this,Quiz.class);
				MainMenu.this.startActivity(settings);
	    	}
	    	if(v==findViewById(R.id.btnNews))
	    	{
				Intent news = new Intent(MainMenu.this,Quiz.class);
				MainMenu.this.startActivity(news);
	    	}
	    	if(v==findViewById(R.id.btnStories))
	    	{
				Intent stories = new Intent(MainMenu.this,Stories.class);
				MainMenu.this.startActivity(stories);
	    	}
	    	if(v==findViewById(R.id.btnPeotry))
	    	{
				Intent poetry = new Intent(MainMenu.this,Stories.class);
				MainMenu.this.startActivity(poetry);
	    	}
	    }
    }
}