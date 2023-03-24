package com.example.exercicio3;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    private EditText cepEditText;
    private Button consultarButton;
    private TextView resultadoTextView;

    private static final String URL = "https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente";
    private static final String NAMESPACE = "http://cliente.bean.master.sigep.bsb.correios.com.br/";
    private static final String METHOD_NAME = "consultaCEP";
    private static final String SOAP_ACTION = NAMESPACE + METHOD_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.menu.activitymain);

        cepEditText = findViewById(R.id.cepEditText);
        consultarButton = findViewById(R.id.consultarButton);
        resultadoTextView = findViewById(R.id.resultadoTextView);

        consultarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cep = cepEditText.getText().toString();
                new ConsultaCEPTask().execute(cep);
            }
        });
    }

    private class ConsultaCEPTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String cep = params[0];
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("cep", cep);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(URL);
            transport.debug = true;

            try {
                transport.call(SOAP_ACTION, envelope);
                SoapObject response = (SoapObject) envelope.getResponse();
                String logradouro = response.getPropertyAsString("end");
                String bairro = response.getPropertyAsString("bairro");
                String cidade = response.getPropertyAsString("cidade");
                String estado = response.getPropertyAsString("uf");

                return String.format("%s, %s - %s/%s", logradouro, bairro, cidade, estado);
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao consultar CEP: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            resultadoTextView.setText(result);
        }
    }
}

