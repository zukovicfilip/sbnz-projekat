package sbnz.integracija.example.service;

import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    public void init();

    public void save(UUID propertyId, MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}