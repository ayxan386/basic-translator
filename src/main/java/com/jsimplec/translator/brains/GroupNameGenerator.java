package com.jsimplec.translator.brains;

public interface GroupNameGenerator {
  default String getGroupName(Object obj) {
    return obj.getClass().getSimpleName();
  }
}
