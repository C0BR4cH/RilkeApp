package hevs.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }
    
    public void onClick(View v)
    {
    	if(v==findViewById(R.id.btnQuizz))
    	{
			Intent settings = new Intent(MainMenu.this,Quiz.class);
			MainMenu.this.startActivity(settings);
    	}
    }
}