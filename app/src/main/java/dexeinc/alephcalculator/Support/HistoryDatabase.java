package dexeinc.alephcalculator.Support;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "operation_history.db";
    private static final String TB_NAME = "history";
    private static final String OP = "OP";
    private static final String RS = "RS";

    public HistoryDatabase(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TB_NAME + " ("+ OP + " TEXT, " + RS + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
    }

    public void addOperation(String operation, String result) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OP, operation);
        contentValues.put(RS, result);
        db.insert(TB_NAME, null, contentValues);
    }

    public Cursor getOperations() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TB_NAME, null);
    }

    public void deleteOperations() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TB_NAME);
    }
}
