package org.ngo.basic;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml"})
public abstract class TestBase {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Ignore
    public void testGetNameSpace() {
        logger.debug(this.getClass().getSuperclass().getName());
    }

}
