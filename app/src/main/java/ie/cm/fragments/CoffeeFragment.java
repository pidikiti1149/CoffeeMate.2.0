package ie.cm.fragments;

import ie.cm.activities.Base;
import ie.cm.activities.Edit;
import ie.cm.adapters.CoffeeListAdapter;
import ie.cm.models.Coffee;
import ie.cm.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;

import java.io.Serializable;

import javax.sql.StatementEvent;

public class CoffeeFragment  extends ListFragment implements  OnClickListener
{ 
  protected         Base                activity;
  protected static  CoffeeListAdapter 	listAdapter;
  protected         ListView 			listView;


  public CoffeeFragment() {
    // Required empty public constructor
  }

  public static CoffeeFragment newInstance() {

    CoffeeFragment fragment = new CoffeeFragment();
    return fragment;
  }

@Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    this.activity = (Base) context;
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);

    listAdapter = new CoffeeListAdapter(activity, this, Base.coffeeList);
    setListAdapter (listAdapter);
  }
     
  @Override
  public void onStart()
  {
    super.onStart();
  }

  @Override
  public void onClick(View view)
  {
    if (view.getTag() instanceof Coffee)
    {
      onCoffeeDelete ((Coffee) view.getTag());

    }
  }

  private void onCoffeeDelete(final Coffee coffee)
  {

    String stringName = coffee.name;

    AlertDialog.Builder builder = new AlertDialog.Builder(activity);

    builder.setMessage("Are you sure you want to Delete the \'Coffee\' " + stringName + "?");

    builder.setCancelable(false);

    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

      public void onClick(DialogInterface dialog, int id) {

        Base.coffeeList.remove(coffee);
        listAdapter.coffeeList.remove(coffee);
        listAdapter.notifyDataSetChanged();

      }
    }).setNegativeButton("No", new DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialog, int id) {

        //dialog.cancel();

          Base.coffeeList.remove(coffee);
          listAdapter.coffeeList.remove(coffee);
          listAdapter.notifyDataSetChanged();

      }
    });
        AlertDialog alert = builder.create();
        alert.show();
  }


  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {

    Bundle activityInfo = new Bundle();

    //activityInfo.putInt("coffeeID",/* pass in the coffees id here */);

    Intent goEdit = new Intent(getActivity(), Edit.class);

    getActivity().startActivity(goEdit);

  }



}


  