package info.infosity.Milan;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.app.Fragment;

public class FragmentTab2seen extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmenttab2, container, false);
        EditText testo;
        testo = (EditText) rootView.findViewById(R.id.testoTab2);
        int pos=Vids.numVettoreAttrazione;
        Attrazioni attuale=AtrSeen.questa;
        String generale="todo";//attuale.getTec()
        testo.setText(Html.fromHtml(generale));
        testo.setKeyListener(null);
        testo.setFocusable(false);
        return rootView;
    }

}