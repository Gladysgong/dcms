package com.web.util.validation.bean.number;

import com.web.util.validation.bean.AbstractValidation;

/**
 * 数值最小值验证
 *
 * @author 杜延雷
 * @date 2016/9/26.
 */
public class MinValue<T extends Number> extends AbstractValidation<T> {
    /**
     * 最小值
     */
    private T minValue ;

    public MinValue(T object, T minValue, String errorTip) {
        super(object, errorTip);
        this.minValue = minValue ;
    }

    @Override
    public boolean validate() {
        if (null != object && null != minValue) {
            if (object instanceof Byte) {
                return object.byteValue() >= minValue.byteValue() ;
            } else if (object instanceof Short) {
                return object.shortValue() >= minValue.shortValue() ;
            } else if (object instanceof Integer) {
                return object.intValue() >= minValue.intValue() ;
            } else if (object instanceof Long) {
                return object.longValue() >= minValue.longValue() ;
            } else if (object instanceof Float) {
                return object.floatValue() >= minValue.floatValue() ;
            } else if (object instanceof Double) {
                return object.doubleValue() >= minValue.doubleValue() ;
            }
        }
        return false;
    }
}
