package net.pside.android.example.mostpowerfulorminandroid;

/**
 * Created by keima on 14/11/22.
 */
public interface IOrmTestCase {
    public static final int NUMBER_OF_INSERT_SINGLE = 10000;

    public void testSingleInsert();
    public void testSingleBulkInsert();

}
