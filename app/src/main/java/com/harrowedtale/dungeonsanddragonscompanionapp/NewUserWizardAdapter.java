package com.harrowedtale.dungeonsanddragonscompanionapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.harrowedtale.dungeonsanddragonscompanionapp.wizard_fragments.WizardFragment;
import com.harrowedtale.dungeonsanddragonscompanionapp.wizard_fragments.fragment_wizard_1;
import com.harrowedtale.dungeonsanddragonscompanionapp.wizard_fragments.fragment_wizard_2;
import com.harrowedtale.dungeonsanddragonscompanionapp.wizard_fragments.fragment_wizard_3;

public class NewUserWizardAdapter extends FragmentPagerAdapter {

    WizardFragment[] wizardPages = {new fragment_wizard_1(), new fragment_wizard_2(), new fragment_wizard_3()};
    String [] wizardTitles = {"Basics", "Details", "Stats"};


    public NewUserWizardAdapter(FragmentManager fm) {
        super(fm);
    }

    //get fragment at position
    @Override
    public Fragment getItem(int position) {
        return wizardPages[position];
    }

    //get count of fragments in adapter
    @Override
    public int getCount() {
        return wizardPages.length;
    }

    //grab title for the fragment (currently not used)
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return wizardTitles[position];
    }
}
