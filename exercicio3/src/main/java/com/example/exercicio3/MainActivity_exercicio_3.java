package com.example.exercicio3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class CorreiosService {

    public CorreiosService() throws Exception {
    }

    public static Endereco buscarEnderecoPorCep(String cep) throws Exception {
        URL url = new URL("https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/xml");

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cli=\"http://cliente.bean.master.sigep.bsb.correios.com.br/\"><soapenv:Header/><soapenv:Body><cli:consultaCEP><cep>" + cep + "</cep></cli:consultaCEP></soapenv:Body></soapenv:Envelope>";

        con.setDoOutput(true);
        con.getOutputStream().write(xml.getBytes());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        Serializer serializer = new Persister();
        Endereco endereco = serializer.read(Endereco.class, response.toString());

        return endereco;
    }

    CorreiosService correiosService = new CorreiosService();
    Endereco endereco = correiosService.buscarEnderecoPorCep("01001000");


}
