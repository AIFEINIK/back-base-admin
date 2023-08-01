/** created by leihong.pan at 2019/11/21 15:34 */
package com.fei.common.result;

public interface ResultAble {

    <T> Result<T> toResult();

    <T> Result<T> toResult(Object... args);

}
