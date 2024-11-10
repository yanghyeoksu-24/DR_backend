package com.dr.controller.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8888") // CORS 허용
@RestController("boardFileApiController")
@RequestMapping("/board/tui-editor/image-upload") // 경로 설정
public class FileApiController {

    //업로드할 디렉토리 경로 설정
    private final String uploadDir = Paths.get("C:", "upload").toString(); // 상대 경로 사용

    // 이미지 업로드 처리 후 JSON 응답 보내기
    @PostMapping("/board/tui-editor/image-upload")
    public ResponseEntity<Map<String, Object>> uploadEditorImage(@RequestParam("image") final MultipartFile image) {
        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "파일이 비어 있습니다."));
        }

        String orgFilename = image.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = orgFilename.substring(orgFilename.lastIndexOf(".") + 1);
        String saveFilename = uuid + "." + extension;
        String fileFullPath = Paths.get(uploadDir, saveFilename).toString();

        // Generating local file name with timestamp for uniqueness
        String localFileName = "image_" + System.currentTimeMillis() + "_" + saveFilename;

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            File uploadFile = new File(fileFullPath);
            image.transferTo(uploadFile);

            // Sending response with the image info
            Map<String, Object> response = new HashMap<>();
            response.put("photoOriginal", orgFilename);  // Original file name
            response.put("photoLocal", "/image/photo/" + localFileName);   // Saved file name (path)
            response.put("photoSize", image.getSize());  // File size

            return ResponseEntity.ok(response);  // Sending JSON response with file info
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "파일 저장 실패: " + e.getMessage()));
        }
    }



    // 이미지 출력 처리
    @GetMapping(value = "/image-print", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> printEditorImage(@RequestParam final String filename) {
        // 업로드된 이미지 파일의 경로 설정
        String fileFullPath = Paths.get(uploadDir, filename).toString();
        File uploadedFile = new File(fileFullPath);

        // 파일이 존재하는지 확인
        if (!uploadedFile.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 파일이 존재하지 않으면 404 반환
        }

        try {
            // 파일을 읽어 바이트 배열로 변환
            byte[] imageBytes = Files.readAllBytes(uploadedFile.toPath());

            // 파일의 확장자에 따라 올바른 MIME 타입 설정
            String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
            MediaType mediaType = switch (extension) {
                case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
                case "png" -> MediaType.IMAGE_PNG;
                case "gif" -> MediaType.IMAGE_GIF;
                default -> MediaType.APPLICATION_OCTET_STREAM; // 기본적으로 파일 전송
            };

            return ResponseEntity.ok()
                    .contentType(mediaType) // 콘텐츠 타입 설정
                    .body(imageBytes); // 이미지 바이트 배열 반환
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 파일 읽기 실패
        }
    }

}
