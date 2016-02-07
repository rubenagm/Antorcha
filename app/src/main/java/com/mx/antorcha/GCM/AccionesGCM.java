package com.mx.antorcha.GCM;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Luis on 05/02/2016.
 */
public class AccionesGCM {

    static public void mensajeRecibido (String mensaje) {
        try {
            JSONObject jsonObject = new JSONObject(mensaje);

            switch (jsonObject.getString("accion")) {
                case "medalla" : {
                    medallaRecibida(jsonObject.getInt("identificador"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static public void medallaRecibida(int id){


    }
}
