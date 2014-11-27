package net.pside.android.example.mostpowerfulorminandroid.manager;

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Manager;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.View;
import com.couchbase.lite.android.AndroidContext;

import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by keima on 14/11/20.
 */
public class SimpleModelManager {

    private Context mContext;
    private Manager mManager;

    public SimpleModelManager(Context context) {
        mContext = context;
    }

    public boolean save(Simple simple) {
        Map<String, Object> daoContent = new HashMap<>();

        daoContent.put(Simple.STRING_VALUE, simple.stringValue);
        daoContent.put(Simple.DATE_VALUE, simple.dateValue.getTime());
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
        Manager manager = getManager();
        if (Manager.isValidDatabaseName(Simple.DATABASE_NAME)) {
            Database database = manager.getDatabase(Simple.DATABASE_NAME);
            Document document = database.createDocument();
            document.putProperties(content);
        }
    }

    public Simple load(Document document) {
        Simple simple = new Simple();
        simple.stringValue = (String) document.getProperty(Simple.STRING_VALUE);
        simple.dateValue = new Date(Converter.toLong((Integer) document.getProperty(Simple.DATE_VALUE)));
        simple.booleanValue = (boolean) document.getProperty(Simple.BOOLEAN_VALUE);
        simple.shortValue = ((Integer) document.getProperty(Simple.SHORT_VALUE)).shortValue();
        simple.intValue = (Integer) document.getProperty(Simple.INT_VALUE);
        simple.longValue = ((Integer) document.getProperty(Simple.LONG_VALUE)).longValue();
        simple.floatValue = ((Double) document.getProperty(Simple.FLOAT_VALUE)).floatValue();
        simple.doubleValue = ((Double) document.getProperty(Simple.DOUBLE_VALUE));

        return simple;
    }

    /**
     * @deprecated 一応残しておくけど、このやり方( document.getProperties()を引数に取る )をすると激烈に重い
     */
    public Simple load(Map<String, Object> content) {
        Simple simple = new Simple();
        simple.stringValue = (String) content.get(Simple.STRING_VALUE);
        simple.dateValue = new Date(Converter.toLong((Integer) content.get(Simple.DATE_VALUE)));
        simple.booleanValue = (boolean) content.get(Simple.BOOLEAN_VALUE);
        simple.shortValue = ((Integer) content.get(Simple.SHORT_VALUE)).shortValue();
        simple.intValue = (Integer) content.get(Simple.INT_VALUE);
        simple.longValue = ((Integer) content.get(Simple.LONG_VALUE)).longValue();
        simple.floatValue = ((Double) content.get(Simple.FLOAT_VALUE)).floatValue();
        simple.doubleValue = ((Double) content.get(Simple.DOUBLE_VALUE));

        return simple;
    }

    public View getCouchView(Database database) {
        View view = database.getView(Simple.VIEW_NAME);
        if (view.getMap() == null) {
            Mapper map = new Mapper() {
                @Override
                public void map(Map<String, Object> document, Emitter emitter) {
                    Object o = document.get(Simple.DATE_VALUE);
                    emitter.emit(o, document);
                }
            };
            view.setMap(map, "1");
        }
        return view;
    }

    public List<Simple> findAll(Database database) throws IOException, CouchbaseLiteException {
        List<Simple> simpleList = new ArrayList<>();
        View view = getCouchView(database);

        Query query = view.createQuery();

        QueryEnumerator result = query.run();
        Log.d("CouchbaseManager", result.getCount() + "");

        for (; result.hasNext(); ) {
            QueryRow row = result.next();
            Simple simple = load(row.getDocument());
            if (simple.booleanValue) {
                simpleList.add(simple);
            }
        }
        return simpleList;
    }

    public Manager getManager() throws IOException {
        if (mManager == null) {
            mManager = new Manager(new AndroidContext(mContext), Manager.DEFAULT_OPTIONS);
        }
        return mManager;
    }

    public void deleteDatabase() {
        try {
            Manager manager = getManager();
            Database database = manager.getDatabase(Simple.DATABASE_NAME);
            if (database != null) {
                database.delete();
            }
        } catch (CouchbaseLiteException | IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        mContext = null;
        mManager = null;
    }

}
