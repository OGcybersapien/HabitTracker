package xyz.cybersapien.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import xyz.cybersapien.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by ogcybersapien on 13/10/16.
 *
 * Database helper for the Habits Db.
 * Manages database creation, version management(not needed as of now though)
 * and adding/removing a row from the table
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    /*Log tag for the Activity to be used for error detection/removal */
    public static final String LOG_TAG = HabitDbHelper.class.getName();

    /* Name of the Database file*/
    private static final String DATABASE_NAME = "habits.db";

    /* Database version. to be used in case we have to change the database schema.*/
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of HabitDbHelper Object
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time.
     * Creates a table for the habits
     * @param db the database we are dealing with.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a String that contains the SQL statement to create the Habits table
        StringBuilder habitStringBuilder;
        habitStringBuilder = new StringBuilder("CREATE TABLE ");
        habitStringBuilder.append(HabitEntry.TABLE_NAME + " (")
                .append(HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, ")
                .append(HabitEntry.COLUMN_HABIT_MONDAY + " INTEGER NOT NULL DEFAULT 0, ")
                .append(HabitEntry.COLUMN_HABIT_TUESDAY + " INTEGER NOT NULL DEFAULT 0, ")
                .append(HabitEntry.COLUMN_HABIT_WEDNESDAY + " INTEGER NOT NULL DEFAULT 0, ")
                .append(HabitEntry.COLUMN_HABIT_THURSDAY + " INTEGER NOT NULL DEFAULT 0, ")
                .append(HabitEntry.COLUMN_HABIT_FRIDAY + " INTEGER NOT NULL DEFAULT 0, ")
                .append(HabitEntry.COLUMN_HABIT_SATURDAY + " INTEGER NOT NULL DEFAULT 0, ")
                .append(HabitEntry.COLUMN_HABIT_SUNDAY + " INTEGER NOT NULL DEFAULT 0, ")
                .append(HabitEntry.COLUMN_HABIT_WEEKS + " INTEGER NOT NULL DEFAULT 0);");

        db.execSQL(habitStringBuilder.toString());

    }

    /**
     * Needed only when there is an update to the database, such as addition/removal of a column or a table
     * Not needed for now.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Database still in v1, so nothing to be done here.
    }

    /**
     * Method to add a new habit to the table
     */
    public long insertHabit(SQLiteDatabase db, String habitName,
                            boolean days[], int weeks){

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, habitName);

        return db.insert(HabitEntry.TABLE_NAME, null, values);
    }
}
