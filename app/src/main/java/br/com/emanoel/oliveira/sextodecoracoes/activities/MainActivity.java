package br.com.emanoel.oliveira.sextodecoracoes.activities;

public class MainActivity extends BaseActivity {
//    //referencias ao recycler view
//    private RecyclerView mRecyclerView;
//    private RecyclerView.LayoutManager mlayoutManager;
//    private RecyclerView.Adapter mAdapter;
//    private ArrayList<String> mDataSet;
//    private ArrayList<Integer> imageResource;
//    private ArrayList<Integer> imageChair, imageSofa, imageAlmofada;
//    private ImageView ivMain;
//    private int tipoModelo = 1;//poltrona as default model
//    private int actualPosition;
//    private String nameFabric;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
////        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
////        iv = new MyCustonListener() {
////            @Override
////            public void onItemClicked(View v, int position) {
////                ivMain.setImageResource(imageChair.get(position));
////            }
////        };
//
//
//        ivMain = findViewById(R.id.iv_main);
//
//
//        mDataSet = new ArrayList<>();
//        imageResource = new ArrayList<>();
//        imageChair = new ArrayList<>();
//        imageSofa = new ArrayList<>();
//        imageAlmofada = new ArrayList<>();
//
//        mDataSet.add("Correntes");
//        mDataSet.add("Flechas");
//        mDataSet.add("Xadrez");
//        mDataSet.add("Verde damasco");
//
//        imageResource.add(R.drawable.correntes);
//        imageResource.add(R.drawable.flechas);
//        imageResource.add(R.drawable.xadrez);
//        imageResource.add(R.drawable.verde_damask);
//
//        imageChair.add(R.drawable.chair_geo_preto);
//        imageChair.add(R.drawable.chair_seta);
//        imageChair.add(R.drawable.chair_xadrez);
//        imageChair.add(R.drawable.chair_floral);
//
//        imageSofa.add(R.drawable.sofa_correntes);
//        imageSofa.add(R.drawable.sofa_setas);
//        imageSofa.add(R.drawable.sofa_xadrez);
//        imageSofa.add(R.drawable.sofa_verde);
//
//        imageAlmofada.add(R.drawable.almofada_correntes);
//        imageAlmofada.add(R.drawable.almofada_setas);
//        imageAlmofada.add(R.drawable.almofada_xadrez);
//        imageAlmofada.add(R.drawable.almofada_verde);
//
//        //testing globalArray
//        globalArray.add("teste");
//        globalArray.add("será");
//        globalArray.add("talvez");
//
////        for (int i = 0; i < 30 ; i++) {
////
////            mDataSet.add("Novo titulo # " + i);
////
////        }
//
//        mRecyclerView = findViewById(R.id.rvTexturas);
//        mRecyclerView.setHasFixedSize(true);
//        mlayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerView.setLayoutManager(mlayoutManager);
//
//        //MyCustonListener is used to listener the click event on the recyclerviewAdapter and send it to the mainActivity
//        //In the mainActivity we have the callback and then use it to load the image on the mainActivity
//        mAdapter = new MainAdapter(mDataSet, imageResource, imageChair, new MyCustonListener() {
//            @Override
//            public void onItemClicked(View v, int position) {
//                //Log.d("Item clicado", "clicked position:" + position);
//                //Toast.makeText(MainActivity.this, "Clicked Item: "+position,Toast.LENGTH_SHORT).show();
//
//                rvHasClicked = true; //used to know if image on recyclerView has received a click and free the click
//                //on ivMain...
//
//
//                if (tipoModelo == 1) {
//                    actualPosition = position;
//                    nameFabric = mDataSet.get(position);
//                    ivMain.setImageResource(imageChair.get(position));//Todo get the image from cloud storage
//                } else if (tipoModelo == 2) {
//                    actualPosition = position;
//                    nameFabric = mDataSet.get(position);
//                    ivMain.setImageResource(imageSofa.get(position));//Todo get the image from cloud storage
//                } else if (tipoModelo == 3) {
//                    actualPosition = position;
//                    nameFabric = mDataSet.get(position);
//                    ivMain.setImageResource(imageAlmofada.get(position));//Todo get the image from cloud storage
//                }
//            }
//        });
//        //listener to ivMain
//        ivMain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //call detailsActivity
//                //pass the fabric if has a one
//                if (rvHasClicked){
//                    Intent intent = new Intent(getApplicationContext(), DetalhesActivity.class);
//                    intent.setAction(Intent.ACTION_SEND);
//                    intent.putExtra("nomeTecido", nameFabric);
//                    intent.putExtra("image position", actualPosition);
//
//                    startActivity(intent);
//
//                }else {
//
//                    Toast.makeText(getApplicationContext(),"Clic em uma estampa...",Toast.LENGTH_SHORT).show();
//
//                }
//
//
//            }
//        });
//
//        mRecyclerView.setAdapter(mAdapter);
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        //habilita cadastro?
//        if (userIsAdmin){
//            Log.d("MENU ITEM", "onCreate: userAdmin");
//
//          MenuItem menuItem = menu.findItem(R.id.action_cadastro);
//           menuItem.setVisible(true);
//
//           }
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//
//        } else if (id == R.id.action_cadastro) {
//
//            startActivity(new Intent(this,CadastroProdutos.class));
//            //cadastrar novas fotos
//            return true;
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_almofada) {
//            ivMain.setImageResource(R.drawable.almofada);
//            tipoModelo = 3;
//        } else if (id == R.id.nav_cart) {
//
//            if (nroItensCart < 0) {
//                Toast.makeText(getApplicationContext(),"Carrinho vazio!!",Toast.LENGTH_SHORT).show();
//            } else {
//                startActivity(new Intent(getApplicationContext(), CarrinhoActivity.class));
//            }
//        } else if (id == R.id.nav_sair) {
//
//            finish();
//            System.exit(0);
//
//        }
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

}
