package com.jsimplec.translator.brains;

import com.jsimplec.translator.model.MapWrapper;

public interface Translator <T> {
  void translate(T obj, MapWrapper mw);
}
