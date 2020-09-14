package com.jsimplec.translator;

import com.jsimplec.translator.model.MapWrapper;

public interface Translator <T> {
  void translate(T obj, MapWrapper mw);
}
