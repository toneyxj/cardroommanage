package com.xj.cardroommanage.inter;

public interface BackOperate<T> {
    /**
     * 返回操作状态
     * @param state
     */
    void onBackState(T data,boolean state);
}
