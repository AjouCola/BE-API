package kr.or.cola.backend.todo.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByDate(LocalDate date);
    Optional<Item> findByDateAndFolderId(LocalDate date, Long folderId);
    List<Item> findAllByDateAndFolderIdIn(LocalDate date, List<Long> folders);
    void deleteAllByFolderId(Long folderId);
}
