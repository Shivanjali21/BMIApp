package com.practice.bmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ContactDB";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_CONTACT = "contacts";
    private static final String KEY_ID = "id";
    private static final String Key_NAME = "name";
    private static final String Key_Mobile_No = "mobile_no";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE " + TABLE_CONTACT +
              "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
              Key_NAME + " TEXT, " +
              Key_Mobile_No + " TEXT"+ ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
      onCreate(db);
    }

    public void addContact(String name, String mobile) {
      SQLiteDatabase database = this.getWritableDatabase();

      ContentValues contentValues = new ContentValues();
      contentValues.put(Key_NAME, name);
      contentValues.put(Key_Mobile_No, mobile);
      database.insert(TABLE_CONTACT, null, contentValues);
    }

    public ArrayList<ContactModel> fetchContact() {
      SQLiteDatabase database = this.getReadableDatabase();

      Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_CONTACT,null);
      ArrayList<ContactModel> arrContact = new ArrayList<>();
      while (cursor.moveToNext()){
        ContactModel contactModel = new ContactModel();
        contactModel.id = cursor.getInt(0);
        contactModel.name = cursor.getString(1);
        contactModel.mobile_no = cursor.getString(2);

        arrContact.add(contactModel);
      }
      cursor.close();
      return arrContact;
    }

    public void updateContact(ContactModel contactModel){
      SQLiteDatabase database = this.getWritableDatabase();
      ContentValues cv = new ContentValues();
      cv.put(Key_Mobile_No, contactModel.mobile_no);
      database.update(TABLE_CONTACT, cv,KEY_ID + " = " + contactModel.id, null);
    }

    public void deleteContact(int id){
      SQLiteDatabase database = this.getWritableDatabase();
      database.delete(TABLE_CONTACT, KEY_ID+ " = ? ", new String[]{String.valueOf(id)});
    }
}
