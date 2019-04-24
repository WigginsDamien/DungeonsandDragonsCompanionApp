package com.harrowedtale.dungeonsanddragonscompanionapp.wizard_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.harrowedtale.dungeonsanddragonscompanionapp.NewCharacterSingleton;
import com.harrowedtale.dungeonsanddragonscompanionapp.R;

public class fragment_wizard_4 extends WizardFragment{

    Spinner profSpinner1, profSpinner2, profSpinner3, profSpinner4;
    TextView profTextView3, profTextView4, classTextView;
    NewCharacterSingleton newCharacter = NewCharacterSingleton.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_wizard_4, container, false);
        classTextView = rootView.findViewById(R.id.prof_class_textView);
        classTextView.setText(getString(R.string.class_label_wiz, newCharacter.getCharacterClass()));

        profSpinner1 = rootView.findViewById(R.id.prof1_spinner);
        ArrayAdapter<CharSequence> prof1_adapter = ArrayAdapter.createFromResource(getActivity(), newCharacter.getClassProficiencies(), android.R.layout.simple_spinner_item);
        prof1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profSpinner1.setAdapter(prof1_adapter);

        profSpinner2 = rootView.findViewById(R.id.prof2_spinner);
        ArrayAdapter<CharSequence> prof2_adapter = ArrayAdapter.createFromResource(getActivity(), newCharacter.getClassProficiencies(), android.R.layout.simple_spinner_item);
        prof2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profSpinner2.setAdapter(prof2_adapter);
        profSpinner2.setSelection(1);

        profSpinner3 = rootView.findViewById(R.id.prof3_spinner);
        profSpinner4 = rootView.findViewById(R.id.prof4_spinner);
        profTextView3 = rootView.findViewById(R.id.prof3_textView);
        profTextView4 = rootView.findViewById(R.id.prof4_textView);
        ArrayAdapter<CharSequence> prof3_adapter; //used twice, so I had to declare before switch

        //we don't always need a 3rd/4th proficiency, so we check to see if we create them or not.
        switch (newCharacter.getProficiencyCount())
        {
            case 2:
                profSpinner3.setVisibility(View.GONE);
                profSpinner4.setVisibility(View.GONE);
                profTextView3.setVisibility(View.GONE);
                profTextView4.setVisibility(View.GONE);
                break;
            case 3:
                profSpinner4.setVisibility(View.GONE);
                profTextView4.setVisibility(View.GONE);

                prof3_adapter = ArrayAdapter.createFromResource(getActivity(), newCharacter.getClassProficiencies(), android.R.layout.simple_spinner_item);
                prof3_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                profSpinner3.setAdapter(prof3_adapter);
                profSpinner3.setSelection(2);
                break;
            case 4:
                prof3_adapter = ArrayAdapter.createFromResource(getActivity(), newCharacter.getClassProficiencies(), android.R.layout.simple_spinner_item);
                prof3_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                profSpinner3.setAdapter(prof3_adapter);
                profSpinner3.setSelection(2);

                ArrayAdapter<CharSequence> prof4_adapter = ArrayAdapter.createFromResource(getActivity(), newCharacter.getClassProficiencies(), android.R.layout.simple_spinner_item);
                prof4_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                profSpinner4.setAdapter(prof4_adapter);
                profSpinner3.setSelection(3);
                break;
            default:
                //this shouldn't be possible
                break;
        }

        return rootView;
    }

    @Override
    public boolean areFieldsValid() {
        boolean areValid = true;

        //checks if input is correct. (structured with ifs so we don't do checks on 3/4 if they don't exist, etc.)
        if(profSpinner1.getSelectedItem().toString().equals(profSpinner2.getSelectedItem().toString())){
            ((TextView)profSpinner2.getSelectedView()).setError("Duplicate Proficiency");
            areValid = false;
        }

        //if more than 2 proficiency selections
        if(newCharacter.getProficiencyCount() > 2){
            if(profSpinner1.getSelectedItem().toString().equals(profSpinner3.getSelectedItem().toString())){
                ((TextView)profSpinner3.getSelectedView()).setError("Duplicate Proficiency");
                areValid = false;
            }
            if(profSpinner2.getSelectedItem().toString().equals(profSpinner3.getSelectedItem().toString())){
                ((TextView)profSpinner3.getSelectedView()).setError("Duplicate Proficiency");
                areValid = false;
            }
        }
        if(newCharacter.getProficiencyCount() > 3){
            if(profSpinner1.getSelectedItem().toString().equals(profSpinner4.getSelectedItem().toString())){
                ((TextView)profSpinner4.getSelectedView()).setError("Duplicate Proficiency");
                areValid = false;
            }
            if(profSpinner2.getSelectedItem().toString().equals(profSpinner4.getSelectedItem().toString())){
                ((TextView)profSpinner4.getSelectedView()).setError("Duplicate Proficiency");
                areValid = false;
            }
            if(profSpinner3.getSelectedItem().toString().equals(profSpinner4.getSelectedItem().toString())){
                ((TextView)profSpinner4.getSelectedView()).setError("Duplicate Proficiency");
                areValid = false;
            }
        }

        //if input looks good, submit proficiencies to singleton
        if(areValid){
            switch (newCharacter.getProficiencyCount())
            {
                case 2:
                    newCharacter.setWizardPageFour(profSpinner1.getSelectedItem().toString(), profSpinner2.getSelectedItem().toString(), "default", "default");
                    break;
                case 3:
                    newCharacter.setWizardPageFour(profSpinner1.getSelectedItem().toString(), profSpinner2.getSelectedItem().toString(), profSpinner3.getSelectedItem().toString(), "default");
                    break;
                case 4:
                    newCharacter.setWizardPageFour(profSpinner1.getSelectedItem().toString(), profSpinner2.getSelectedItem().toString(), profSpinner3.getSelectedItem().toString(), profSpinner4.getSelectedItem().toString());
                    break;
                default:
                    //this shouldn't be possible
                    break;
            }
            return true;
        }

        return areValid;
    }
}
