package ru.popovich.emergencyassist.service;

//https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql/
//https://stackoverflow.com/questions/36446201/org-postgresql-util-psqlexception-error-value-too-long-for-type-character-vary

//JSON MAPPER

//https://stackoverflow.com/questions/34277392/best-way-to-load-some-json-files-into-a-spring-boot-application
//https://www.journaldev.com/2324/jackson-json-java-parser-api-example-tutorial
//https://www.baeldung.com/jackson-mapping-dynamic-object
//https://stackoverflow.com/questions/25738569/jpa-map-json-column-to-java-object

//https://stackoverflow.com/questions/6349421/how-to-use-jackson-to-deserialise-an-array-of-objects
//https://stackoverflow.com/questions/23101260/ignore-fields-from-java-object-dynamically-while-sending-as-json-from-spring-mvc

//https://www.baeldung.com/jackson-ignore-properties-on-serialization
//https://stackoverflow.com/questions/23012841/receiving-json-and-deserializing-as-list-of-object-at-spring-mvc-controller


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.popovich.emergencyassist.model.SocialServiceCatalog;
import ru.popovich.emergencyassist.model.TestUser;
import ru.popovich.emergencyassist.repository.SocialServiceCatalogDao;
import ru.popovich.emergencyassist.repository.SocialServiceDao;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SocialServiceServiceInit {

    Logger logger = LoggerFactory.getLogger(SocialServiceCatalog.class);

    @Autowired
    private SocialServiceCatalogDao socialServiceCatalogDao;

    @Autowired
    private SocialServiceDao socialServiceDao;

    @Autowired
    ResourceLoader resourceLoader;

    @Value("${json.file}")
    String jsonfile;


    List<SocialServiceCatalog> socialServiceCatalogList = new ArrayList<>();

    SocialServiceCatalog[] socialServiceCatalogs = new SocialServiceCatalog[10];


    SocialServiceCatalog socialServiceCatalog = new SocialServiceCatalog();

    TestUser testUser = new TestUser();

    Map<String, String> myMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException{

        ObjectMapper jsonMapper = new ObjectMapper();

        socialServiceCatalogList =
                jsonMapper.readValue(
                        resourceLoader.getResource(
                                jsonfile).getFile(),
                        jsonMapper.getTypeFactory().constructCollectionType(List.class, SocialServiceCatalog.class));


        socialServiceCatalogList.forEach(
                s-> {
                    socialServiceCatalogDao.save(s);

                    s.getSocialService().forEach(
                            x->
                            {
                                x.setSocialServiceCatalog(s);
                                socialServiceDao.save(x);
                            });


                }
                );

    }
}
