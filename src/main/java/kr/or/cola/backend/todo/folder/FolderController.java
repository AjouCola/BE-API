package kr.or.cola.backend.todo.folder;

import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.todo.folder.dto.FolderUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/folder")
@RequiredArgsConstructor
public class FolderController {
    private FolderService folderService;

    @PostMapping("")
    public ResponseEntity<Long> createFolder(@LoginUser SessionUser sessionUser, @RequestBody FolderUpdateRequestDto requestDto) {
        Long folderId = folderService.createFolder(sessionUser.getUserId(), requestDto);
        return ResponseEntity.ok(folderId);
    }

    @PatchMapping("/{folderId}")
    public ResponseEntity<Long> updateFolder(@PathVariable Long id, @RequestBody FolderUpdateRequestDto requestDto) {
        Long folderId = folderService.updateFolder(id, requestDto);
        return ResponseEntity.ok(folderId);
    }
}
