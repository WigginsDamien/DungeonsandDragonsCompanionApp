package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    ImageButton d4, d6, d8, d10, d12, d20;//Variables for the imageButtons
    Button amountMinus, amountPlus, modMinus, modPlus;
    PopupWindow popupWindow;//Variable for popup window
    LayoutInflater layoutInflater;
    ConstraintLayout relativeLayout;
    Random rand = new Random();//Create random instance
    int rollModifier = 0, numOfDice = 1, totalRoll = 0;
    TextView dAmount, modAmount;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        dAmount = (TextView) findViewById(R.id.tvDAmountText);
        modAmount = (TextView) findViewById(R.id.tvModAmount);

        d4 = (ImageButton) findViewById(R.id.buttonD4);
        d6 = (ImageButton) findViewById(R.id.buttonD6);
        d8 = (ImageButton) findViewById(R.id.buttonD8);
        d10 = (ImageButton) findViewById(R.id.buttonD10);
        d12 = (ImageButton) findViewById(R.id.buttonD12);
        d20 = (ImageButton) findViewById(R.id.buttonD20);
        amountMinus = (Button) findViewById(R.id.bDAmountMinus);
        amountPlus = (Button) findViewById(R.id.bDAmountPlus);
        modMinus = (Button) findViewById(R.id.bModAmountMinus);
        modPlus = (Button) findViewById(R.id.bModAmountPlus);

        relativeLayout = (ConstraintLayout) findViewById(R.id.relative);

        //------------------------------Dice Amount Modifications------------------------------

        //Onclick for the dice amount minus. Subtract 1 from numOfDice and display it
        amountMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numOfDice >= 2)
                {
                    numOfDice = (numOfDice - 1);
                    dAmount.setText(String.valueOf(numOfDice) + "d");
                }
            }
        });

        //Onclick for the dice amount plus. Add 1 to numOfDice and display it
        amountPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numOfDice = (numOfDice + 1);
                dAmount.setText(String.valueOf(numOfDice) + "d");
            }
        });

        //------------------------------Dice Modifier Modifications------------------------------

        //Onclick for the modMinus. Subtract 1 from rollModifier and display it
        modMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollModifier = (rollModifier - 1);
                modAmount.setText(String.valueOf(rollModifier));
            }
        });

        //Onclick for the modPlus. Add 1 to rollModifier and display it
        modPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollModifier = (rollModifier + 1);
                if(rollModifier > 0)
                {
                    modAmount.setText("+" + String.valueOf(rollModifier));
                }
                else
                {
                    modAmount.setText(String.valueOf(rollModifier));
                }
            }
        });

        //-----------------------------Dice Randomization and Display-----------------------------

        //On click for the d4 dice
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loop through number of dice to get more random dice values
                for(int i = 0; i < numOfDice; i++)
                {
                    int randInt = rand.nextInt(4) + 1;//Create random int between 1 and 4
                    totalRoll = totalRoll + randInt;//Add the random dice roll to the total
                }
                totalRoll = totalRoll + rollModifier;//Add together the roll and the modifier

                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_window, null);

                popupWindow = new PopupWindow(container, RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT, true);

                //Set the text view in the popup window layout equal to the total roll
                ((TextView)popupWindow.getContentView().findViewById(R.id.tvTotal)).setText(String.valueOf(totalRoll));

                popupWindow.setElevation(20);//Set the elevation higher, so that the window has a shadow

                //Show the popup window
                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                //Dismiss the popup window when touched
                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
                totalRoll = 0;
            }
        });

        //On click for the d6 dice
        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loop through number of dice to get more random dice values
                for(int i = 0; i < numOfDice; i++)
                {
                    int randInt = rand.nextInt(6) + 1;//Create random int between 1 and 4
                    totalRoll = totalRoll + randInt;//Add the random dice roll to the total
                }
                totalRoll = totalRoll + rollModifier;//Add together the roll and the modifier

                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_window, null);

                popupWindow = new PopupWindow(container, RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT, true);

                //Set the text view in the popup window layout equal to the total roll
                ((TextView)popupWindow.getContentView().findViewById(R.id.tvTotal)).setText(String.valueOf(totalRoll));

                popupWindow.setElevation(20);//Set the elevation higher, so that the window has a shadow

                //Show the popup window
                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                //Dismiss the popup window when touched
                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
                totalRoll = 0;
            }
        });

        //On click for the d8 dice
        d8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loop through number of dice to get more random dice values
                for(int i = 0; i < numOfDice; i++)
                {
                    int randInt = rand.nextInt(8) + 1;//Create random int between 1 and 4
                    totalRoll = totalRoll + randInt;//Add the random dice roll to the total
                }
                totalRoll = totalRoll + rollModifier;//Add together the roll and the modifier

                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_window, null);

                popupWindow = new PopupWindow(container, RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT, true);

                //Set the text view in the popup window layout equal to the total roll
                ((TextView)popupWindow.getContentView().findViewById(R.id.tvTotal)).setText(String.valueOf(totalRoll));

                popupWindow.setElevation(20);//Set the elevation higher, so that the window has a shadow

                //Show the popup window
                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                //Dismiss the popup window when touched
                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
                totalRoll = 0;
            }
        });

        //On click for the d10 dice
        d10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loop through number of dice to get more random dice values
                for(int i = 0; i < numOfDice; i++)
                {
                    int randInt = rand.nextInt(10) + 1;//Create random int between 1 and 4
                    totalRoll = totalRoll + randInt;//Add the random dice roll to the total
                }
                totalRoll = totalRoll + rollModifier;//Add together the roll and the modifier

                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_window, null);

                popupWindow = new PopupWindow(container, RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT, true);

                //Set the text view in the popup window layout equal to the total roll
                ((TextView)popupWindow.getContentView().findViewById(R.id.tvTotal)).setText(String.valueOf(totalRoll));

                popupWindow.setElevation(20);//Set the elevation higher, so that the window has a shadow

                //Show the popup window
                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                //Dismiss the popup window when touched
                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
                totalRoll = 0;
            }
        });

        //On click for the d12 dice
        d12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loop through number of dice to get more random dice values
                for(int i = 0; i < numOfDice; i++)
                {
                    int randInt = rand.nextInt(12) + 1;//Create random int between 1 and 4
                    totalRoll = totalRoll + randInt;//Add the random dice roll to the total
                }
                totalRoll = totalRoll + rollModifier;//Add together the roll and the modifier

                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_window, null);

                popupWindow = new PopupWindow(container, RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT, true);

                //Set the text view in the popup window layout equal to the total roll
                ((TextView)popupWindow.getContentView().findViewById(R.id.tvTotal)).setText(String.valueOf(totalRoll));

                popupWindow.setElevation(20);//Set the elevation higher, so that the window has a shadow

                //Show the popup window
                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                //Dismiss the popup window when touched
                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
                totalRoll = 0;
            }
        });

        //On click for the d20 dice
        d20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loop through number of dice to get more random dice values
                for(int i = 0; i < numOfDice; i++)
                {
                    int randInt = rand.nextInt(20) + 1;//Create random int between 1 and 4
                    totalRoll = totalRoll + randInt;//Add the random dice roll to the total
                }
                totalRoll = totalRoll + rollModifier;//Add together the roll and the modifier

                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_window, null);

                popupWindow = new PopupWindow(container, RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT, true);

                //Set the text view in the popup window layout equal to the total roll
                ((TextView)popupWindow.getContentView().findViewById(R.id.tvTotal)).setText(String.valueOf(totalRoll));

                popupWindow.setElevation(20);//Set the elevation higher, so that the window has a shadow

                //Show the popup window
                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                //Dismiss the popup window when touched
                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
                totalRoll = 0;
            }
        });



    }


}
