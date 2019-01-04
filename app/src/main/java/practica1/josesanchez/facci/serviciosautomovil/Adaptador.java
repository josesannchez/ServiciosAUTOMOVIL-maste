package practica1.josesanchez.facci.serviciosautomovil;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Adaptador extends BaseAdapter {
    private static LayoutInflater inflater = null;

    Context contexto;
    String[][] datos;
    int[] datosImg;

    public Adaptador(Context conexto, String[][] datos, int[] imagenes)
    {
        this.contexto = conexto;
        this.datos = datos;
        this.datosImg = imagenes;

        inflater = (LayoutInflater)conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista, null);

        TextView titulo = (TextView) vista.findViewById(R.id.tvNombre);
        TextView duracion = (TextView) vista.findViewById(R.id.tvPrecio);
        TextView director = (TextView) vista.findViewById(R.id.tvModelo);

        ImageView imagen = (ImageView) vista.findViewById(R.id.ivImagen);


        titulo.setText(datos[i][0]);
        director.setText(datos[i][1]);
        duracion.setText("Precio " + datos[i][2]);
        imagen.setImageResource(datosImg[i]);


        imagen.setTag(i);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visorImagen = new Intent(contexto, VisorImagen.class);
                visorImagen.putExtra("IMG", datosImg[(Integer)v.getTag()]);
                contexto.startActivity(visorImagen);
            }
        });


        return vista;
    }

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
