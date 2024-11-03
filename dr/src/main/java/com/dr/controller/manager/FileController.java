package com.dr.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
@RequestMapping("/upload")
public class FileController {

    private final String baseUploadDirectory = "C:/upload"; // 기본 업로드 디렉토리

    @PostMapping("/file")
    public String uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productName") String name,
            RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "파일이 없습니다.");
            redirectAttributes.addFlashAttribute("status", "error");
            return "redirect:/manager/registerProduct";
        }

        String originalFilename = file.getOriginalFilename();
        String[] allowedExtensions = {".jpg", ".jpeg", ".png", ".gif"};
        boolean isAllowed = Arrays.stream(allowedExtensions)
                .anyMatch(ext -> originalFilename.toLowerCase().endsWith(ext));

        if (!isAllowed) {
            redirectAttributes.addFlashAttribute("message", "허용되지 않는 파일 형식입니다.");
            redirectAttributes.addFlashAttribute("status", "error");
            return "redirect:/manager/registerProduct";
        }

        try {
            // "상품" 폴더 생성
            File userDirectory = new File(baseUploadDirectory, "상품");
            if (!userDirectory.exists()) {
                userDirectory.mkdirs();  // 폴더가 없는 경우 생성
            }

            // 파일 이름 중복 방지를 위한 새로운 파일 이름 생성
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = name + "_" + LocalDateTime.now().toString().replace(":", "-") + fileExtension;
            File saveFile = new File(userDirectory, newFilename);
            file.transferTo(saveFile);

            redirectAttributes.addFlashAttribute("message", "파일이 성공적으로 업로드되었습니다.");
            redirectAttributes.addFlashAttribute("status", "success");
            return "redirect:/manager/registerProduct";
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "파일 업로드 실패: " + e.getMessage());
            redirectAttributes.addFlashAttribute("status", "error");
            return "redirect:/manager/registerProduct";
        }
    }
}
