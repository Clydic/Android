package modele;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class ConfirmationDialog  extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the Builder clss for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        builder.setMessage("Voulez-vous supprimer ce sort?")
                .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isCancelable();
                    }
                });

        return null;
    }
}
