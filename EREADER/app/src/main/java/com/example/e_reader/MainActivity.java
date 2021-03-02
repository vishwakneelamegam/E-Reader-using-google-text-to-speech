package com.example.e_reader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech playData;
    Button listenData;
    TextView showData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listenData = findViewById(R.id.button);
        showData = findViewById(R.id.showData);
        showData.setText("A lion was sleeping in a forest. A mouse started playing on it. The lion was disturbed and arose from his sleep. It caught up the mouse angrily and tried to crush it to death. Then the mouse prayed the lion to leave him off and assured that it would help him when it needed. The lion laughed at it and let him off. One day the lion was caught in a net spread by a hunter. It roared and tried to escape but in vain. The mouse heared the lions roaring and came there. It started cutting the net with its teeth. The lion escaped and thanked the mouse. ");
        playData = new TextToSpeech(getApplicationContext(), status -> {
            if(status != TextToSpeech.ERROR) {
                playData.setLanguage(Locale.US);
            }
        });
        listenData.setOnClickListener(v -> playData.speak(getSelectedData(), TextToSpeech.QUEUE_FLUSH, null));
    }
    public void onPause(){
        if(playData !=null){
            playData.stop();
            playData.shutdown();
        }
        super.onPause();
    }
    public String  getSelectedData(){
        try {
            int startSelection=showData.getSelectionStart(),endSelection=showData.getSelectionEnd();
            return showData.getText().toString().substring(startSelection, endSelection).trim();
        }
        catch (Exception e){
            return "error";
        }
    }
}