package hevs.project;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class Gallery extends Activity{

	public ImageView image;
	public Integer[] imageIds=null;
	public GalleryListener gallerylistener;
	public ButtonListener buttonListener;
	public Button btnHome;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);

		//create variables and set references
		image = (ImageView) findViewById(R.id.test_image);
		imageIds = new Integer[]{R.drawable.rilke_background, R.drawable.rilke_bg};
		gallerylistener = new GalleryListener();
		buttonListener = new ButtonListener();
		btnHome = (Button) findViewById(R.id.gallery_btnHome);
		
		android.widget.Gallery gallery = (android.widget.Gallery) findViewById(R.id.galley_gall);
		gallery.setAdapter(new ImageAdapter(this));

		//set first picture into imageView
		setPic(0);

		//add listener
		gallery.setOnItemClickListener(gallerylistener);
		btnHome.setOnClickListener(buttonListener);
	}

	//set new picture in imageView
	public void setPic(int position){
		image.setImageResource(imageIds[position]);
	}

	//personal listener for the gallery
	public class GalleryListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			setPic(position);
		}
	}

	//listener for the home button
	public class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v)
		{
			if(v==findViewById(R.id.gallery_btnHome)){
				finish();
			}
		}
	}

	//imageAdapter for the gallery
	public class ImageAdapter extends BaseAdapter {
		int mGalleryItemBackground;
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
			TypedArray attr = mContext.obtainStyledAttributes(R.styleable.gallery_gallery);
			mGalleryItemBackground = attr.getResourceId(
					R.styleable.gallery_gallery_android_galleryItemBackground, 0);
			attr.recycle();
		}

		public int getCount() {
			return imageIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(mContext);

			imageView.setImageResource(imageIds[position]);
			imageView.setLayoutParams(new android.widget.Gallery.LayoutParams(150, 100));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setBackgroundResource(mGalleryItemBackground);

			return imageView;
		}
	}
}
