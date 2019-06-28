package ru.popovich.emergencyassist.service;

//https://stackoverflow.com/questions/34277392/best-way-to-load-some-json-files-into-a-spring-boot-application

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

    @Value("${json.file1}")
    String jsonfile1;

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

        logger.info(jsonfile1.toString());

//        testUser = jsonMapper.readValue(resourceLoader.getResource(jsonfile).getFile(),TestUser.class);

        socialServiceCatalog = jsonMapper.readValue(
                resourceLoader.getResource(jsonfile1).getFile(),
                SocialServiceCatalog.class);

        socialServiceCatalogList =
                jsonMapper.readValue(
                        resourceLoader.getResource(
                                jsonfile).getFile(),
                        jsonMapper.getTypeFactory().constructCollectionType(List.class, SocialServiceCatalog.class));

        logger.info(socialServiceCatalog.getTitle());
        logger.info(socialServiceCatalog.getSocialService().get(0).getTitle());
//        logger.info("Id: " + socialServiceCatalog.getId().toString());

        socialServiceCatalogList.forEach(
                s-> {
                    socialServiceCatalogDao.save(s);
//                    logger.info(s.getTitle());
//                    logger.info(s.toString());

                    s.getSocialService().forEach(
                            x->
                            {
//                                logger.info(x.toString());
                                x.setSocialServiceCatalog(s);
                                socialServiceDao.save(x);
                            });


                }
                );

//        socialServiceCatalog.getSocialService().forEach(socialServiceDao::save);
//        socialServiceCatalogDao.save(socialServiceCatalog);



//        socialServiceCatalogDao.save(socialServiceCatalog);
//        assert(socialServiceCatalog.getTitle().equals("Социально-бытовые услуги"));
    }
}
