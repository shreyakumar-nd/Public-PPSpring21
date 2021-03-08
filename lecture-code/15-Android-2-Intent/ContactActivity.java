
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ContactActivity extends AppCompatActivity {

    private TextView mDisplayAboutTextView;
    private Button mOpenWebpageButton;
    private Button mOpenMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // connect with UI elements
        mDisplayAboutTextView = (TextView) findViewById(R.id.tv_about_text);
        mOpenWebpageButton = (Button) findViewById(R.id.button_open_webpage);
        mOpenMapButton = (Button) findViewById(R.id.button_open_map);

        // grabbing the data that the originating intent sends us
        Intent intentThatStartedThisActivity = getIntent();
        String message = "news";
        //check is extra data
        if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            message = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mDisplayAboutTextView.append("\n\n\n" + message);
        } // end if

        final String urlString = "https://www.nd.edu/"; // url string

        // open webpage button
        mOpenWebpageButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        openWebPage(urlString);

                    } // end of onClick method

                } // end of View
        ); // end of setOnClickListener

        // open map button
        mOpenMapButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        openMap();

                    } // end of onClick
                } // end of View
        ); // end of setOnCli
    } // end of onCreate

    public void openMap(){
        String addressString = "University of Notre Dame, IN";
        Uri addressUri = Uri.parse("geo:0,0").buildUpon().appendQueryParameter("q", addressString).build();
        Intent openMapIntent = new Intent(Intent.ACTION_VIEW);
        openMapIntent.setData(addressUri);

        // check if it can be opened, and open it
        if(openMapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(openMapIntent);
        }
    } // end of open map

    public void openWebPage(String urlString){
        Uri webpage = Uri.parse(urlString);

        Intent openWebPageIntent = new Intent(Intent.ACTION_VIEW, webpage);
        // check if that intent can be launched, and then launch it
        if(openWebPageIntent.resolveActivity(getPackageManager()) != null){
            startActivity(openWebPageIntent);
        }
    } // end of open web page

} // end of ContactActivity class
