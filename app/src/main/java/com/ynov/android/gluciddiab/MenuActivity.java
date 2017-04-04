package com.ynov.android.gluciddiab;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.util.ArraySet;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ynov.android.gluciddiab.dataUtils.KfcContract;
import com.ynov.android.gluciddiab.dataUtils.KfcDbHelper;
import com.ynov.android.gluciddiab.dataUtils.McDoContract;
import com.ynov.android.gluciddiab.dataUtils.McDoDbHelper;
import com.ynov.android.gluciddiab.panierUtils.CustomListViewPanierArticle;
import com.ynov.android.gluciddiab.restoUtils.KfcImageAdapter;
import com.ynov.android.gluciddiab.restoUtils.McDoImageAdapter;

import java.util.ArrayList;
import java.util.Arrays;



/**
 * Created by admin on 01/04/17.
 */

public class MenuActivity extends AppCompatActivity {

    private Toolbar toolbar;

    Spinner dropdown;

    String[] liste = {
            "Tous produits",
            "Boissons",
            "Sandwiches",
            "Desserts",
            "Petit Dej",
            "Accompagnements"
    };

    private String[] fakedata = {
            "Coca Cola",
            "Deluxe Potatoes",
            "280 Original",
            "Cheese Burger",
            "Evian",
            "Petite Frites",
            "Truc au Poulet",
            "Ketchup",
            "Muffin myrtille",
            "280 Emmental",
            "Petit Hotdog",
            "Sundae",
            "Triple cheese"
    };

    // Panier

    TextView tvPanier;
    Button btnValid;
    ListView lvPanier;

    // gestion des popups
    //PopupWindow pw;
    //Button fabButton;

    // récup depuis restoActivity
    String mealTime;
    String restoChoice;

    final Context context = this;



    SQLiteDatabase mDb;
    String ItemNames;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        // Connection a la db

        Intent intentThatStartedThisActivity = getIntent();

        Bundle extras = intentThatStartedThisActivity.getExtras();
        mealTime = extras.getString("EXTRA_MEAL");
        restoChoice = extras.getString("EXTRA_RESTO");


        // Initializing ArrayLists for the following if/else statement
        final ArrayList<String> ArrayItemNames = new ArrayList<String>();
        final ArrayList<String> ArrayProtoLent = new ArrayList<String>();
        final ArrayList<String> ArrayProtoRapide = new ArrayList<String>();
        final ArrayList<String> ArrayCat = new ArrayList<String>();

        // GridView
        GridView gridview = (GridView) findViewById(R.id.gridviewItems);



        //
        //if KFC is selected
        //

        if(restoChoice.equals("KFC")){


            KfcDbHelper dbHelper = new KfcDbHelper(this);

            mDb = dbHelper.getReadableDatabase();

            Cursor cursor = getKfcItem();
            cursor.moveToFirst();

            //int i = 0;

            if (cursor.moveToFirst()) {
                do {
                    ArrayItemNames.add(cursor.getString(cursor.getColumnIndex(KfcContract.KFCListEntry.PRODUCT_NAME)));
                } while (cursor.moveToNext());
            }

            cursor.close();

            // connexion a protocole


            Cursor cursorProtoLent = getKfcItem();
            cursorProtoLent.moveToFirst();

            //int i = 0;;

            if (cursorProtoLent.moveToFirst()) {
                do {
                    ArrayProtoLent.add(cursorProtoLent.getString(cursorProtoLent.getColumnIndex(KfcContract.KFCListEntry.GLU_LENT)));
                } while (cursorProtoLent.moveToNext());
            }

            cursorProtoLent.close();

            Cursor cursorProtoRapide = getKfcItem();
            cursorProtoRapide.moveToFirst();

            //int i = 0;

            if (cursorProtoRapide.moveToFirst()) {
                do {
                    ArrayProtoRapide.add(cursorProtoRapide.getString(cursorProtoRapide.getColumnIndex(KfcContract.KFCListEntry.GLU_RAPIDE)));
                } while (cursorProtoRapide.moveToNext());
            }

            cursorProtoRapide.close();

            // cursor categories

            Cursor cursorCat = getKfcItem();
            cursorCat.moveToFirst();

            //int i = 0;

            if (cursorCat.moveToFirst()) {
                do {
                    ArrayCat.add(cursorCat.getString(cursorCat.getColumnIndex(KfcContract.KFCListEntry.CATEGORIE)));
                } while (cursorCat.moveToNext());
            }

            cursorCat.close();
            gridview.setAdapter(new KfcImageAdapter(this));
        }


        //
        //If MCDonald's is selected
        //

        else if(restoChoice.equals("McDo")) {



            McDoDbHelper dbHelper = new McDoDbHelper(this);

            mDb = dbHelper.getReadableDatabase();

            Cursor cursor = getMcDoItem();
            cursor.moveToFirst();

            //int i = 0;

            if (cursor.moveToFirst()) {
                do {
                    ArrayItemNames.add(cursor.getString(cursor.getColumnIndex(McDoContract.Entrees.PRODUCT_NAME)));
                } while (cursor.moveToNext());
            }

            cursor.close();

            // connexion a protocole


            Cursor cursorProtoLent = getMcDoItem();
            cursorProtoLent.moveToFirst();

            //int i = 0;;

            if (cursorProtoLent.moveToFirst()) {
                do {
                    ArrayProtoLent.add(cursorProtoLent.getString(cursorProtoLent.getColumnIndex(McDoContract.Entrees.GLU_LENT)));
                } while (cursorProtoLent.moveToNext());
            }

            cursorProtoLent.close();

            Cursor cursorProtoRapide = getMcDoItem();
            cursorProtoRapide.moveToFirst();

            //int i = 0;

            if (cursorProtoRapide.moveToFirst()) {
                do {
                    ArrayProtoRapide.add(cursorProtoRapide.getString(cursorProtoRapide.getColumnIndex(McDoContract.Entrees.GLU_RAPIDE)));
                } while (cursorProtoRapide.moveToNext());
            }

            cursorProtoRapide.close();

            // cursor categories

            Cursor cursorCat = getMcDoItem();
            cursorCat.moveToFirst();

            //int i = 0;

            if (cursorCat.moveToFirst()) {
                do {
                    ArrayCat.add(cursorCat.getString(cursorCat.getColumnIndex(McDoContract.Entrees.CATEGORIE)));
                } while (cursorCat.moveToNext());
            }

            cursorCat.close();
            gridview.setAdapter(new McDoImageAdapter(this));
        }

        ArraySet setCat = new ArraySet(ArrayCat);
        Object[] arraySet = setCat.toArray();
        String[] stringArray = Arrays.copyOf(arraySet, arraySet.length, String[].class);





        tvPanier = (TextView) findViewById(R.id.textPanier);
        btnValid = (Button) findViewById(R.id.buttonValidPanier);
        lvPanier = (ListView) findViewById(R.id.listViewPanier);
        dropdown = (Spinner) findViewById(R.id.spinner);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stringArray);
        dropdown.setAdapter(adapter);


        final ArrayList<String> ArrayPanier = new ArrayList<String>();
        final ArrayList<String> ArrayGluLent = new ArrayList<String>();
        final ArrayList<String> ArrayGluRapide = new ArrayList<String>();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                Toast.makeText(MenuActivity.this, mealTime + " " + restoChoice,
                        Toast.LENGTH_SHORT).show();
                //tvPanier.append("\n" + "1x" + fakedata[position]);

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_menu);
                dialog.setTitle("Voulez vous ce produit?");

                TextView text = (TextView) dialog.findViewById(R.id.DialogItem);
                text.setText(ArrayItemNames.get(position));

                TextView tvGluLent = (TextView) dialog.findViewById(R.id.DialogGluLent);
                tvGluLent.setText("Glu Lent: " + ArrayProtoLent.get(position));

                TextView tvGluRapide = (TextView) dialog.findViewById(R.id.DialogGluRapide);
                tvGluRapide.setText("Glu Rapide: " + ArrayProtoRapide.get(position));

                ImageView image = (ImageView) dialog.findViewById(R.id.DialogImage);
                image.setImageResource(R.drawable.mcdodoublecheese);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ArrayPanier.add(ArrayItemNames.get(position));

                        ArrayGluLent.add(ArrayProtoLent.get(position));

                        ArrayGluRapide.add(ArrayProtoRapide.get(position));

                        //menuListAdapter adapter = new menuListAdapter(MenuActivity.this, ArrayPanier);

                        CustomListViewPanierArticle adapter = new
                                CustomListViewPanierArticle(MenuActivity.this,ArrayPanier);

                        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MenuActivity.this, android.R.layout.simple_list_item_1, ArrayPanier);

                        lvPanier.setAdapter(adapter);
                        dialog.dismiss();
                    }
                });

                Button dialogButtonNo = (Button) dialog.findViewById(R.id.dialogButtonNon);
                // if button is clicked, close the custom dialog
                dialogButtonNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                dialog.show();


            }
        });

        final Context context = MenuActivity.this;

        btnValid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Class destinationActivity = PanierActivity.class;

                Intent startPanierActivityIntent = new Intent(context, destinationActivity);

                Bundle extras = new Bundle();

                extras.putString("EXTRA_MEAL", mealTime);
                extras.putStringArrayList("EXTRA_MENU", ArrayPanier);
                extras.putStringArrayList("EXTRA_GLULENT", ArrayGluLent);
                extras.putStringArrayList("EXTRA_GLURAPIDE", ArrayGluRapide);


                startPanierActivityIntent.putExtras(extras);

                startActivity(startPanierActivityIntent);


            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private Cursor getMcDoItem() {
        // COMPLETED (6) Inside, call query on mDb passing in the table name and projection String [] order by COLUMN_TIMESTAMP
        return mDb.query(
                McDoContract.Entrees.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                McDoContract.Entrees.COLUMN_TIMESTAMP
        );
    }

    private Cursor getKfcItem() {
        // COMPLETED (6) Inside, call query on mDb passing in the table name and projection String [] order by COLUMN_TIMESTAMP
        return mDb.query(
                KfcContract.KFCListEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                KfcContract.KFCListEntry.COLUMN_TIMESTAMP
        );
    }



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Menu Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    /*private void initiatePopupWindow(View v) {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) MenuActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.activity_menu,
                    (RelativeLayout) findViewById(R.id.menu_layout));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            // display the popup in the center
            pw.showAtLocation(v, Gravity.CENTER, 0, 0);
            TextView mResultText = (TextView) layout.findViewById(R.id.server_status_text);
            Button cancelButton = (Button) layout.findViewById(R.id.end_data_send_button);
            cancelButton.setOnClickListener(cancel_button_click_listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    //private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
    //    public void onClick(View v) {
    //        pw.dismiss();
    //    }
    //};
}