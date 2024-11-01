package com.dr.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/upload")
public class FileController {

    private final String baseUploadDirectory = "C:/upload"; // 기본 업로드 디렉토리

    @PostMapping("/file")
    public String uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productName") String name,  // 추가된 name 파라미터
            RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "파일이 없습니다.");
            redirectAttributes.addFlashAttribute("status", "error");
            return "redirect:/manager/registerProduct";
        }

        try {
            // 사용자 이름에 해당하는 폴더 생성
            File userDirectory = new File(baseUploadDirectory, name);
            if (!userDirectory.exists()) {
                userDirectory.mkdirs();  // 폴더가 없는 경우 생성
            }

            // 해당 폴더에 파일 저장
            File saveFile = new File(userDirectory, file.getOriginalFilename());
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
