package hevs.project;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends Activity{

	private Button btnPrev;
	private Button btnHome;
	private Button btnNext;
	private VideoView myVideoView;
	private ButtonListener btnListener;
	private int counter = 0;
	private final String[] SrcPath = {"rtsp://v1.cache8.c.youtube.com/CjYLENy73wIaLQkmCIbhzkXGsBMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYMzQpcDlp52LTww=/0/0/0/video.3gp",
			"rtsp://v8.cache2.c.youtube.com/CjYLENy73wIaLQkyRz4qrMCItRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYMzQpcDlp52LTww=/0/0/0/video.3gp", 
	"rtsp://v8.cache3.c.youtube.com/CjYLENy73wIaLQkEAmvzAcok3RMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYMzQpcDlp52LTww=/0/0/0/video.3gp"};

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);

		//add reference to video view
		myVideoView = (VideoView)findViewById(R.id.myvideoview);

		//add reference to buttons
		btnPrev=(Button)findViewById(R.id.vid_btnPrev);
		btnHome=(Button)findViewById(R.id.vid_btnHome);
		btnNext=(Button)findViewById(R.id.vid_btnNext);

		//start video
		setVid(counter);

		//create and add listener
		btnListener = new ButtonListener();
		btnPrev.setOnClickListener(btnListener);
		btnHome.setOnClickListener(btnListener);
		btnNext.setOnClickListener(btnListener);
	}

	//set video url in the videoview and play it
	public void setVid(int position){
		myVideoView.setVideoURI(Uri.parse(SrcPath[position]));
		myVideoView.setMediaController(new MediaController(this));
		myVideoView.requestFocus();
		myVideoView.start();
	}

	//ButtonListener for buttons on bottom
	public class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v)
		{
			//show previous video
			if(v==findViewById(R.id.vid_btnPrev))
			{
				counter--;
				if(counter<0)
					counter=SrcPath.length-1;
				setVid(counter);
			}
			//go back to the menu
			if(v==findViewById(R.id.vid_btnHome))
			{
				finish();
			}
			//show next video
			if(v==findViewById(R.id.vid_btnNext))
			{
				counter++;
				if(counter>=SrcPath.length)
					counter=0;
				setVid(counter);
			}
		}
	}
}
