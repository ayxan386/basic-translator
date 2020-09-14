package com.jsimplec.translator;

import com.jsimplec.translator.model.MapWrapper;

import java.io.Serializable;

public interface Translatable {
  void translate(MapWrapper l);
}
