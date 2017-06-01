package com.agileengine.money.vo;

/**
 * Created by Ded on 31.03.2017.
 */
public class IdVO implements IBaseVO{

    private Long id;

    public IdVO() { }

    public IdVO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
