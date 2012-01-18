package hevs.project;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class News extends ListActivity {

	private ArrayList<String> item = new ArrayList<String>();
	private ArrayList<String> cont = new ArrayList<String>();
	private ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			URL rssUrl = new URL("http://wordpress.pre-view.ch/feed/");
			SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();
			SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
			XMLReader myXMLReader = mySAXParser.getXMLReader();
			RSSHandler myRSSHandler = new RSSHandler();
			myXMLReader.setContentHandler(myRSSHandler);
			InputSource myInputSource = new InputSource(rssUrl.openStream());
			myXMLReader.parse(myInputSource);

			setListAdapter(new ArrayAdapter<String>(this, R.layout.news, item));

			lv = getListView();
			lv.setTextFilterEnabled(true);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//when clicked open new activity for a single post
				Bundle bundle = new Bundle();
				Intent news_post = new Intent(News.this,NewsPost.class);
				bundle.putStringArrayList("title", item);
				bundle.putStringArrayList("content", cont);	
				bundle.putInt("position", position);
				news_post.putExtras(bundle);
				News.this.startActivity(news_post);
				//close news activity
				finish();
			}
		});
	}

	private class RSSHandler extends DefaultHandler
	{
		final int stateUnknown = 0;
		final int stateTitle = 1;
		final int stateContent = 2;
		int state = stateUnknown;

		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
		}

		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// change state for each xml node
			if (localName.equalsIgnoreCase("title"))
			{
				state = stateTitle;
			}
			else 
			if(localName.equalsIgnoreCase("description"))
			{
				state = stateContent;
			}else
				state = stateUnknown;
		}

		@Override
		public void endElement(String uri, String localName, String qName)
		throws SAXException {
			//when finished set state to unknown
			state = stateUnknown;
		}

		@Override
		public void characters(char[] ch, int start, int length)
		throws SAXException {
			// save xml text into a string
			String strCharacters = new String(ch, start, length);
			// title in title array
			if (state == stateTitle)
			{
				item.add(strCharacters);
			}
			// description in content array
			if (state == stateContent)
			{
				cont.add(strCharacters);
			}
		}
	}
}