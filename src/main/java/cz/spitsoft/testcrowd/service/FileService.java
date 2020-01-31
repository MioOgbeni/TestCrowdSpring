package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.file.FileImp;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileImp saveFile(MultipartFile file, String testCaseId);

    FileImp getFile(String fileId);
}
