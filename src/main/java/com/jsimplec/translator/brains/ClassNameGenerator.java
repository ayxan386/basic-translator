package com.jsimplec.translator.brains;

public interface ClassNameGenerator {
  default String getGroupName(Object obj) {
    return obj.getClass().getSimpleName();
  }
}
