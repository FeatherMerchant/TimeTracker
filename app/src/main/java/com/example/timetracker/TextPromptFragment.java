package com.example.timetracker;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class TextPromptFragment extends Fragment {
    private EditText editText;
    private Button doneButton;
    private Button cancelButton;
    private String input;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        return inflater.inflate(R.layout.text_prompt_window, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceBundle) {
        Context context = getActivity();
        editText = new EditText(context);
        doneButton = getView().findViewById(R.id.done_button);
        cancelButton = getView().findViewById(R.id.cancel_button);

        doneButton.setOnClickListener(v ->{

        });
    }

    public String getInput() {

    }
}
