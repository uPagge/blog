package ru.epam.blog.core.pojo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OffsetAndCount {

    private Integer offset;
    @NotNull(message = "Задайте количество")
    @Max(value = 100, message = "Слишком большое значение")
    @Min(value = 1, message = "Слишком маленькое значение")
    private Integer count;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
