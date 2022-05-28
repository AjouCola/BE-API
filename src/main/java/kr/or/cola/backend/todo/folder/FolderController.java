package kr.or.cola.backend.todo.folder;

import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.todo.folder.dto.FolderResponseDto;
import kr.or.cola.backend.todo.folder.dto.FolderUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/folder")
@RequiredArgsConstructor
public class FolderController {
    private final FolderService folderService;

    @PostMapping("")
    public ResponseEntity<Long> createFolder(
            @LoginUser SessionUser sessionUser,
            @RequestBody FolderUpdateRequestDto requestDto) {
        log.info("session User : "+ sessionUser.getUserId().toString());
        log.info("folderUpdateResponse : "+ requestDto.getName()+", "+ requestDto.getColor());

        Long FolderId = folderService.createFolder(sessionUser.getUserId(), requestDto);
        return ResponseEntity.ok(FolderId);
    }

    @PatchMapping("/{folderId}")
    public ResponseEntity<Void> updateFolder(
            @PathVariable Long folderId,
            @RequestBody FolderUpdateRequestDto requestDto) {
        folderService.updateFolder(folderId, requestDto);
        return ResponseEntity.ok().build();
    }

    // Read Folder
    @GetMapping("")
    public ResponseEntity<List<FolderResponseDto>> getFolders(@LoginUser SessionUser sessionUser) {
        List<FolderResponseDto> folderResponse = folderService.readFolders(sessionUser.getUserId());
        return ResponseEntity.ok(folderResponse);
    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long folderId) {
        folderService.deleteFolder(folderId);
        return ResponseEntity.ok().build();
    }
}
