package com.fei.common.page;

import com.fei.common.utils.BeanUtils;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Data
@NoArgsConstructor
public class Page<T> implements Serializable {

    public static <T> Page<T> EMPTY_PAGE() {
        return new Page<>(0, Lists.newArrayList());
    }

    public static <T> Page<T> of(int total, List<T> page) {
        return new Page<>(total, page);
    }

    private int total;

    private List<T> page;

    public boolean notEmpty() {
        return !CollectionUtils.isEmpty(this.page);
    }

    public static <T> Page<T> emptyPage() {
        return new Page<>(Collections.emptyList());
    }

    public Page(int total, List<T> page) {
        this.total = total;
        this.page = page;
    }

    public Page(List<T> page) {
        this.total = 0;
        this.page = page;
    }

    public void ifPresent(Consumer<List<T>> listConsumer) {
        if (this.getPage() != null && this.getPage().size() > 0) {
            listConsumer.accept(this.page);
        }
    }

    public <S> Page<S> transform(Class<S> clazz) {
        List<S> sList = BeanUtils.batchTransform(clazz, page);
        return Page.of(total, sList);
    }

}
