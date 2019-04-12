package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.harrowedtale.dungeonsanddragonscompanionapp.wizard_fragments.WizardFragment;

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
                        Toast.makeText(NewUserWizard.this, "Setup Done!", Toast.LENGTH_SHORT).show();
                        Intent mainMenu = new Intent(NewUserWizard.this, MainActivity.class);
                        startActivity(mainMenu);
                    }
                    else {
                        NewCharacterSingleton newCharacter = NewCharacterSingleton.getInstance();
                        Toast.makeText(NewUserWizard.this, Integer.toString(newCharacter.getCharisma()), Toast.LENGTH_SHORT).show();
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
            else if(position == 2){
                nextButton.setText("Finish");
            }
            else{
                backButton.setVisibility(View.VISIBLE);
                nextButton.setText("Next");
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
}
