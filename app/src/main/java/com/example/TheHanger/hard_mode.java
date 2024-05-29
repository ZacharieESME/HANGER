package com.example.TheHanger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.helloworld.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class hard_mode extends AppCompatActivity {

    private BroadcastReceiver soundEffectsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.TheHanger.ACTION_SOUND_EFFECTS_CHANGED".equals(intent.getAction())) {
                boolean soundEffectsEnabled = intent.getBooleanExtra("sound_effects_enabled", true);
                setSoundEffectsEnabled((ViewGroup) findViewById(android.R.id.content), soundEffectsEnabled);
            }
        }
    }; //Pour les sound effect on a besoin d'un receiver

    //private Set<Character> guessedLetters = new HashSet<>();
    public int guessedLetters = 0;

    private EditText displayText;
    public String randomWord;

    public List<String> letters = new ArrayList<>();

    private int wrongGuesses = 0; // Counter for wrong guesses

    // Array of drawable resources for hangman images
    private int[] hangmanImages = {
            R.drawable.hangmanpart0,
            R.drawable.hangmanpart1,
            R.drawable.hangmanpart2,
            R.drawable.hangmanpart3,
            R.drawable.hangmanpart4,
            R.drawable.hangmanpart5,
            R.drawable.hangmanpart6,
            R.drawable.hangmanpart7,
            R.drawable.hangmanpart8,
            R.drawable.hangmanpart9,
            R.drawable.hangmanpart10,
            R.drawable.hangmanpart11
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hard);

        // Trouver le bouton avec l'ID "button"
        ImageButton backButton = findViewById(R.id.backbutton3);

        // Ajouter un écouteur de clics au bouton
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour démarrer une nouvelle activité
                Intent intent = new Intent(hard_mode.this, select_difficulty.class);
                startActivity(intent);
            }
        });

        // Trouver le bouton avec l'ID "button"
        ImageButton settingButton = findViewById(R.id.settings3);

        // Ajouter un écouteur de clics au bouton
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour démarrer une nouvelle activité
                Intent intent = new Intent(hard_mode.this, Settings.class);
                startActivity(intent);
            }
        });





        // Initialize the EditText
        displayText = findViewById(R.id.displayText);
        randomWord = getRandomWordFromDatabase();

        // Display each letter of the word in separate TextViews
        displayWord(randomWord);

        // Register the receiver
        registerReceiver(soundEffectsReceiver, new IntentFilter("com.TheHanger.ACTION_SOUND_EFFECTS_CHANGED"));

        // Check the current preference and apply it
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean soundEffectsEnabled = sharedPreferences.getBoolean("sound_effects", true);
        setSoundEffectsEnabled((ViewGroup) findViewById(android.R.id.content), soundEffectsEnabled);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the receiver
        unregisterReceiver(soundEffectsReceiver);
    }

    private void setSoundEffectsEnabled(ViewGroup viewGroup, boolean enabled) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup) {
                setSoundEffectsEnabled((ViewGroup) view, enabled);
            } else {
                view.setSoundEffectsEnabled(enabled);
            }
        }
        getSupportActionBar().setTitle("The Hanger");
        getSupportActionBar().hide();

    }

    // Method to handle button clicks
    public void onKeyboardButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        // Append the text of the button to the existing text in the EditText
        displayText.append(buttonText);
    }

    // Method to handle delete button click
    public void onDeleteButtonClick(View view) {
        // Get the current text from the EditText
        String currentText = displayText.getText().toString();

        // Remove the last character from the text
        if (currentText.length() > 0) {
            String newText = currentText.substring(0, currentText.length() - 1);
            displayText.setText(newText);
        }
    }

    // Method to read a random word from the database file
    private String getRandomWordFromDatabase() {
        ArrayList<String> words = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("hard_mode_database");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Select a random word from the list
        if (!words.isEmpty()) {
            Random random = new Random();
            return words.get(random.nextInt(words.size()));
        } else {
            return null;
        }
    }

    private void displayWord(String word) {
        LinearLayout container = findViewById(R.id.container);
        container.removeAllViews(); // Clear previous views

        for (int i = 0; i < word.length(); i++) {
            LinearLayout letterLayout = new LinearLayout(this);
            letterLayout.setOrientation(LinearLayout.VERTICAL);

            TextView underscoreTextView = new TextView(this);
            underscoreTextView.setText(" _ ");
            underscoreTextView.setTextSize(30);
            underscoreTextView.setTextColor(Color.BLACK);

            TextView letterTextView = new TextView(this);
            letterTextView.setText(String.valueOf(word.charAt(i)));
            letterTextView.setTextSize(20);
            letterTextView.setTextColor(Color.WHITE);

            letterLayout.addView(underscoreTextView);
            letterLayout.addView(letterTextView);

            container.addView(letterLayout);
        }
    }
    // Method to handle guess button click
    public void onGuessClicked(View view) {
        EditText editText = findViewById(R.id.displayText);
        String guessedLetter = editText.getText().toString().toLowerCase();// Convert to uppercase for consistency
        System.out.println(editText);
        System.out.println(guessedLetter);


        LinearLayout container = findViewById(R.id.container);// Find the container view



        if (guessedLetter.isEmpty()) {
            // Provide feedback to the user (optional)
            Toast.makeText(this, "Please enter a letter", Toast.LENGTH_SHORT).show();
            return; // Exit the method if no input
        }




        // Check if the guessed letter is in the word
        if (randomWord.contains(guessedLetter) && !letters.contains(guessedLetter)) {

            letters.add(guessedLetter);
            // If the guessed letter is correct, change the color to black and remove the underscore
            for (int i = 0; i < randomWord.length(); i++) {
                if (randomWord.charAt(i) == guessedLetter.charAt(0)) {
                    LinearLayout letterLayout = (LinearLayout) container.getChildAt(i);
                    TextView underscoreTextView = (TextView) letterLayout.getChildAt(0);
                    TextView letterTextView = (TextView) letterLayout.getChildAt(1);

                    underscoreTextView.setVisibility(View.GONE);
                    letterTextView.setTextColor(Color.BLACK);

                    guessedLetters++;

                    if (guessedLetters == randomWord.length()){
                        guessedLetters = 0;
                        ImageView congratImage = findViewById(R.id.congrat);
                        congratImage.setVisibility(View.VISIBLE);
                        Button tryagain = findViewById(R.id.tryagain);
                        tryagain.setVisibility(View.VISIBLE);
                        Button menu = findViewById(R.id.menu);
                        menu.setVisibility(View.VISIBLE);

                        Button guessButton = findViewById(R.id.guess2);
                        guessButton.setEnabled(false);



                    }


                }

            }

        }
        else if(!letters.contains(guessedLetter)){

            // Update the wrong guesses TextView
            TextView wrongGuessesTextView = findViewById(R.id.wrongguesses);
            ImageView hangmanImage = findViewById(R.id.hangman);
            String currentText = wrongGuessesTextView.getText().toString();
            if (!currentText.split(":",2)[1].contains(guessedLetter)) {
                wrongGuessesTextView.setText(currentText + " " + guessedLetter);
                wrongGuesses++;
                if (wrongGuesses < hangmanImages.length - 1) {
                    hangmanImage.setImageResource(hangmanImages[wrongGuesses]);
                }
                else {
                    hangmanImage.setImageResource(hangmanImages[wrongGuesses]);
                    ImageView youloseImage = findViewById(R.id.youlose);
                    youloseImage.setVisibility(View.VISIBLE);
                    Button tryagain = findViewById(R.id.tryagain);
                    tryagain.setVisibility(View.VISIBLE);
                    Button menu = findViewById(R.id.menu);
                    menu.setVisibility(View.VISIBLE);

                    TextView word = findViewById(R.id.word);
                    word.setText("The word was: " + randomWord);

                    Button guessButton = findViewById(R.id.guess2);
                    guessButton.setEnabled(false);

                }
            }
        }


        // Clear the EditText after guessing
        editText.getText().clear();
    }

    public void restartPage(View view) {
        // Reset hangman image to hangmanpart0
        ImageView hangmanImage = findViewById(R.id.hangman);
        hangmanImage.setImageResource(R.drawable.hangmanpart0);

        // Choose a new word
        randomWord = getRandomWordFromDatabase();

        // Reset wrong guesses
        TextView wrongGuessesTextView = findViewById(R.id.wrongguesses);
        wrongGuessesTextView.setText("Wrong guesses:");
        wrongGuesses = 0;

        // Clear the EditText
        EditText editText = findViewById(R.id.displayText);
        editText.getText().clear();


        // Make the you lose pop up invisible
        ImageView youloseImage = findViewById(R.id.youlose);
        youloseImage.setVisibility(View.INVISIBLE);
        ImageView congratImage = findViewById(R.id.congrat);
        congratImage.setVisibility(View.INVISIBLE);
        Button tryagain = findViewById(R.id.tryagain);
        tryagain.setVisibility(View.INVISIBLE);
        Button menu = findViewById(R.id.menu);
        menu.setVisibility(View.INVISIBLE);

        // Reset the word display (replace "container" with your layout ID)
        LinearLayout container = findViewById(R.id.container);
        displayWord(randomWord); // Display the new word

        TextView word = findViewById(R.id.word);
        word.setText("");

        letters.clear();

        Button guessButton = findViewById(R.id.guess2);
        guessButton.setEnabled(true);

    }

}









