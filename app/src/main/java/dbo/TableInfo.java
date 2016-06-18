package dbo;

import android.provider.BaseColumns;

/**
 * Created by Batman on 2/21/16.
 */
public class TableInfo {

        public TableInfo(){

        }

public static abstract class TableDetails implements BaseColumns {
    public static final String EVENT_NAME = "event_name";
    public static final String EVENT_DATE = "event_date";
    public static final String TABLE_NAME = "events";
    public static final String DATABASE_NAME = "common_database";

}
}
