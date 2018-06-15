package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.visa.checkout.CheckoutButton;
import com.visa.checkout.VisaCheckoutSdk;
import com.visa.checkout.VisaPaymentSummary;

import java.util.List;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.adapters.NovoAdapter;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.Produto_Tecido;

public class CheckoutActivity extends BaseActivity implements NovoAdapter.Myinterface {

    ListView list;
    TextView totalValorCheckout;
    NovoAdapter myAdapter;
    NovoAdapter.Myinterface listener;
    CheckBox checkBox;
    //Button btEfetuarPagamento;
    Produto_Tecido produtos = new Produto_Tecido();
    String nameEstampa;

    String TAG="CHECKOUT_ACTIVITY";
    int qdade;
    double valorTotal;
    private List<Produto_Tecido> mCart = getCart();//carrego List com os dados já salvos em cart

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        totalValorCheckout = findViewById(R.id.tvTotalCheckout);
        totalValorCheckout.setText(f.format(totalCart));

        valorTotal = totalCart;//se não for deletado itens...

        CheckoutButton checkoutButton = findViewById(R.id.visaCheckoutButton);
        checkoutButton.init(this, ConfigureVisaPaymentInfo.getProfile(),
                ConfigureVisaPaymentInfo.getPurchaseInfo(),
                new VisaCheckoutSdk.VisaCheckoutResultListener() {
                    @Override
                    public void onResult(VisaPaymentSummary visaPaymentSummary) {
                        if (VisaPaymentSummary.PAYMENT_SUCCESS.equalsIgnoreCase(
                                visaPaymentSummary.getStatusName())) {
                            Log.d(TAG, "Success");
                        } else if (VisaPaymentSummary.PAYMENT_CANCEL.equalsIgnoreCase(
                                visaPaymentSummary.getStatusName())) {
                            Log.d(TAG, "Canceled");
                        } else if (VisaPaymentSummary.PAYMENT_ERROR.equalsIgnoreCase(
                                visaPaymentSummary.getStatusName())) {
                            Log.d(TAG, "Error");
                        } else if (VisaPaymentSummary.PAYMENT_FAILURE.equalsIgnoreCase(
                                visaPaymentSummary.getStatusName())) {
                            Log.d(TAG, "Generic Unknown failure");
                        }
                    }
                });

       // btEfetuarPagamento = findViewById(R.id.btEfetuarPagamento);




        //startPagseguroLib("emanoel_oliveira@hotmail.com",
               // "B47679B87C564831B6049737174735BD",R.layout.activity_checkout,this);


        //myAdapter.atualizaTotal();

        list = findViewById(R.id.lvCheckout);


        myAdapter = new NovoAdapter(mCart, getLayoutInflater(), true, new NovoAdapter.Myinterface() {
            @Override
            public void atualizaValorTotal(double valor) {
                totalValorCheckout.setText(f.format(valor));
                valorTotal = valor;//se for deletado algum item o valorTotal será atualizado
            }
        });

        list.setAdapter(myAdapter);

//        btEfetuarPagamento.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(valorTotal>0){
//                    /* chamar dados pagamento acticity
//                    * passar dados para efetuar o pedido
//                    * salvar pedido
//                    * */
//
//                   // gerarBoleto();
//
//                    startActivity(new Intent(getApplicationContext(),PagamentoActivity.class));
//
//                }else Toast.makeText(getApplicationContext(),"Não há itens no pedido!!",Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    public void atualizaValorTotal(double valor){

        //this.totalValorCheckout = findViewById(R.id.tvTotalCheckout);

        //totalValorCheckout.setText(f.format(valor));
    }

//    public void gerarBoleto(){
//
//        PSBilletRequest psBilletRequest = new PSBilletRequest();
//        psBilletRequest
//                .setDocumentNumber("99404021040")
//                .setName("João da Silva")
//                .setEmail("joao.silva@teste.com")
//                .setAreaCode("34")
//                .setPhoneNumber("999508523")
//                .setStreet("Rua Tapajos")
//                .setAddressComplement("")
//                .setAddressNumber("23")
//                .setDistrict("Saraiva")
//                .setCity("Uberlândia")
//                .setState("MG")
//                .setCountry("BRA")
//                .setPostalCode("38408414")
//                .setTotalValue(50.00)
//                .setAmount(50.00)
//                .setDescriptionPayment("Pagamento do teste de integração")
//                .setQuantity(1);
//                //.setNottificationUrl("https://pagseguro.uol.com.br");
//
//        PSCheckout.generateBooklet(psBilletRequest, psBilletListener, this);
//
//        myToastCurto("boleto gerado!!");
//
//    }
//    private PSBilletListener psBilletListener = new PSBilletListener() {
//        @Override
//        public void onSuccess(PaymentResponseVO responseVO) {
//           String codigoBarras = responseVO.getBookletNumber();// - número do código de barras do boleto
//            String urlLink = responseVO.getPaymentLink();// - link para download do boleto
//
//            myToastCurto(codigoBarras);
//        }
//
//        @Override
//        public void onFailure(Exception e) {
//
//            myToastCurto(e.toString());
//
//            Log.e(TAG, "onFailure: "+ e.toString());
//            // Error
//        }
//
//        @Override
//        public void onProcessing() {
//            myToastCurto("processing...");
//            // Progress
//        }
//    };
//
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
