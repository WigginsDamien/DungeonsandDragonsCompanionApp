package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.harrowedtale.dungeonsanddragonscompanionapp.wizard_fragments.WizardFragment;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class NewUserWizard extends AppCompatActivity {
    ManualViewPager viewPager;
    NewUserWizardAdapter wizardAdapter;
    Button backButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_wizard);
        setTitle("New User Wizard");

        //creates viewPager here in java so we can set it up with the fragments
        viewPager = findViewById(R.id.view_pager_main);
        viewPager.setTouchEnabled(false);
        //create adapter
        wizardAdapter = new NewUserWizardAdapter(getSupportFragmentManager());
        viewPager.setAdapter(wizardAdapter);
        viewPager.addOnPageChangeListener(listener);

        backButton = findViewById(R.id.back_button);
        backButton.setVisibility(View.GONE); //back button should not exist until step 2
        nextButton = findViewById(R.id.next_button);

        //go back to previous fragment
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                viewPager.setCurrentItem(getItem(-1), true);
            }
        });

        //go to next fragment
        nextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(((WizardFragment)wizardAdapter.getItem(viewPager.getCurrentItem())).areFieldsValid()){
                    //go to next page
                    if(viewPager.getCurrentItem() == wizardAdapter.getCount()-1){
                        NewCharacterSingleton newCharacter = NewCharacterSingleton.getInstance();

                        CreateCharacter();

                        Toast.makeText(NewUserWizard.this, "Setup Complete!", Toast.LENGTH_SHORT).show();
                        Intent mainMenu = new Intent(NewUserWizard.this, MainActivity.class);
                        startActivity(mainMenu);

                    }
                    else {
                        NewCharacterSingleton newCharacter = NewCharacterSingleton.getInstance();
                        viewPager.setCurrentItem(getItem(+1), true);
                    }
                }
            }
        });
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
            //required to be here, but we don't need this at this time
        }

        @Override
        public void onPageSelected(int position){
            if(position == 0){
                backButton.setVisibility(View.GONE);
            }
            else if(position == 3){
                //if on last step, set button to finish text
                nextButton.setText(getText(R.string.finish_wiz));
            }
            else{
                //reveal back button if not on first page, revert from 'final' to 'next'
                backButton.setVisibility(View.VISIBLE);
                nextButton.setText(getText(R.string.next_wiz));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //required to be here, but we don't need this at this time
        }
    };

    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }

    private void writeToNewFile(String Name,String data) {
        try {
            //Context.MODE_PRIVATE creates a new file every time, this is what we want to do for the character creation wizard
            //for viewing/editing existing files we are going to need to use Context.MODE_APPEND
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput(Name, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    private void writeToFile(String Name,String data) {
        try {
            //for viewing/editing existing files we are going to need to use Context.MODE_APPEND
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput(Name, Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    public String jsonify(String input){
        String jsonString;
        jsonString=input+ "\n";
        return jsonString;
    }

    public void CreateCharacter(){
        NewCharacterSingleton newCharacter = NewCharacterSingleton.getInstance();
        int strBonus=0,dexBonus=0,conBonus=0,intBonus=0,wisBonus=0,chaBonus=0;
        writeToNewFile(newCharacter.getName(),jsonify(newCharacter.getName()));
        writeToFile(newCharacter.getName(),jsonify(newCharacter.getCharacterClass()));

        writeToFile(newCharacter.getName(),jsonify(newCharacter.getLevel()));
        writeToFile(newCharacter.getName(),jsonify(newCharacter.getHP()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getAlignment()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getRace()));
        if(newCharacter.getRace().equals("Dragonborn")){
            strBonus=2;
            chaBonus=1;
        }if(newCharacter.getRace().equals("Dwarf")){
            conBonus=2;
        }if(newCharacter.getRace().equals("Elf")){
            dexBonus=2;
        }if(newCharacter.getRace().equals("Gnome")){
            intBonus=2;
        }if(newCharacter.getRace().equals("Half-Elf")){
            chaBonus=2;
            dexBonus=1;
            wisBonus=1;
        }if(newCharacter.getRace().equals("Half-Orc")){
            strBonus=2;
            conBonus=1;
        }if(newCharacter.getRace().equals("Halfling")){
            dexBonus=2;
        }if(newCharacter.getRace().equals("Hobgoblin")){
            conBonus=2;
            intBonus=1;
        }if(newCharacter.getRace().equals("Human")){
            strBonus=1;
            dexBonus=1;
            conBonus=1;
            intBonus=1;
            wisBonus=1;
            chaBonus=1;
        }if(newCharacter.getRace().equals("Tiefling")){
            chaBonus=1;
        }

        writeToFile(newCharacter.getName() ,jsonify(String.valueOf(Integer.valueOf(newCharacter.getStrength())+strBonus)) );
        writeToFile(newCharacter.getName() ,jsonify(String.valueOf(Integer.valueOf(newCharacter.getDexterity())+dexBonus)) );
        writeToFile(newCharacter.getName() ,jsonify(String.valueOf(Integer.valueOf(newCharacter.getConstitution())+conBonus)) );
        writeToFile(newCharacter.getName() ,jsonify(String.valueOf(Integer.valueOf(newCharacter.getIntelligence())+intBonus)) );
        writeToFile(newCharacter.getName() ,jsonify(String.valueOf(Integer.valueOf(newCharacter.getWisdom())+wisBonus)) );
        writeToFile(newCharacter.getName() ,jsonify(String.valueOf(Integer.valueOf(newCharacter.getCharisma())+chaBonus)) );

        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getFirstProficiency()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getSecondProficiency()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getThirdProficiency()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getFourthProficiency()));
    }
}
