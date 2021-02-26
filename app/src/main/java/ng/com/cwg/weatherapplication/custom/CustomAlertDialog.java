package ng.com.cwg.weatherapplication.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import ng.com.cwg.weatherapplication.R;


/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 27/04/2020 20:48
 */
public class CustomAlertDialog {
   Context context;
   AlertDialog dialog;
   Boolean cancelable = false;
    public CustomAlertDialog(Context context ) {
        this.context = context;
    }
    public void init(int layout, OnDialogueSet onDialogueSet, Boolean cancelable, Boolean fullscreen){
        this.cancelable = cancelable;
        AlertDialog.Builder alertDialog;
        if (fullscreen) {
           alertDialog = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        }
        else {
          alertDialog =  new AlertDialog.Builder(context);
        }

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(layout,null);
        alertDialog.setView(dialogView);
        onDialogueSet.setUpView(dialogView);

        dialog = alertDialog.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        if (dialog.getWindow() != null)
            dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        dialog.show();
        dialog.setCancelable(this.cancelable);
    }
    public interface OnDialogueSet{
        void setUpView(View view);
    }
    public void close(){
        dialog.dismiss();
    }
    public void forceClose(){
        if (!cancelable)
        dialog.setCancelable(true);
        close();
    }
}
