package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import br.com.emanoel.oliveira.sextodecoracoes.R;

public class PagamentoActivity extends BaseActivity implements PayFragment.OnFragmentInteractionListener{

//    @BindView(R.id.etBandeira)
//    EditText etBandeira;
//    @BindView(R.id.spBandeira)
//    Spinner spBandeira;
//    @BindView(R.id.etNomeTitularCartao)
//    EditText etNomeTitularCartao;
//    @BindView(R.id.etNumeroCartao)
//    EditText etNumeroCartao;
//    @BindView(R.id.etExpira)
//    EditText etExpira;
//    @BindView(R.id.etCVV)
//    EditText etCVV;
//    @BindView(R.id.wallet_payment_button)
//    PaymentButton walletPaymentButton;
//    AppCompatActivity appCompatActivity = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        //ButterKnife.bind(this);

        Fragment fragment = new PayFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();







//        //Inicialização a lib com parametros necessarios
//       PSCheckoutConfig psCheckoutConfig = new PSCheckoutConfig();
//        psCheckoutConfig.setSellerEmail("emanoel_oliveira@hotmail.com");
//        psCheckoutConfig.setSellerToken("B47679B87C564831B6049737174735BD");
//        //Informe o fragment container
//        //psCheckoutConfig.setContainer(this);
//
//        //Inicializa apenas os recursos de pagamento transparente e boleto
//
//        PSCheckout.init(appCompatActivity, psCheckoutConfig);
//
//        //Caso queira inicializar todos os recursos da lib
//        //PSCheckout.init(getActivity(), psCheckoutConfig);



    }

//
//    @OnClick(R.id.wallet_payment_button)
//    public void onWalletPaymentButtonClicked() {

        /* VALIDAR DADOS
        * INICIALIZAR LIB
        * ENVIAR SOLICITAÇÃO PARA O PAGSEGURO
        * AGUARDAR RESPOSTA
        * SE RESPOSTA POSITIVA SALVAR PEDIDO
        * MOSTRAR RESULTADO AO CLIENTE
        * ZERAR LISTAS E CARRINHO*/
//        try {
//            pay("5155901297714587", "06/2023", "920");
//        } catch (Exception erro) {
//
//            Log.e(TAG, "pay metodo " + erro.toString());
//        }


   // }

//    private void pay(String number, String expiry, String cvv) {
//        PSTransparentDefaultRequest psT = new PSTransparentDefaultRequest();
//        //PSCheckoutRequest psCheckoutRequest = new PSCheckoutRequest();
//
//
//        //NUMERO DO CARTAO
//        psT.setCreditCard(number);
//        //CVV DO CARTAO
//        psT.setCvv(cvv);
//        //MÊS DE EXPIRACAO (Ex: 03)
//        psT.setExpMonth(expiry.substring(0, 2));
//        //ANO DE EXPIRACAO, ULTIMOS 2 DIGITOS (Ex: 17)
//        psT.setExpYear(expiry.substring(3, 5));
//        //VALOR DA TRANSACAO
//        psT.setTotalValue("100.00");
//        //DESCRICAO DO PRODUTO/SERVICO
//        psT.setDescriptionPayment("product.getName()");
//        psT.setCreditCardName(etBandeira.getText().toString());
//
//        psT.setNottificationUrl("https://sandbox.pagseguro.uol.com.br");
//
//
//        br.com.uol.pslibs.checkout_in_app.PSCheckout.payTransparentDefault(psT, (PSCheckoutListener) psCheckoutListener, appCompatActivity);
//
//        // PSCheckout.pay(psCheckoutRequest, psCheckoutListener, (AppCompatActivity) getParent());
//    }
//
//    private PSCheckout.PSCheckoutListener psCheckoutListener = new PSCheckout.PSCheckoutListener() {
//        @Override
//        public void onSuccess(PSCheckoutResponse responseVO) {
//           // mProgressDialog.dismiss();
//            //callResult(true, responseVO.getMessage());
//            myToastlongo("Success: " + responseVO.getMessage());
//        }
//
//        @Override
//        public void onFailure(PSCheckoutResponse responseVO) {
//            //mProgressDialog.dismiss();
//            //callResult(false, responseVO.getMessage());
//            myToastlongo("Fail: " + responseVO.getMessage());
//        }
//
//        @Override
//        public void onProcessing() {
////            mProgressDialog.setMessage("Realizando pagamento...");
////            mProgressDialog.setIndeterminate(true);
////            mProgressDialog.setCancelable(false);
////            mProgressDialog.show();
//            myToastlongo("processing");
//        }
//    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
