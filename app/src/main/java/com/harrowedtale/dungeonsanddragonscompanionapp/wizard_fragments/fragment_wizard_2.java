package com.harrowedtale.dungeonsanddragonscompanionapp.wizard_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.harrowedtale.dungeonsanddragonscompanionapp.NewCharacterSingleton;
import com.harrowedtale.dungeonsanddragonscompanionapp.R;

public class fragment_wizard_2 extends WizardFragment {

    EditText levelEditText, hpEditText;
    Spinner alignmentSpinner;
    Spinner raceSpinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_wizard_2, container, false);

        levelEditText = rootView.findViewById(R.id.level_editText);
        hpEditText = rootView.findViewById(R.id.hitpoints_editText);

        alignmentSpinner = rootView.findViewById(R.id.alignment_spinner);
        ArrayAdapter<CharSequence> alignmentsAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.alignments_array, android.R.layout.simple_spinner_item);
        alignmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alignmentSpinner.setAdapter(alignmentsAdapter);

        raceSpinner = rootView.findViewById(R.id.race_spinner);
        ArrayAdapter<CharSequence> racesAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.races_array, android.R.layout.simple_spinner_item);
        racesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(racesAdapter);
        return rootView;
    }

    @Override
    public boolean areFieldsValid() {
        boolean areValid = true;

        //error checking for all 6 stats (sorry, I know its a lot)
        if((levelEditText.getText().toString().equals("")) || Integer.valueOf(levelEditText.getText().toString()) > 340 || Integer.valueOf(levelEditText.getText().toString()) < 1){
            levelEditText.setError("Must be 1-100");
            areValid = false;
        }
        if((hpEditText.getText().toString().equals("")) || Integer.valueOf(hpEditText.getText().toString()) > 340 || Integer.valueOf(hpEditText.getText().toString()) < 1){
            hpEditText.setError("Must be 1-340");
            areValid = false;
        }

        //if input looks good, save all page 2 values into singleton
        if(areValid){
            NewCharacterSingleton newCharacter = NewCharacterSingleton.getInstance();
            newCharacter.setWizardPageTwo(Integer.valueOf(levelEditText.getText().toString()),Integer.valueOf(hpEditText.getText().toString()),
                    alignmentSpinner.getSelectedItem().toString(), raceSpinner.getSelectedItem().toString());
        }

        return areValid;
    }
}