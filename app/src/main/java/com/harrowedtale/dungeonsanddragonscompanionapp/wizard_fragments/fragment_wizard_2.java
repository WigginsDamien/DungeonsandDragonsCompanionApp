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

    EditText levelEditText;
    Spinner alignmentSpinner;
    Spinner raceSpinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_wizard_2, container, false);

        levelEditText = rootView.findViewById(R.id.level_editText);

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
        //if true
        if(!(levelEditText.getText().toString().equals("")) && !(Integer.valueOf(levelEditText.getText().toString()) < 1) && !(Integer.valueOf(levelEditText.getText().toString()) > 100)){
            NewCharacterSingleton newCharacter = NewCharacterSingleton.getInstance();
            newCharacter.setWizardPageTwo(Integer.valueOf(levelEditText.getText().toString()), alignmentSpinner.getSelectedItem().toString(), raceSpinner.getSelectedItem().toString());
            return true;
        }
        else{
            levelEditText.setError("Level must be between 1-100.");
        }

        return false;
    }
}