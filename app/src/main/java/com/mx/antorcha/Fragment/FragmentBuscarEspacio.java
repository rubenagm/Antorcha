package com.mx.antorcha.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.R;

/**
 * Created by Ruben on 15/12/2015.
 */
public class FragmentBuscarEspacio extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Activity activity;
    private static final int ERROR_DIALOG_REQUEST = 1 ;
    GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location location;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGoogleApiClient();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buscar_espacio, container, false);

        //Se cargan las imagenes de los icono de contacto
        ImageView imageViewCompartir = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_compartir);
        ImageView imageViewContacto = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_contacto);
        ImageView imageViewLapiz = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_lapiz);

        AdaptadorSVG.mostrarImagen(imageViewCompartir, activity, R.raw.icono_compartir);
        AdaptadorSVG.mostrarImagen(imageViewContacto, activity, R.raw.icono_llamada);
        AdaptadorSVG.mostrarImagen(imageViewLapiz, activity, R.raw.icono_lapiz);
        return rootView;


    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public synchronized void buildGoogleApiClient(){
    mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build();
        }

    private void getLocation(double lat, double lng, float zoom){
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng,zoom);
        mMap.moveCamera(update);
    }

    private boolean checkServices() {
        int EsDisponible= GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        //Si la coneccion esta Disponible
        if (EsDisponible== ConnectionResult.SUCCESS){
            return true;
        }else if (GooglePlayServicesUtil.isUserRecoverableError(EsDisponible)){
            Dialog dialog=GooglePlayServicesUtil.getErrorDialog(EsDisponible,getActivity(),ERROR_DIALOG_REQUEST);
            dialog.show();
        }else {
            //Toast.makeText(FragmentBuscarEspacio.this, "Cannot connnect to mapping Service", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
//Inicializa el Mapa
    private boolean initMap() {
        if (mMap == null) {
            SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment_espacio);
            mMap=mapFragment.getMap();
        }
        return (mMap!=null);
    }

    @Override
    public void onConnected(Bundle bundle) {
        location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location != null) {
            //Latitud
            double latitudeValue=location.getLatitude();
            //Longitud
            double longitudeValue=location.getLongitude();
            if(checkServices()){
                if(initMap()){
                    //Actualiza el mapa con la ubicacion actual
                    getLocation(latitudeValue, longitudeValue, 15);
                    //Tipo de mapa
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    mMap.setMyLocationEnabled(true);

                    //Configuracion de los Markers
                    MarkerOptions marker= new MarkerOptions()
                            .title("Dharmapuri")
                            .position(new LatLng(latitudeValue,longitudeValue))
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    mMap.addMarker(marker);
                }
            }
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("GettingLocation", "onConnectionFailed");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("GettingLocation", "onConnectionFailed");
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}
