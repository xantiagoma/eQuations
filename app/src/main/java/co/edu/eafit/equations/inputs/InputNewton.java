package co.edu.eafit.equations.inputs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.BigDecimal;

import co.edu.eafit.equations.Methods;
import co.edu.eafit.equations.R;
import co.edu.eafit.equations.Tabs;
import co.edu.eafit.equations.tables.TableNewton;

public class InputNewton extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private int sectionNumber;
    public static InputNewton newInstance(int sectionNumber) {
        InputNewton fragment = new InputNewton();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public InputNewton() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_input_newton, null);
        final EditText inpt_function = (EditText)rootView.findViewById(R.id.inpt_function);
        final EditText inpt_function_dfx = (EditText)rootView.findViewById(R.id.inpt_function_dfx);
        final EditText inpt_tol = (EditText)rootView.findViewById(R.id.inpt_tol);
        final EditText inpt_xlower = (EditText)rootView.findViewById(R.id.inpt_xlower);
        final EditText inpt_iter = (EditText)rootView.findViewById(R.id.inpt_iter);
        final TextView txt_push = (TextView) rootView.findViewById(R.id.txt_push);
        final RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.RG);
        final int[] tipeError = new int[1];
        Button btn_run = (Button)rootView.findViewById(R.id.btn_run);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdb_errorAb) {
                    tipeError[0] = 1;
                    txt_push.setText(R.string.absolute_error);
                } else if(checkedId == R.id.rdb_errorRe){
                    tipeError[0] = 0;
                    txt_push.setText(R.string.relative_error);
                }


            }
        });
        btn_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Tabs) getActivity()).getTabla().getArray().clear();
                BigDecimal x0 = new BigDecimal(inpt_xlower.getText().toString());
                BigDecimal tol = new BigDecimal(inpt_tol.getText().toString());
                long iter = Integer.parseInt(inpt_iter.getText().toString());
                ((Tabs) getActivity()).getTabla().addHead(
                        getActivity().getResources().getStringArray(
                                R.array.cabecera_tabla_Newton
                        )
                );
                Methods.Newton(x0, tol, iter, tipeError[0], inpt_function.getText().toString(),
                        inpt_function_dfx.getText().toString(), ((Tabs) getActivity()).getTabla()

                );
                ((TableNewton) ((Tabs) getActivity()).getFragmentTable()).load_tables();
                txt_push.setText(((Tabs) getActivity()).getTabla().getResult());
            }
        });
        return rootView;
    }
}
