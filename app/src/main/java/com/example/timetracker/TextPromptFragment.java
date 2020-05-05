package com.example.timetracker;

import android.content.Context;
import android.content.SharedPreferences;
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
        super.onViewCreated(view, savedInstanceBundle);
        editText = getView().findViewById(R.id.edit_text);
        doneButton = getView().findViewById(R.id.done_button);
        cancelButton = getView().findViewById(R.id.cancel_button);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("promptInput", editText.getText().toString());
    }

    public String getInput() {
        doneButton.setOnClickListener(v ->{
            input = editText.getText().toString();
        });

        cancelButton.setOnClickListener(v ->{
            input = null;
        });
        return input;
    }
}
