package com.gy.editor;

import java.beans.PropertyEditorSupport;

/**
 * 100.00d
 */
public class DoubleEditor extends PropertyEditorSupport  {
    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if (text == null || text.equals("")) {
            text = "0";
        }
        if(text.endsWith("d") || text.endsWith("D")) {
            // 1000D
            double value = Double.parseDouble(text.substring(0, text.length() - 1));
            setValue(value);
        } else {
            throw new IllegalArgumentException("数值必须以D/d结尾");
        }
    }

    @Override
    public String getAsText() {
        return getValue().toString() + "d";
    }
}
