package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.uol.pslibs.checkout_in_app.PSCheckout;
import br.com.uol.pslibs.checkout_in_app.transparent.listener.PSCheckoutListener;
import br.com.uol.pslibs.checkout_in_app.transparent.vo.PSTransparentDefaultRequest;
import br.com.uol.pslibs.checkout_in_app.wallet.view.components.PaymentButton;
import br.com.uol.pslibs.checkouttransparent.vo.PSCheckoutResponse;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.etBandeira)
    EditText etBandeira;
    @BindView(R.id.spBandeira)
    Spinner spBandeira;
    @BindView(R.id.etNomeTitularCartao)
    EditText etNomeTitularCartao;
    @BindView(R.id.etNumeroCartao)
    EditText etNumeroCartao;
    @BindView(R.id.etExpira)
    EditText etExpira;
    @BindView(R.id.etCVV)
    EditText etCVV;
    @BindView(R.id.wallet_payment_button)
    PaymentButton walletPaymentButton;
    Unbinder unbinder;
    String TAG = "PAGAMENTO_ACTIVITY";
    AppCompatActivity activity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public PayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PayFragment newInstance(String param1, String param2) {
        PayFragment fragment = new PayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


//        try {
//            startPagseguroLib("emanoel_oliveira@hotmail.com",
//                    "B47679B87C564831B6049737174735BD", getId(), getActivity());
//        } catch (Exception erro) {
//
//            Log.e(TAG, "startPagseguroLib " + erro.toString());
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.wallet_payment_button)
    public void onViewClicked() {

        try {
            pay("5155901297714587", "06/2023", "920");
        } catch (Exception erro) {

            Log.e(TAG, "pay metodo " + erro.toString());
        }
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

    private void pay(String number, String expiry, String cvv) {
        PSTransparentDefaultRequest psT = new PSTransparentDefaultRequest();
        //PSCheckoutRequest psCheckoutRequest = new PSCheckoutRequest();


        //NUMERO DO CARTAO
        psT.setCreditCard(number);
        //CVV DO CARTAO
        psT.setCvv(cvv);
        //MÊS DE EXPIRACAO (Ex: 03)
        psT.setExpMonth(expiry.substring(0, 2));
        //ANO DE EXPIRACAO, ULTIMOS 2 DIGITOS (Ex: 17)
        psT.setExpYear(expiry.substring(3, 5));
        //VALOR DA TRANSACAO
        psT.setTotalValue("100.00");
        //DESCRICAO DO PRODUTO/SERVICO
        psT.setDescriptionPayment("product.getName()");
        psT.setCreditCardName(etBandeira.getText().toString());

        psT.setNottificationUrl("https://sandbox.pagseguro.uol.com.br");


        PSCheckout.payTransparentDefault(psT, (PSCheckoutListener) psCheckoutListener, activity);

        // PSCheckout.pay(psCheckoutRequest, psCheckoutListener, (AppCompatActivity) getParent());
    }

    private br.com.uol.pslibs.checkouttransparent.PSCheckout.PSCheckoutListener psCheckoutListener = new br.com.uol.pslibs.checkouttransparent.PSCheckout.PSCheckoutListener() {
        @Override
        public void onSuccess(PSCheckoutResponse responseVO) {
            // mProgressDialog.dismiss();
            //callResult(true, responseVO.getMessage());
            myToastlongo("Success: " + responseVO.getMessage());
        }

        @Override
        public void onFailure(PSCheckoutResponse responseVO) {
            //mProgressDialog.dismiss();
            //callResult(false, responseVO.getMessage());
            myToastlongo("Fail: " + responseVO.getMessage());
        }

        @Override
        public void onProcessing() {
//            mProgressDialog.setMessage("Realizando pagamento...");
//            mProgressDialog.setIndeterminate(true);
//            mProgressDialog.setCancelable(false);
//            mProgressDialog.show();
            myToastlongo("processing");
        }
    };

    public void myToastlongo(String mensagem) {

        Toast.makeText(getActivity(), mensagem, Toast.LENGTH_LONG).show();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    static class ViewHolder {
        @BindView(R.id.etBandeira)
        EditText etBandeira;
        @BindView(R.id.spBandeira)
        Spinner spBandeira;
        @BindView(R.id.etNomeTitularCartao)
        EditText etNomeTitularCartao;
        @BindView(R.id.etNumeroCartao)
        EditText etNumeroCartao;
        @BindView(R.id.etExpira)
        EditText etExpira;
        @BindView(R.id.etCVV)
        EditText etCVV;




        ViewHolder(View view) {
            ButterKnife.bind(this, view);


            etBandeira.setInputType(0);//desabilita digitação

            spBandeira.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    etBandeira.setText(spBandeira.getSelectedItem().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                    etBandeira.setText(spBandeira.getSelectedItem().toString());

                }
            });
        }


    }
}
