package com.web.util.validation.bean.string;

import com.web.util.validation.bean.AbstractValidation;

/**
 * 字符串长度区间验证
 *
 * @author 杜延雷
 * @date 2016/9/26.
 */
public class StringRangeLength extends AbstractValidation<String> {

    /**
     * 下限长度
     */
    private int lowerLimitLength ;

    /**
     * 上限长度
     */
    private int upperLimitLength ;

    public StringRangeLength(String property, int lowerLimitLength, int upperLimitLength, String errorTip) {
        this.object = property ;
        this.lowerLimitLength = lowerLimitLength;
        this.upperLimitLength = upperLimitLength;
        this.errorTip = errorTip ;
    }

    @Override
    public boolean validate() {
        if (null != object) {
            return object.length() >= lowerLimitLength && object.length() <= upperLimitLength ;
        }
        return false;
    }
}
