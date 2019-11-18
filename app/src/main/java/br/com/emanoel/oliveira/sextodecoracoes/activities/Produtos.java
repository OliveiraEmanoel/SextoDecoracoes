package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.adapters.ProdutoRecyclerViewAdapter;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.Almofada;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Produtos extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.rvToYou)
    RecyclerView rvToYou;
    ProdutoRecyclerViewAdapter adapter;
    private List<Almofada> roupasArrayList;

    static Boolean persistence = false;
    //DatabaseReference myRef;
    private final String TAG = "READING_FROM_DATABASE";
    private Almofada roupa;


    private Unbinder unbinder;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);
        rvToYou = findViewById(R.id.rvToYou);


        //todo isso não estáfuncionando
        if (isNovidade) {
            Log.e("Produtos", "Novidade = " + isNovidade);
            initializeData();
        } else {
            initializeDataForAll();
        }

        setLayoutAdapter();


    }

    private void initializeData() {

        showProgressDialog();

        roupasArrayList = new ArrayList<>();

        roupasArrayList.clear();//clear previous data

        produtoKey.clear();


        //reference to database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("produtos");


        // Read from the database
        myRef.child("almofadas").orderByChild("novidade").equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot roupasSnapshot : dataSnapshot.getChildren()) {
                    roupa = roupasSnapshot.getValue(Almofada.class);

                    if (roupa.getIsActive()) {
                        roupasArrayList.add(roupa);
                        produtoKey.add(roupasSnapshot.getKey());
                        Log.d(TAG, "Title: " + roupa.getNome() + ",description " + roupa.getDescription() + " price" + roupa.getPrice());


                    }
                }
                setLayoutAdapter();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        hideProgressDialog();
    }

    private void initializeDataForAll() {

        showProgressDialog();

        roupasArrayList = new ArrayList<>();

        roupasArrayList.clear();//clear previous data

        produtoKey.clear();


        //reference to database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("produtos");

        //showing all
        // Read from the database
        myRef.child("almofadas").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot roupasSnapshot : dataSnapshot.getChildren()) {
                    roupa = roupasSnapshot.getValue(Almofada.class);

                    if (roupa.getIsActive()) {
                        roupasArrayList.add(roupa);
                        produtoKey.add(roupasSnapshot.getKey());
                        Log.d(TAG, "Title: " + roupa.getNome() + ",description " + roupa.getDescription() + " price" + roupa.getPrice());


                    }
                }
                setLayoutAdapter();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        hideProgressDialog();
    }


    private void setLayoutAdapter() {

        //setting layout manager
        LinearLayoutManager llm = new LinearLayoutManager(Produtos.this);

        rvToYou.setHasFixedSize(true);
        rvToYou.setLayoutManager(llm);

        //setting adapter
        adapter = new ProdutoRecyclerViewAdapter(Produtos.this, roupasArrayList);

        adapter.notifyDataSetChanged();

        rvToYou.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //habilita cadastro?
        if (userIsAdmin) {
            Log.d("MENU ITEM", "onCreate: userAdmin");

            MenuItem menuItem = menu.findItem(R.id.action_cadastro);
            menuItem.setVisible(true);

            MenuItem configItem = menu.findItem(R.id.action_settings);
            configItem.setVisible(true);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, ConfigActivity.class));
            return true;

        } else if (id == R.id.action_cadastro) {

            startActivity(new Intent(this, CadastroProdutos.class));
            //cadastrar novas fotos
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_almofada) {
            isNovidade = false;
            startActivity(new Intent(getApplicationContext(), Produtos.class));
        } else if (id == R.id.nav_almofada_novidades) {
            isNovidade = true;
            startActivity(new Intent(getApplicationContext(), Produtos.class));
        } else if (id == R.id.nav_cart) {

            if (nroItensCart < 0) {
                Toast.makeText(getApplicationContext(), "Carrinho vazio!!", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(getApplicationContext(), CarrinhoActivity.class));
            }
        } else if (id == R.id.nav_sair) {
            finishAffinity();
            System.exit(0);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void startPagseguroLib(String emailSeller, String tokenSeller, int layoutID, Activity activity) {
//
//
//        //Inicialização a lib com parametros necessarios
//        PSCheckoutConfig psCheckoutConfig = new PSCheckoutConfig();
//        psCheckoutConfig.setSellerEmail(emailSeller);
//        psCheckoutConfig.setSellerToken(tokenSeller);
//        //Informe o fragment container
//        psCheckoutConfig.setContainer(layoutID);
//
//        //Inicializa apenas os recursos de pagamento transparente e boleto
//        PSCheckout.initTransparent(activity, psCheckoutConfig);
//
//        //Caso queira inicializar todos os recursos da lib
//        //PSCheckout.init(getActivity(), psCheckoutConfig);
//
//
//    }
//
//    //inicialização da biblioteca do pagseguro
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        PSCheckout.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
//    }


}
