package com.harrowedtale.dungeonsanddragonscompanionapp.wizard_fragments;

import android.os.Bundle;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

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
        //if true
        NewCharacterSingleton newCharacter = NewCharacterSingleton.getInstance();

        //save all page 3 values into singleton
        newCharacter.setWizardPageThree(Integer.valueOf(strengthEditText.getText().toString()),Integer.valueOf(dexterityEditText.getText().toString()),Integer.valueOf(constitutionEditText.getText().toString()),
                Integer.valueOf(intelligenceEditText.getText().toString()),Integer.valueOf(wisdomEditText.getText().toString()),Integer.valueOf(charismaEditText.getText().toString()));

        return true;

        //else
    }
}