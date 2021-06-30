package sbnz.integracija.example.controller;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import sbnz.integracija.example.model.FileInfo;
import sbnz.integracija.example.model.ResponseMessage;
import sbnz.integracija.example.service.FilesStorageService;

@Controller
public class FilesController {

    @Autowired
    FilesStorageService storageService;

    @PostMapping("/property/{propertyId}/upload")
    public ResponseEntity<ResponseMessage> uploadFiles(@PathVariable UUID propertyId, @RequestParam HashMap<String, MultipartFile> formData) {
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            for(String fileName : formData.keySet()) {
                System.out.println(fileName);
                MultipartFile file = formData.get(fileName);
                storageService.save(propertyId, file);
                fileNames.add(propertyId.toString() + '_' + file.getOriginalFilename());
            }

            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/property/{propertyId}/files")
    public ResponseEntity<List<FileInfo>> getPropertyListFiles(@PathVariable UUID propertyId) {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        List<FileInfo> propertyFileInfos = new ArrayList<>();

        for(FileInfo fileInfo : fileInfos)
            if(fileInfo.getName().contains(propertyId.toString()))
                propertyFileInfos.add(fileInfo);

        return ResponseEntity.status(HttpStatus.OK).body(propertyFileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}