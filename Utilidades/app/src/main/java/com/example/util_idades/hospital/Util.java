package com.example.util_idades.hospital;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.core.app.ActivityCompat;

public class Util {
    public static void solicitarPermiso(final String permiso, String justificacion,
                                        final int requestCode, final Activity context){
        if(ActivityCompat.shouldShowRequestPermissionRationale(context,permiso)){
            new AlertDialog.Builder(context)
                    .setTitle("Solicitud de permiso de ubicación")
                    .setMessage(justificacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(context,new String[]{permiso},requestCode);
                        }
                    }).show();
        }else{
            ActivityCompat.requestPermissions(context,new String[]{permiso},requestCode);
        }
    }
}
