package co.edu.eafit.equations;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import co.edu.eafit.equations.helps.Help;
import co.edu.eafit.equations.inputs.InputBisection;
import co.edu.eafit.equations.inputs.InputFalsePosition;
import co.edu.eafit.equations.inputs.InputFixedPoint;
import co.edu.eafit.equations.inputs.InputIncrementalSearches;
import co.edu.eafit.equations.inputs.InputMultipleRoots;
import co.edu.eafit.equations.inputs.InputNewton;
import co.edu.eafit.equations.inputs.InputSecant;
import co.edu.eafit.equations.tables.TableBisection;
import co.edu.eafit.equations.tables.TableFalsePosition;
import co.edu.eafit.equations.tables.TableFixedPoint;
import co.edu.eafit.equations.tables.TableIncrementalSearches;
import co.edu.eafit.equations.tables.TableMultipleRoots;
import co.edu.eafit.equations.tables.TableNewton;
import co.edu.eafit.equations.tables.TableSecant;

public class Tabs extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Fragment fragmentInput;
    private Fragment fragmentTable;
    private Fragment fragmentHelp;
    public  Fragment getFragmentInput(){return this.fragmentInput;}
    public  Fragment getFragmentTable(){return this.fragmentTable;}
    public  Fragment getFragmentHelp(){return this.fragmentHelp;}
    public void setFragmentInput(Fragment fragment){this.fragmentInput = fragment;}
    public void setFragmentTable(Fragment fragment){this.fragmentTable = fragment;}
    public void setFragmentHelp(Fragment fragment){this.fragmentHelp = fragment;}
    private Tabla tabla;
    public Tabla getTabla(){
        return this.tabla;
    }
    public void setTabla(Tabla tabla){
        this.tabla = tabla;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        String nameToolbar = getIntent().getExtras().getString("type");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(nameToolbar);
        setTabla(new Tabla());
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment tab = null;
            int type = getIntent().getExtras().getInt("id");
            switch (position) {
                case 0: //Entradas
                    if(fragmentInput!=null){
                        return fragmentInput;
                    }else{
                        switch (type){
                            case 0:
                                fragmentInput = InputIncrementalSearches.newInstance();
                                break;
                            case 1:
                                fragmentInput = InputBisection.newInstance(position);
                                break;
                            case 2:
                                fragmentInput = InputFalsePosition.newInstance(position);
                                break;
                            case 3:
                                fragmentInput = InputFixedPoint.newInstance(position);
                                break;
                            case 4:
                                fragmentInput = InputNewton.newInstance(position);
                                break;
                            case 5:
                                fragmentInput = InputSecant.newInstance(position);
                                break;
                            case 6:
                                fragmentInput = InputMultipleRoots.newInstance(position);
                                break;
                            default:
                                fragmentInput = null;
                                break;
                        }
                        return fragmentInput;
                    }
                    //break;
                case 1:
                    if(fragmentTable!=null){
                        return fragmentTable;
                    }else {
                        switch (type) {
                            case 0:
                                fragmentTable = TableIncrementalSearches.newInstance();
                                break;
                            case 1:
                                fragmentTable = TableBisection.newInstance(position);
                                break;
                            case 2:
                                fragmentTable = TableFalsePosition.newInstance(position);
                                break;
                            case 3:
                                fragmentTable = TableFixedPoint.newInstance(position);
                                break;
                            case 4:
                                fragmentTable = TableNewton.newInstance(position);
                                break;
                            case 5:
                                fragmentTable = TableSecant.newInstance(position);
                                break;
                            case 6:
                                fragmentTable = TableMultipleRoots.newInstance(position);
                                break;
                            default:
                                fragmentTable = null;
                                break;
                        }
                        ;
                        return fragmentTable;
                        //break;
                    }
                case 2:
                    if(fragmentHelp!=null){
                        return fragmentHelp;
                    }else{
                        switch (type){
                            //Usar por si nesecita un help en especifico
                            default:
                                fragmentHelp =  Help.newInstance(position);
                                break;
                        }
                        return fragmentHelp;
                        //break;
                    }
                default:
                    Log.i("ErrorMenu:","Creacion de Frament Invalida");
            }
            return tab;
        }

        @Override
        public int getCount() { //Numero de paginas
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Entrada";
                case 1:
                    return "Tabla";
                case 2:
                    return "Ayuda";
            }
            return "Default";
        }
    }

}