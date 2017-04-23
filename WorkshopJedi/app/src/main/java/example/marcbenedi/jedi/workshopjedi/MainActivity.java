package example.marcbenedi.jedi.workshopjedi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private Button mButton;
    private Button cameraButton;
    private Button recyclerButton;
    private Button APIButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.title);
        mButton = (Button) findViewById(R.id.button);
        cameraButton = (Button) findViewById(R.id.button1);
        recyclerButton = (Button) findViewById(R.id.button2);
        APIButton = (Button) findViewById(R.id.apibutton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Benvingut!");
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });

        recyclerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),RecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        APIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),APIActivity.class);
                startActivity(intent);
            }
        });

    }
}
