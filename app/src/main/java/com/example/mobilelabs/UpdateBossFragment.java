package com.example.mobilelabs;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class UpdateBossFragment extends DialogFragment {
    public String resultBossName;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        String bossName = bundle.getString("bossName");

        getDialog().setTitle("Edit boss!");
        View v = inflater.inflate(R.layout.update_boss_fragment, null);
        Button button = v.findViewById(R.id.buttonSuccess);
        TextView textViewName =  v.findViewById(R.id.editTextBossNameFragment);
        textViewName.setText(bossName);

        button.setOnClickListener(u -> {
            resultBossName = textViewName.getText().toString();
            dismiss();
        });
        return v;
    }
    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}
