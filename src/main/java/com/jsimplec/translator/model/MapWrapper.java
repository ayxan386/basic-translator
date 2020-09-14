package com.jsimplec.translator.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MapWrapper {
  private final Map<String, String> values;
  private final String targetLang;

  private MapWrapper(List<TranslationModel> translationModels, String targetLang) {
    values = new HashMap<>();
    this.targetLang = targetLang;
    fillInMap(translationModels);
  }

  private void fillInMap(List<TranslationModel> translations) {
    translations
        .forEach(t -> t
            .getVals()
            .forEach((k, v) -> values.put(makeKey(t.getFieldName(), v), t.getVals().get(targetLang))));
  }

  public Optional<String> get(String fn, String val) {
    String key = makeKey(fn, val);
    if (values.containsKey(key)) {
      return Optional.ofNullable(values.get(key));
    }
    return Optional.empty();
  }

  public static MapWrapper buildInstance(List<TranslationModel> translations, String targetLang) {
    return new MapWrapper(translations, targetLang);
  }

  public static String makeKey(String fn, String val) {
    return String.format("%s|%s", fn, val);
  }
}
