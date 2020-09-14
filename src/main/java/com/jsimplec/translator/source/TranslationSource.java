package com.jsimplec.translator.source;

import com.jsimplec.translator.model.TranslationModel;

import java.util.List;

public interface TranslationSource {
  List<TranslationModel> loadByClassName(String className);
}
