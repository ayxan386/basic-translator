package com.jsimplec.translator.model;

import java.io.Serializable;
import java.util.Map;

public class TranslationModel implements Serializable {

  private final String className;
  private final String fieldName;
  private final Map<String, String> vals;

  public TranslationModel(String className, String fieldName, Map<String, String> vals) {
    this.className = className;
    this.fieldName = fieldName;
    this.vals = vals;
  }

  public String getClassName() {
    return className;
  }

  public String getFieldName() {
    return fieldName;
  }

  public Map<String, String> getVals() {
    return vals;
  }
}
