package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.visa.checkout.CheckoutButton;
import com.visa.checkout.VisaCheckoutSdk;
import com.visa.checkout.VisaPaymentSummary;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PagamentoActivity extends BaseActivity {


    private static final String TAG = PagamentoActivity.class.getSimpleName();
    @BindView(R.id.spBandeira)
    Spinner spBandeira;
    @BindView(R.id.etNomeTitularCartao)
    EditText etNomeTitularCartao;
    @BindView(R.id.etNumeroCartao)
    EditText etNumeroCartao;
    @BindView(R.id.etCVV)
    EditText etCVV;
    @BindView(R.id.spMes)
    Spinner spMes;
    @BindView(R.id.spAno)
    Spinner spAno;
    //@BindView(R.id.visaCheckoutButton)
   // Button visaCheckoutButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        ButterKnife.bind(this);




        spBandeira.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spAno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


//    @OnClick(R.id.visaCheckoutButton)
//    public void onVisaCheckoutButtonClicked() {
//    }

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


}
