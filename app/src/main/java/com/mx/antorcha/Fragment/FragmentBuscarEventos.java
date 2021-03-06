package com.mx.antorcha.Fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ruben on 15/12/2015.
 */
public class FragmentBuscarEventos extends Fragment  implements GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener{

    private Activity activity;
    GoogleMap mMap;
    MapView mapView;
    static final LatLng Cucei = new LatLng(20.657588, -103.325770);

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buscar_evento, container, false);

        //Se cargan las imagenes de los icono de contacto
        ImageView imageViewCompartir = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_evento_compartir);
        ImageView imageViewContacto = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_evento_contacto);

        AdaptadorSVG.mostrarImagen(imageViewCompartir, activity, R.raw.icono_compartir);
        AdaptadorSVG.mostrarImagen(imageViewContacto, activity, R.raw.icono_llamada);

        //Inicializar el mapa
        mapView = (MapView) rootView.findViewById(R.id.map_fragment_evento);
        mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(getContext());
        mMap = mapView.getMap();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Ubicacion en el mapa
        mMap.setMyLocationEnabled(true);
        mMap.getMyLocation();

        //Agregar Marcadores
        addMarker();

        return rootView;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onMapLongClick(LatLng puntoPulsado) {
        /*mMap.addMarker(new MarkerOptions().title("Marcador").position(puntoPulsado).
                icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)));*/
    }

    //Onclick del Marcador
    @Override
    public boolean onMarkerClick(Marker marker){
        marker.showInfoWindow();
        MarkerOptions markerOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        return true;
    }
    @Override
    public void onMarkerDrag(Marker marker){

    }
    @Override
    public void onMarkerDragEnd(Marker marker){
        //Actualiza la informacion del Marcador
        marker.setSnippet(getCity(marker.getPosition()));
        marker.showInfoWindow();
    }
    @Override
    public void onMarkerDragStart(Marker marker){

    }

    public String getCity(LatLng posicion){
        Geocoder geocoder;
        List<Address> addresses;
        String ciudad ="";
        geocoder = new Geocoder(getContext(), Locale.getDefault());
        try{
            addresses = geocoder.getFromLocation(posicion.latitude, posicion.longitude,1);
            ciudad = addresses.get(0).getAddressLine(3);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return ciudad;
    }

    public void addMarker(){
        Bitmap bitmap = null;
        mMap.addMarker(new MarkerOptions()
                .position(Cucei)
                .title("Marcador")
                .snippet(getCity(Cucei))
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

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
}
