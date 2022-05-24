package kr.or.cola.backend.common;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseWithPagination<T> {
    private final Integer page;
    private final Integer size;
    private final List<T> data;
}
