package net.pside.android.example.mostpowerfulorminandroid.library;

public interface IOrmTestCase {
    int NUMBER_OF_INSERT_SINGLE = 10000;

    String MSG_LOGGER_INITIALIZE_BULK_ON = "Insert (BulkMode: ON)";
    String MSG_LOGGER_INITIALIZE_BULK_OFF = "Insert (BulkMode: OFF)";

    String MSG_LOGGER_SPLIT_INSERT = "Insert " + NUMBER_OF_INSERT_SINGLE + " records.";

    String MSG_LOGGER_SPLIT_SELECT = "Select Records.";

    String getDatabaseName();

    void testSingleInsert() throws Exception;

    void testSingleBulkInsert() throws Exception;

}
