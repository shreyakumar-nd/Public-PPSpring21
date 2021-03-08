package lecture.lecture02_about_practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mSearchTermEditText;
    Button mSearchButton;
    Button mResetButton;
    TextView mSearchResultsDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO connect with UI elements from the xml

        mSearchTermEditText = (EditText) findViewById(R.id.et_search_box);
        mSearchButton       = (Button) findViewById(R.id.button_search);
        mResetButton        = (Button) findViewById(R.id.button_reset);
        mSearchResultsDisplay = (TextView) findViewById(R.id.tv_display_text);



        mSearchResultsDisplay.setText("Student names:\n\n\n");
        final String[] studentNames = {"Jacob", "Peter", "Rafa", "Gaurav", "Chase", "Molly", "Nolan"};
        for(String student: studentNames) {
            mSearchResultsDisplay.append("\n\n" + student);
        }

        final String defaultDisplayText = mSearchResultsDisplay.getText().toString();

        // responding to button press
        mSearchButton.setOnClickListener(
                new View.OnClickListener(){ // unnamed/anonymous object
                    @Override
                    public void onClick(View v) {
                        //TODO search button stuff
                        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ Search button clicked!!");
                        // get search term from UI
                        String searchText = mSearchTermEditText.getText().toString();
                        for (String name: studentNames){
                            if(name.toLowerCase().equals(searchText.toLowerCase())){
                                // search successful
                                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ Search successful!!");
                                mSearchResultsDisplay.setText(name);
                                break;
                            }else{
                                mSearchResultsDisplay.setText("No matching results.");
                            }
                        }
                        // compare with text view text

                    } // end of onClick method
                } // end of new object creation
        ); // end of setOnClickListener

        mResetButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        //TODO reset button stuff
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ Reset button clicked!!");
                        mSearchResultsDisplay.setText(defaultDisplayText);
                    } // end of onclick method
                } // end of object
        );

    } // end of onCreate

    // how to connect with menu
    // Steps: go to About page from menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu); // id of the menu resource that should be inflated
        return true;
    } // end of onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int menuItemSelected = item.getItemId();

        if(menuItemSelected == R.id.menu_contact){ // id from main_menu.xml for the About item


            //spl - launching activity in our app - then launch the About Activity
            Class destinationActivity = ContactActivity.class;

            // create intent to go to next page
            Intent startAboutActivityIntent = new Intent(MainActivity.this, destinationActivity);

            String msg = mSearchTermEditText.getText().toString();
            startAboutActivityIntent.putExtra(Intent.EXTRA_TEXT, msg);

            startActivity(startAboutActivityIntent);
            Log.d("info", "Contact Activity launched");
        } // end if
        return true;
    } // end of onOptions

} // end of class MainActivity

