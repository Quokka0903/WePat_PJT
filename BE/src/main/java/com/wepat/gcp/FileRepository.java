package com.wepat.gcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileRepository.class);
    public void saveAll(List<InputFile> inputFiles) {
        LOGGER.info("saveAll");
        for (InputFile file : inputFiles) {
            LOGGER.info("file name: {}, fileURL: {}, fileId: {}",  file.getFileName(), file.getFileUrl(), file.getId());
//            System.out.println();
        }
    }
}
