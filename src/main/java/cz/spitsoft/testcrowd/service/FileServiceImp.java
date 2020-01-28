package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.file.FileImp;
import cz.spitsoft.testcrowd.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class FileServiceImp implements FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileImp saveFile(String name, MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            FileImp newFile = new FileImp(name, fileName, file.getBytes(), file.getContentType(), new Date());

            return fileRepository.save(newFile);
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public FileImp getFile(String fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found with id " + fileId));
    }
}
