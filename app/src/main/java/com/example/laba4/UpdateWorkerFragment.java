package com.example.laba4;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class UpdateWorkerFragment extends DialogFragment{
    public String resultWorkerName;
    public int resultWorkerAge;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        String workerName = bundle.getString("workerName");
        int workerAge = bundle.getInt("workerAge");

        getDialog().setTitle("Update worker's name");
        View v = inflater.inflate(R.layout.update_worker_fragment, null);
        Button buttonSuccess = v.findViewById(R.id.buttonSuccess);
        TextView textViewName =  v.findViewById(R.id.editTextWorkerNameFragment);
        textViewName.setText(workerName);

        buttonSuccess.setOnClickListener(u -> {
            resultWorkerName = textViewName.getText().toString();
            resultWorkerAge = workerAge;
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
