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
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.R;

import javax.security.auth.callback.Callback;

/**
 * Created by Ruben on 15/12/2015.
 */
public class FragmentBuscarEspacio extends Fragment {

    private Activity activity;
    private static final int ERROR_DIALOG_REQUEST = 1 ;
    GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    MapView mapView;
    Location location;
    OnMapReadyCallback callback;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buscar_espacio, container, false);

        //Se cargan las imagenes de los icono de contacto
        ImageView imageViewCompartir = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_compartir);
        ImageView imageViewContacto = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_contacto);
        ImageView imageViewLapiz = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_lapiz);

        mapView = (MapView) rootView.findViewById(R.id.map_fragment_espacio);
        mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(getContext());

       mMap = mapView.getMap();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);

        addMarkers();

        AdaptadorSVG.mostrarImagen(imageViewCompartir, activity, R.raw.icono_compartir);
        AdaptadorSVG.mostrarImagen(imageViewContacto, activity, R.raw.icono_llamada);
        AdaptadorSVG.mostrarImagen(imageViewLapiz, activity, R.raw.icono_lapiz);
        return rootView;



    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }

    public void addMarkers()
    {
        double latitudeValue=location.getLatitude();
        //getting the longitude value
        double longitudeValue=location.getLongitude();
        MarkerOptions markerOptions = new MarkerOptions()
                .title("Marker")
                .position(new LatLng(latitudeValue, longitudeValue)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(markerOptions);
    }


}
