package com.example.cis.mazeminotaurs.NewCharacter.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;
import com.example.cis.mazeminotaurs.character.classes.Classes;
import com.example.cis.mazeminotaurs.character.classes.Magician;
import com.example.cis.mazeminotaurs.character.classes.Specialist;
import com.example.cis.mazeminotaurs.character.classes.Warrior;
import com.example.cis.mazeminotaurs.util.Util;

/**
 * Created by jsmith on 9/15/17.
 */

public class DetailDialogFragment extends DialogFragment {
    private static final String EMPTY_MSG = "Can't select anything.";

    public interface DetailDialogListener {
        public void onDialogPositiveClick(BaseClass instance);
    }
    DetailDialogListener mListener;

    Classes mSelectedClass;
    Weapon[] mChoiceWeps;
    Weapon[] mStartWeps;
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
        mChoiceWeps = getChoiceWeapons();
        mStartWeps = getStartWeapons();

        // Populate choice weapons list.
        if (mSelectedClass.getJavaClass().getSuperclass() == Magician.class) {
            view.findViewById(R.id.choice_weapon_label).setVisibility(View.GONE);
            view.findViewById(R.id.choice_weapon_spinner).setVisibility(View.GONE);
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
            
            if (mChoiceWeps.length > 0) {
                spinItems.addAll(getWeaponNames(mChoiceWeps));
            } else {
                spinItems.add(EMPTY_MSG);
                choiceSpinner.setEnabled(false);
            }
        }

        final Spinner startSpinner = (Spinner) view.findViewById(R.id.start_weapon_spinner);
        ArrayAdapter<String> spinItems = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item);
        startSpinner.setAdapter(spinItems);
        startSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                onStartWeaponChanged(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        
        if (selectedClass == Classes.HUNTER) {
            spinItems.add(EMPTY_MSG);
            String newText = String.format("%s/%s",
                    getContext().getString(R.string.weapon_of_choice),
                    getContext().getString(R.string.starting_weapon));

            ((TextView) view.findViewById(R.id.choice_weapon_label)).setText(newText);
            view.findViewById(R.id.start_weapon_label).setVisibility(View.GONE);
            startSpinner.setVisibility(View.GONE);
        } else if (mStartWeps == null) {
            view.findViewById(R.id.start_weapon_label).setVisibility(View.GONE);
            startSpinner.setVisibility(View.GONE);
        }
        else if (mStartWeps.length > 0) {
            spinItems.addAll(getWeaponNames(mStartWeps));
        } else {
            spinItems.add(EMPTY_MSG);
            startSpinner.setEnabled(false);
        }

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setPositiveButton(R.string.confirm_button, getOnConfirmListener())
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
            } if (mSelectedClass.getJavaClass().getSuperclass() == Specialist.class) {
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
            } if (mSelectedClass.getJavaClass().getSuperclass() == Specialist.class) {
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
            }
            else {
                result[i] = "";
            }
        }
        return result;
    }

    private void onChoiceWeaponChanged(int position) {
        if (mChoiceWeps!= null && position > -1 && position < mChoiceWeps.length) {
            mSelectedChoiceWep = mChoiceWeps[position].getResId();
        }
    }

    private DialogInterface.OnClickListener getOnConfirmListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseClass instance = null;
                try {
                    instance = (BaseClass) mSelectedClass.getJavaClass().newInstance();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                if (instance != null) {
                    // Safely swaps the default weapon from default constructor
                    //TODO: Add exception for null values aka Hunter.
                    try {
                        instance.getStartGear().remove(instance.getPossibleStartWeapons()[0]);
                        Equipment oldAmmo = Util.getAmmo(instance.getPossibleStartWeapons()[0]);
                        if (oldAmmo != null) {
                            instance.getStartGear().remove(oldAmmo);
                        }

                        instance.getStartGear().add(EquipmentDB.getInstance().getWeapon(mSelectedWeapon));
                        Equipment newAmmo = Util.getAmmo(EquipmentDB.getInstance().getWeapon(mSelectedWeapon));
                        if (newAmmo != null) {
                            instance.getStartGear().add(newAmmo);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // Pass silently, this should mean that the class has no starting weapon choices.
                    } catch (NullPointerException e) {
                        // Pass silently, this means Hunter was chosen.
                    }

                    if (!(instance instanceof Magician)) {
                        if (instance instanceof Warrior) {
                            ((Warrior)instance).setWeaponOfChoice(EquipmentDB.getInstance().getWeapon(mSelectedChoiceWep));
                        } else {
                            ((Specialist)instance).setWeaponOfChoice(EquipmentDB.getInstance().getWeapon(mSelectedChoiceWep));
                        }
                    }
                    instance.setCharacter(new PlayerCharacter());
                    instance.getCharacter().setCharClass(instance);
                    instance.getCharacter().initializeClass();
                    mListener.onDialogPositiveClick(instance);
                }
            }
        };
    }

    private void onStartWeaponChanged(int position) {
        if (mStartWeps != null && position > -1 && position < mStartWeps.length) {
            mSelectedWeapon = mStartWeps[position].getResId();
        }
    }

    public void setListener(DetailDialogListener listener) {
        mListener = listener;
    }
}
