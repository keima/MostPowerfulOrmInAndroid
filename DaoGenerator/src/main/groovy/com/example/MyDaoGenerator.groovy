package com.example

import org.greenrobot.greendao.generator.DaoGenerator
import org.greenrobot.greendao.generator.Entity
import org.greenrobot.greendao.generator.Schema

public class MyDaoGenerator {
    static int SCHEMA_VERSION = 1;
    static String SCHEMA_PACKAGE_NAME = "net.pside.android.example.mostpowerfulorminandroid";

    public static void main(String[] args) throws Exception {
        println "Generating greenDAO Schema and Entities..."

        println "> Setup Schema: ${SCHEMA_PACKAGE_NAME} : ${SCHEMA_VERSION}"
        Schema schema = new Schema(SCHEMA_VERSION, SCHEMA_PACKAGE_NAME);

        println "> Setup Entity: Simple"
        Entity simple = schema.addEntity("Simple");
        simple.addIdProperty();
        simple.addStringProperty("stringValue");
        simple.addDateProperty("dateValue");
        simple.addBooleanProperty("booleanValue");
//        simple.addByteArrayProperty("blobValue");
        simple.addShortProperty("shortValue");
        simple.addIntProperty("intValue");
        simple.addLongProperty("longValue");
        simple.addFloatProperty("floatValue");
        simple.addDoubleProperty("doubleValue");

        // 以下はいまのところ使いみちがないのでコメントアウトします
        /*
        def entities = new Entity[5];
        def entityNames = [
                "ChildOne",
                "ChildTwo",
                "ChildThree",
                "ChildFour",
                "ChildFive",
        ]

        // addEntityとaddPropertyを分ける(ひとつに纏めることもできるけどここは速度重視でもないし。。。)
        entities.eachWithIndex { Entity entity, int i ->
            entities[i] = schema.addEntity(entityNames[i])
        }

        entityNames.eachWithIndex() { String entityName, int i ->
            println "> Setup Entity: ${entityName}"

            Entity entity = entities[i]
            entity.addIdProperty()

            if (entityName.equals("ChildFive")) {
                entity.addStringProperty("ormName")
            } else {
                def property = entity.addLongProperty(entityNames[i + 1] + "Id").getProperty()
                entity.addToOne(entities[i], property)
            }
        }
        */

        println "> Generating Schema and Entity: ${args[0]}"
        new DaoGenerator().generateAll(schema, args[0]);
    }
}