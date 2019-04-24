package com.harrowedtale.dungeonsanddragonscompanionapp.wizard_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.harrowedtale.dungeonsanddragonscompanionapp.NewCharacterSingleton;
import com.harrowedtale.dungeonsanddragonscompanionapp.R;

public class fragment_wizard_3 extends WizardFragment {

    EditText strengthEditText;
    EditText dexterityEditText;
    EditText constitutionEditText;
    EditText intelligenceEditText;
    EditText wisdomEditText;
    EditText charismaEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_wizard_3, container, false);

        strengthEditText = rootView.findViewById(R.id.strength_editText);
        dexterityEditText = rootView.findViewById(R.id.dexterity_editText);
        constitutionEditText = rootView.findViewById(R.id.constitution_editText);
        intelligenceEditText = rootView.findViewById(R.id.intelligence_editText);
        wisdomEditText = rootView.findViewById(R.id.wisdom_editText);
        charismaEditText = rootView.findViewById(R.id.charisma_editText);

        //Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public boolean areFieldsValid() {
        boolean areValid = true;

        //error checking for all 6 stats (sorry, I know its a lot)
        if((strengthEditText.getText().toString().equals("")) || Integer.valueOf(strengthEditText.getText().toString()) > 20 || Integer.valueOf(strengthEditText.getText().toString()) < 1){
            strengthEditText.setError("Must be 1-20");
            areValid = false;
        }
        if((dexterityEditText.getText().toString().equals("")) || Integer.valueOf(dexterityEditText.getText().toString()) > 20 || Integer.valueOf(dexterityEditText.getText().toString()) < 1){
            dexterityEditText.setError("Must be 1-20");
            areValid = false;
        }
        if((constitutionEditText.getText().toString().equals("")) || Integer.valueOf(constitutionEditText.getText().toString()) > 20 || Integer.valueOf(constitutionEditText.getText().toString()) < 1){
            constitutionEditText.setError("Must be 1-20");
            areValid = false;
        }
        if((intelligenceEditText.getText().toString().equals("")) || Integer.valueOf(intelligenceEditText.getText().toString()) > 20 || Integer.valueOf(intelligenceEditText.getText().toString()) < 1){
            intelligenceEditText.setError("Must be 1-20");
            areValid = false;
        }
        if((wisdomEditText.getText().toString().equals("")) || Integer.valueOf(wisdomEditText.getText().toString()) > 20 || Integer.valueOf(wisdomEditText.getText().toString()) < 1){
            wisdomEditText.setError("Must be 1-20");
            areValid = false;
        }
        if((charismaEditText.getText().toString().equals("")) || Integer.valueOf(charismaEditText.getText().toString()) > 20 || Integer.valueOf(charismaEditText.getText().toString()) < 1){
            charismaEditText.setError("Must be 1-20");
            areValid = false;
        }

        //if input looks good, save all page 3 values into singleton
        if(areValid){
            NewCharacterSingleton newCharacter = NewCharacterSingleton.getInstance();
            newCharacter.setWizardPageThree(Integer.valueOf(strengthEditText.getText().toString()),Integer.valueOf(dexterityEditText.getText().toString()),Integer.valueOf(constitutionEditText.getText().toString()),
                    Integer.valueOf(intelligenceEditText.getText().toString()),Integer.valueOf(wisdomEditText.getText().toString()),Integer.valueOf(charismaEditText.getText().toString()));
        }

        return areValid;
    }
}