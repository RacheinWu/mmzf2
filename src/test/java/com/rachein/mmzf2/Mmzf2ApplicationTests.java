package com.rachein.mmzf2;

import com.rachein.mmzf2.core.service.impl.DraftServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Mmzf2ApplicationTests {

    @Autowired
    private DraftServiceImpl draftService;

    @Test
    void contextLoads() {
        //1590172174008344577
        draftService.toDrawing(1590172174008344577L);

    }



}
