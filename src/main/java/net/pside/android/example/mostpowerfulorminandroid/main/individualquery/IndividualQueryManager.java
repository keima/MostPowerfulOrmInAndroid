/*
 * IndividualQueryManager.java
 *
 * Generated on: 11/27/2015 02:30:54
 *
 */



package net.pside.android.example.mostpowerfulorminandroid.main.individualquery;


@javax.inject.Singleton
public class IndividualQueryManager extends IndividualQueryBaseManager {


    @javax.inject.Inject
    public IndividualQueryManager() {
    }

    @Override
    public String getQuery() {
        return IndividualQuery.QUERY;
    }


}