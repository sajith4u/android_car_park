package park.innova.dev.carpark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StarterScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starter_screen);
        ImageView login = (ImageView) findViewById(R.id.loginPageIcon);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StarterScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
