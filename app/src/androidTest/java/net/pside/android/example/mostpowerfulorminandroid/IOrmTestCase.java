package net.pside.android.example.mostpowerfulorminandroid;

/**
 * Created by keima on 14/11/22.
 */
public interface IOrmTestCase {
    int NUMBER_OF_INSERT_SINGLE = 10000;

    String MSG_LOGGER_SPLIT_INSERT = "Insert " + NUMBER_OF_INSERT_SINGLE + " records.";

    String MSG_LOGGER_SPLIT_SELECT = "Select Records.";

    String getDatabaseName();

    void testSingleInsert();

    void testSingleBulkInsert();

}
