package com.ols.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import junit.framework.Assert;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MongoDbSpringIntegrationTest {
	
//	https://www.baeldung.com/spring-boot-embedded-mongodb
	
    @DisplayName("given object to save"
        + " when save object using MongoDB template"
        + " then object is saved")
    @Test
    public void testFromBaeldung(@Autowired MongoTemplate mongoTemplate) {
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
            .add("key", "value")
            .get();
 
        // when
        mongoTemplate.save(objectToSave, "collection");
 
        // then
        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key")
            .containsOnly("value");
        
        
       }
    
    @Test
    public void testWithUser(@Autowired MongoTemplate mongoTemplate) {
    	User bob = new User("Bobbybobobo",69);
    	
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
            .add(bob.getName(), bob)
            .get();
 
        // when
        mongoTemplate.save(objectToSave, "users");
 
        // then
        assertThat(mongoTemplate.findAll(DBObject.class, "users")).extracting(bob.getName()).extracting("name")
            .containsOnly("Bobbybobobo");
        
        
       }
}