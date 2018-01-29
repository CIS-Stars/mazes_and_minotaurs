package com.example.cis.mazeminotaurs.NewCharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.NewCharacter.dialogs.DetailDialogFragment;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.character.classes.BaseClass;

/**
 * This fragment displays all of the playable classes for players in the app.
 * The user can select a class and start a new character.
 *
 * Cycle:
 * <ol>
 *     <li>CharacterCreationFragment</li>
 *     <li>DetailDialogFragment</li>
 *     <li>CreateCharacter</li>
 * </ol>
 *
 * @author Chaos on 4/4/2017.
 */

public class CharacterCreationFragment extends Fragment implements DetailDialogFragment.DetailDialogListener {

    /*
     * These are the widgets found in the layout.
     */

    public ImageButton mWarriorButton;
    public ImageButton mMagicianButton;
    public ImageButton mSpecialistButton;

    public LinearLayout mWarriorGroup;
    public LinearLayout mMagicianGroup;
    public LinearLayout mSpecialistGroup;

    public TextView mSelectClass;
    public String mClass;

    /**
     * This is the dialog instance that is called on class select.
     */
    DetailDialogFragment mDialog;

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        super.onCreateView(li, vg, b);
        View rootView = li.inflate(R.layout.fragment_character_creation, vg, false);

        mWarriorButton = (ImageButton) rootView.findViewById(R.id.warrior_button);
        mMagicianButton = (ImageButton) rootView.findViewById(R.id.magician_button);
        mSpecialistButton = (ImageButton) rootView.findViewById(R.id.specialist_button);

        mWarriorGroup = (LinearLayout) rootView.findViewById(R.id.warrior_linear);
        mMagicianGroup = (LinearLayout) rootView.findViewById(R.id.magician_linear);
        mSpecialistGroup = (LinearLayout) rootView.findViewById(R.id.specialist_linear);

        mSelectClass = (TextView) rootView.findViewById(R.id.select_class_view);

        mWarriorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectClass.setVisibility(View.VISIBLE);
                mWarriorGroup.setVisibility(View.VISIBLE);
                mMagicianGroup.setVisibility(View.GONE);
                mSpecialistGroup.setVisibility(View.GONE);
            }
        });

        mMagicianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectClass.setVisibility(View.VISIBLE);
                mWarriorGroup.setVisibility(View.GONE);
                mMagicianGroup.setVisibility(View.VISIBLE);
                mSpecialistGroup.setVisibility(View.GONE);
            }
        });

        mSpecialistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectClass.setVisibility(View.VISIBLE);
                mWarriorGroup.setVisibility(View.GONE);
                mMagicianGroup.setVisibility(View.GONE);
                mSpecialistGroup.setVisibility(View.VISIBLE);
            }
        });

        // Hooks up listeners for every classes' button
        // Warriors
        rootView.findViewById(R.id.amazon_button).setOnClickListener(createButtonClickListener(R.string.amazon, R.string.amazon_class));
        rootView.findViewById(R.id.barbarian_button).setOnClickListener(createButtonClickListener(R.string.barbarian, R.string.barbarian_class));
        rootView.findViewById(R.id.centaur_button).setOnClickListener(createButtonClickListener(R.string.centaur, R.string.centaur_class));
        rootView.findViewById(R.id.noble_button).setOnClickListener(createButtonClickListener(R.string.noble, R.string.noble_class));
        rootView.findViewById(R.id.spear_button).setOnClickListener(createButtonClickListener(R.string.spearman, R.string.spearman_class));

        // Magicians
        rootView.findViewById(R.id.elementalist_button).setOnClickListener(createButtonClickListener(R.string.elementalist, R.string.elementalist_class));
        rootView.findViewById(R.id.lyrist_button).setOnClickListener(createButtonClickListener(R.string.lyrist, R.string.lyrist_class));
        rootView.findViewById(R.id.nymph_button).setOnClickListener(createButtonClickListener(R.string.nymph, R.string.nymph_class));
        rootView.findViewById(R.id.priest_button).setOnClickListener(createButtonClickListener(R.string.priest, R.string.priest_class));
        rootView.findViewById(R.id.sorcerer_button).setOnClickListener(createButtonClickListener(R.string.sorceror, R.string.sorcerer_class));

        // Specialists
        rootView.findViewById(R.id.hunter_button).setOnClickListener(createButtonClickListener(R.string.hunter, R.string.hunter_class));
        rootView.findViewById(R.id.thief_button).setOnClickListener(createButtonClickListener(R.string.thief, R.string.thief_class));

        return rootView;
    }

    /**
     * Helper method that shows the detail dialog on button click.
     *
     * @param classRes    the resource id of the class' name
     * @param descriptRes the resource id of the class' description.
     * @return the onclick listener.
     */
    private View.OnClickListener createButtonClickListener(final int classRes, final int descriptRes) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetailDialog(classRes, descriptRes);
            }
        };
    }

    /**
     * Helper method for showing the detail dialog.
     * Automatically adds the arguments and attaches the listener. Uses hardcoded
     * TAG value.
     * @param classRes the resource id of the class' name.
     * @param descriptRes the resource id of the class' description.
     */
    public void showDetailDialog(int classRes, int descriptRes) {
        mDialog = new DetailDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("class", classRes);
        bundle.putInt("classInfo", descriptRes);
        mDialog.setArguments(bundle);
        mDialog.setListener(this);

        mDialog.show(getChildFragmentManager(), "DetailDialogFragment");
    }

    /**
     * {@inheritDoc}
     *
     * From here, the third step of the cycle is started. The arguments for selected class and
     * the instance of said class are passed on as well.
     * @param instance a new instance of the user selected class.
     */
    @Override
    public void onDialogPositiveClick(BaseClass instance) {
        CreateCharacter addedType = new CreateCharacter();
        Bundle classType = new Bundle();
        classType.putString("newClass", mClass);
        classType.putSerializable("classInstance", instance);
        addedType.setArguments(classType);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, addedType)
                .addToBackStack(null)
                .commit();
    }
}
