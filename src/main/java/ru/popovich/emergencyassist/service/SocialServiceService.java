package ru.popovich.emergencyassist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.popovich.emergencyassist.model.SocialService;
import ru.popovich.emergencyassist.model.SocialServiceCatalog;
import ru.popovich.emergencyassist.repository.SocialServiceCatalogDao;
import ru.popovich.emergencyassist.repository.SocialServiceDao;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SocialServiceService {

    Logger logger = LoggerFactory.getLogger(SocialServiceCatalog.class);

    @Value("${json.file}")
    String jsonfile;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    SocialServiceDao socialServiceDao;

    @Autowired
    private SocialServiceCatalogDao socialServiceCatalogDao;

    List<SocialServiceCatalog> socialServiceCatalogList = new ArrayList<>();

    public void save(SocialService socialService){
        socialServiceDao.save(socialService);
    }

    //    @PostConstruct
    public void init() throws IOException {

        ObjectMapper jsonMapper = new ObjectMapper();

        //Load json
        socialServiceCatalogList =
                jsonMapper.readValue(
                        resourceLoader.getResource(
                                jsonfile).getFile(),
                        jsonMapper.getTypeFactory().constructCollectionType(List.class, SocialServiceCatalog.class));


        // Save Social Services to Database
        socialServiceCatalogList.forEach(
                s-> {
                    s.setDateEnable(LocalDateTime.now());
                    socialServiceCatalogDao.save(s);

                    s.getSocialService().forEach(
                            x->
                            {
                                x.setSocialServiceCatalog(s);
                                x.setDateEnable(LocalDateTime.now());
                                socialServiceDao.save(x);
                            });
                }
        );
    }


}
