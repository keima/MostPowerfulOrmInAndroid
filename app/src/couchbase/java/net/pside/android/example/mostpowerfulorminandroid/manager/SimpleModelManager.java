package net.pside.android.example.mostpowerfulorminandroid.manager;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;

import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by keima on 14/11/20.
 */
public class SimpleModelManager {

    private Context mContext;

    public SimpleModelManager(Context context) {
        mContext = context;
    }

    public boolean save(Simple simple) {
        Map<String, Object> daoContent = new HashMap<>();

        daoContent.put(Simple.STRING_VALUE, simple.stringValue);
        daoContent.put(Simple.DATE_VALUE, simple.dateValue);
        daoContent.put(Simple.BOOLEAN_VALUE, simple.booleanValue);
        daoContent.put(Simple.BLOB_VALUE, simple.blobValue);
        daoContent.put(Simple.SHORT_VALUE, simple.shortValue);
        daoContent.put(Simple.INT_VALUE, simple.intValue);
        daoContent.put(Simple.LONG_VALUE, simple.longValue);
        daoContent.put(Simple.FLOAT_VALUE, simple.floatValue);
        daoContent.put(Simple.DOUBLE_VALUE, simple.doubleValue);

        try {
            saveInternal(daoContent);
        } catch (IOException | CouchbaseLiteException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void saveInternal(Map<String, Object> content) throws IOException, CouchbaseLiteException {
        Manager manager = new Manager(new AndroidContext(mContext), Manager.DEFAULT_OPTIONS);
        if (Manager.isValidDatabaseName(Simple.DATABASE_NAME)) {
            Database database = manager.getDatabase(Simple.DATABASE_NAME);
            Document document = database.createDocument();
            document.putProperties(content);
        }
    }

}
