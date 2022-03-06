package com.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Automatically map mongodb documents to Plain Old Java Objects POJOs using only the mongoDB driver
 *
 */
public class App 
{
    public static void main( String[] args ){

        String connection = "mongodb+srv://lum:<password>@crudoperation.wxplj.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
        ConnectionString connectionString = new ConnectionString(connection);

        //configure the CodecRegistry to include a codec to handle the translation to and from BSON for our POJOs.
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

        //adding the default codec registry, which contains all the default codecs.
        // They can handle all the major types in Java-like Boolean, Double, String, BigDecimal, etc.

        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                pojoCodecRegistry);

        //wrap all settings together using MongoClientSettings.
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {

            MongoDatabase db = mongoClient.getDatabase("Info");
            MongoCollection<Person> person = db.getCollection("persons", Person.class);

            person.deleteMany(new Document());


            List<Address> userAddresses = singletonList(
                    new Address().setCity("Prizren")
                            .setNumber(12)
                            .setStreet("Bajram Curr"));
            Person user = new Person().setFirstname("lum")
                    .setSurename("gashi")
                    .setTwitter("@lumg")
                    .setAge(24)
                    .setAddresses(userAddresses);


            List<Address> user1Addresses = singletonList(
                    new Address().setCity("Prishtina")
                            .setNumber(4)
                            .setStreet("Tophane"));

            Person user1 = new Person().setFirstname("filan")
                    .setSurename("fisteku")
                    .setTwitter("@filan")
                    .setAge(15)
                    .setAddresses(user1Addresses);


            person.insertOne(user);
            person.insertOne(user1);


        }
    }
}
