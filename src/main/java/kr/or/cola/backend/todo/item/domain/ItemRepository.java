package kr.or.cola.backend.todo.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByDate(LocalDate date);
    Item findByDateAndFolderId(LocalDate date, Long folderId);
    List<Item> findAllByDateAndFolderIdIn(LocalDate date, List<Long> folders);
    void deleteAllByFolderId(Long folderId);

    List<Item> findAllByDateBetweenAndFolderIdIn(LocalDate from, LocalDate to, List<Long> folders);
}
