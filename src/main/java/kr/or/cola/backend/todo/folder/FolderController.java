package kr.or.cola.backend.todo.folder;

import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.todo.folder.dto.FolderResponseDto;
import kr.or.cola.backend.todo.folder.dto.FolderUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/folder")
@RequiredArgsConstructor
public class FolderController {
    private FolderService folderService;

    @PostMapping("")
    public ResponseEntity<Long> createFolder(@LoginUser SessionUser sessionUser, @RequestBody FolderUpdateRequestDto requestDto) {
        Long FolderId = folderService.createFolder(sessionUser.getUserId(), requestDto);
        return ResponseEntity.ok(FolderId);
    }

    @PatchMapping("/{folderId}")
    public ResponseEntity<Void> updateFolder(@PathVariable Long folderId, @RequestBody FolderUpdateRequestDto requestDto) {
        folderService.updateFolder(folderId, requestDto);
        return ResponseEntity.ok().build();
    }

    // Read Folder
    @GetMapping("")
    public ResponseEntity<List<FolderResponseDto>> getFolders(@LoginUser SessionUser sessionUser) {
        List<FolderResponseDto> folderResponse = folderService.readFolders(sessionUser.getUserId());
        return ResponseEntity.ok(folderResponse);
    }

    @GetMapping("/{folderId}")
    public ResponseEntity<FolderResponseDto> getFolder(@PathVariable Long folderId) {
        FolderResponseDto folderResponse = folderService.readFolder(folderId);
        return ResponseEntity.ok(folderResponse);
    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long folderId) {
        folderService.deleteFolder(folderId);
        return ResponseEntity.ok().build();
    }
}
