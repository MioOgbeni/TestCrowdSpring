package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.file.FileImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileImp, String> {
}
