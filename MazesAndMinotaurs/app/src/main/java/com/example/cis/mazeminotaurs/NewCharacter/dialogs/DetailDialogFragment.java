package com.example.cis.mazeminotaurs.NewCharacter.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.classes.Classes;
import com.example.cis.mazeminotaurs.character.classes.Magician;
import com.example.cis.mazeminotaurs.character.classes.Specialist;
import com.example.cis.mazeminotaurs.character.classes.Warrior;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by jsmith on 9/15/17.
 */

public class DetailDialogFragment extends DialogFragment {
    public interface DetailDialogListener {
        public void onDialogPositiveClick(DialogFragment dialogFragment);
        public void onDialogNegativeClick(DialogFragment dialogFragment);
    }
    DetailDialogListener mListener;

    Classes mSelectedClass;
    // Ints cannot have a null value so this is a replacement for it.
    int mSelectedWeapon = -43762;
    int mSelectedChoiceWep = -43762;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();

        LayoutInflater li = LayoutInflater.from(getContext());
        View view = li.inflate(R.layout.dialog_class_selection, null);
        // Class Name
        ((TextView) view.findViewById(R.id.class_label))
                .setText(args.getInt("class"));
        // Class Description
        ((TextView) view.findViewById(R.id.class_description))
                .setText(args.getInt("classInfo"));

        Classes selectedClass = Classes.AMAZON;
        for (Classes cClass: Classes.values()) {
            if (cClass.getResId() == args.getInt("class")) {
                selectedClass = cClass;
                break;
            }
        }
        mSelectedClass = selectedClass;
        // Populate choice weapons list.
        if (mSelectedClass.getJavaClass().getSuperclass() == Magician.class) {
            view.findViewById(R.id.choice_weapon_spinner).setEnabled(false);
        } else {
            Spinner choiceSpinner = (Spinner)view.findViewById(R.id.choice_weapon_spinner);
            ArrayAdapter<String> spinItems = new ArrayAdapter<>(getContext(),
                    R.layout.support_simple_spinner_dropdown_item);
            choiceSpinner.setAdapter(spinItems);
            choiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    onChoiceWeaponChanged(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spinItems.addAll(getWeaponNames(getChoiceWeapons()));
        }

        final Spinner startSpinner = (Spinner) view.findViewById(R.id.start_weapon_spinner);
        final ArrayAdapter<String> spinItems = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item);
        startSpinner.setAdapter(spinItems);
        spinItems.addAll(getWeaponNames(getStartWeapons()));

        startSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                onStartWeaponChanged(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Character Details")
                .setView(view)
                .create();
        return dialog;
    }

    @Override
    // Thanks to @kuffs (https://stackoverflow.com/questions/32083053/android-fragment-onattach-deprecated)
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            //EMPTY FOR NOW
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + "is not a DetailDialogListener!");
        }
    }

    private Weapon[] getChoiceWeapons() {
        try {
            Object instance = mSelectedClass.getJavaClass().newInstance();
            if (mSelectedClass.getJavaClass().getSuperclass() == Warrior.class) {
                Warrior wClass = (Warrior) instance;
                return wClass.getPossibleWeaponsOfChoice();
            } else {
                Specialist sClass = (Specialist) instance;
                return sClass.getPossibleWeaponsOfChoice();
            }
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Weapon[] getStartWeapons(){
        try {
            Object instance = mSelectedClass.getJavaClass().newInstance();
            if (mSelectedClass.getJavaClass().getSuperclass() == Warrior.class) {
                Warrior wClass = (Warrior) instance;
                return wClass.getPossibleStartWeapons();
            } else {
                Specialist sClass = (Specialist) instance;
                return sClass.getPossibleStartWeapons();
            }
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String[] getWeaponNames(Weapon... weapons) {
        String[] result = new String[weapons.length];
        for (int i = 0; i < result.length; i++) {
            if (weapons[i] != null) {
                result[i] = getContext().getString(weapons[i].getResId());
            } else {
                result[i] = null;
            }
        }
        return result;
    }

    private void onChoiceWeaponChanged(int position) {
        Weapon[] choiceWeps = getChoiceWeapons();
        if (choiceWeps!= null && position > -1 && position < choiceWeps.length) {
            mSelectedChoiceWep = choiceWeps[position].getResId();
        }
    }

    private void onStartWeaponChanged(int position) {
        Weapon[] startWeps = getStartWeapons();
        if (startWeps != null && position > -1 && position < startWeps.length) {
            mSelectedWeapon = getStartWeapons()[position].getResId();
        }
    }

}
