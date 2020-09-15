package com.jsimplec.translator.brains;

import com.jsimplec.translator.model.MapWrapper;

import java.io.Serializable;

public interface Translatable {
  void translate(MapWrapper l);
}
