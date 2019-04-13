package com.harrowedtale.dungeonsanddragonscompanionapp.wizard_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.harrowedtale.dungeonsanddragonscompanionapp.NewCharacterSingleton;
import com.harrowedtale.dungeonsanddragonscompanionapp.R;

public class fragment_wizard_1 extends WizardFragment {

    EditText nameField;
    Spinner classSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_wizard_1, container, false);

        //input fields
        nameField = rootView.findViewById(R.id.name_editText);
        classSpinner = rootView.findViewById(R.id.class_spinner);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.classes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(adapter);
        
        return rootView;
    }

    @Override
    public boolean areFieldsValid() {
        //if true
        NewCharacterSingleton newCharacter = NewCharacterSingleton.getInstance();
        newCharacter.setWizardPageOne(nameField.getText().toString(), classSpinner.getSelectedItem().toString());
        return true;

        //else
    }
}
