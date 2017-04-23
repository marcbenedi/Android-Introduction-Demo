package example.marcbenedi.jedi.workshopjedi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class APIActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.resultTextView);
        button = (Button) findViewById(R.id.buttonQuery);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editText.getText().toString();

                if (city.isEmpty()) Toast.makeText(v.getContext(),"PLS enter a city first",Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(v.getContext(),"Asking for "+city+" weather",Toast.LENGTH_SHORT).show();
                    ConnectToServer a = new ConnectToServer();
                    try {
                        String weather = a.execute("http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=fd6dc18deccfbefa220a58dd1b9271d4").get();
                        textView.setText(weather);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    public JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);

        urlConnection.setDoOutput(true);

        urlConnection.connect();

        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

        char[] buffer = new char[1024];

        String jsonString = new String();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }

    public class ConnectToServer extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            try {
                JSONObject apiResponse = getJSONObjectFromURL(url);
                JSONArray weatherArray = apiResponse.getJSONArray("weather");
                JSONObject weatherObject = (JSONObject) weatherArray.get(0);
                return weatherObject.getString("main");
            } catch (IOException e) {
                e.printStackTrace();
            }catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
