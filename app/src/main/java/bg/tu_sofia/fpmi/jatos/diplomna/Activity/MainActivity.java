package bg.tu_sofia.fpmi.jatos.diplomna.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import bg.tu_sofia.fpmi.jatos.diplomna.Class.Terrain;
import bg.tu_sofia.fpmi.jatos.diplomna.Methods.Buffer;
import bg.tu_sofia.fpmi.jatos.diplomna.Data.PrefConfig;
import bg.tu_sofia.fpmi.jatos.diplomna.Methods.Methods;
import bg.tu_sofia.fpmi.jatos.diplomna.R;
import bg.tu_sofia.fpmi.jatos.diplomna.Data.SQLController;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;

    private TextView menuHeadCapitalValue;

    private View headerView;

    private ListView mainListТerrains;

    private NavigationView mainNavigationView;

    private FloatingActionButton mainButtonAdd;

    private Button menuHeadCapitalButtonEdit;

    private int capital;

    private String title;
    private String[] result;


    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // NavigationView
        mainNavigationView =(NavigationView) findViewById(R.id.mainNavigationView);
        drawerLayout =(DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                int j=0;
                int sumCost = 0;
                int [] costs = new int[Buffer.terrains.size()];
                int [] indexs = new int[Buffer.terrains.size()];
                double[] efficiency = new double[Buffer.terrains.size()];
                double[] maxEfficiency = new double[1];

                Intent intent = new Intent(MainActivity.this, Result.class);
                if (id==R.id.criteria1){
                    for (Terrain i : Buffer.terrains)
                    {
                        Methods.eff(i);
                        Methods.MaximumExpectedProfit(i);
                        costs[j] = i.getOptimalCost();
                        efficiency[j] = i.getOptimalEff();
                        j++;
                    }
                    Methods.knapSack(capital, costs,efficiency,indexs,Buffer.terrains.size());
                    sumCost = Methods.sumCosts(costs,indexs,efficiency,maxEfficiency);
                    title = "MaximumExpectedProfit" +"\n"+ "Total cost: "+ sumCost+ " Total efficiency: "+maxEfficiency[0];
                    result = Buffer.setResult(indexs);
                    intent.putExtra("title",title);
                    intent.putExtra("result",result);
                    startActivity(intent);
                } else if(id==R.id.criteria2){
                     for (Terrain i : Buffer.terrains)
                     {
                         Methods.eff(i);
                         Methods.MaximumProfitInTheMostProbableCondition(i);
                         costs[j] = i.getOptimalCost();
                         efficiency[j] = i.getOptimalEff();
                         j++;
                     }
                     Methods.knapSack(capital, costs,efficiency,indexs,Buffer.terrains.size());
                     sumCost = Methods.sumCosts(costs,indexs,efficiency,maxEfficiency);
                     title = "MaximumProfitInTheMostProbableCondition" +"\n"+"Total cost: "+ sumCost+ " Total efficiency: "+maxEfficiency[0];
                     result = Buffer.setResult(indexs);
                     intent.putExtra("title",title);
                     intent.putExtra("result",result);
                     startActivity(intent);
                } else if (id==R.id.criteria3){
                     for (Terrain i : Buffer.terrains)
                     {
                         Methods.eff(i);
                         Methods.MaximumGuaranteedProfit(i);
                         costs[j] = i.getOptimalCost();
                         efficiency[j] = i.getOptimalEff();
                         j++;
                     }
                     Methods.knapSack(capital, costs,efficiency,indexs,Buffer.terrains.size());
                     sumCost = Methods.sumCosts(costs,indexs,efficiency,maxEfficiency);
                     title = "MaximumGuaranteedProfit" +"\n"+"Total cost: "+ sumCost+ " Total efficiency: "+maxEfficiency[0];
                     result = Buffer.setResult(indexs);
                     intent.putExtra("title",title);
                     intent.putExtra("result",result);
                     startActivity(intent);
                }
                return false;
            }
        });

        // NavigationView Head
        headerView = mainNavigationView.getHeaderView(0);
        menuHeadCapitalValue = headerView.findViewById(R.id.menuHeadCapitalValue);
        menuHeadCapitalButtonEdit = headerView.findViewById(R.id.menuHeadCapitalButtonEdit);

            capital = PrefConfig.loadTotalFromPref(this);
            menuHeadCapitalValue.setText("Capital: "+capital);


        menuHeadCapitalButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditCapital.class);
                startActivity(intent);
            }
        });

        // List

        mainListТerrains =(ListView)findViewById(R.id.mainListТerrains);

        Buffer.mydb = new SQLController(this);

        Buffer.fillListFromDB(this);
        Buffer.arrToArrT(list);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);

        mainListТerrains.setAdapter((arrayAdapter));

        mainListТerrains.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this,Edit.class);
               // intent.putExtra("editTerr", Buffer.terrains.get(i));
                Buffer.terrain=Buffer.terrains.get(i);
                startActivity(intent);
            }
        });

       // FloatingActionButton

       mainButtonAdd = findViewById(R.id.mainButtonAdd);

       mainButtonAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, Add.class);
               startActivity(intent);
           }
       });

    }

   // NavigationView
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
       if (mToggle.onOptionsItemSelected(item)){
           return true;
       }
       return super.onOptionsItemSelected(item);
   }
}