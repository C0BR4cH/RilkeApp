package hevs.project;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;


public class Video extends Activity{

	String SrcPath = "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp";
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	       setContentView(R.layout.video);
	       VideoView myVideoView = (VideoView)findViewById(R.id.myvideoview);
	       myVideoView.setVideoURI(Uri.parse(SrcPath));
	       myVideoView.setMediaController(new MediaController(this));
	       myVideoView.requestFocus();
	       myVideoView.start();
	}
}
