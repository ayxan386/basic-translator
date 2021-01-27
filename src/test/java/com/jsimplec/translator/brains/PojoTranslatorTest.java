package com.jsimplec.translator.brains;

import com.jsimplec.translator.model.MapWrapper;
import com.jsimplec.translator.model.TranslationModel;
import com.jsimplec.translator.source.TranslationSource;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;

class PojoTranslatorTest {

  PojoTranslator pojoTranslator;
  TranslationSource translationSource;
  SimplePojo pojo;
  private String translateTo;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    pojo = new SimplePojo("Book", "to read");
    Map<String, String> values = new HashMap<>();
    values.put("en_US", "Book");
    translateTo = "Книга";
    values.put("ru_RU", translateTo);
    translationSource = (groupName) -> singletonList(new TranslationModel("pojo", "name", values));
    pojoTranslator = new PojoTranslator(translationSource);
  }

  @org.junit.jupiter.api.Test
  void translateCollection() {
    pojoTranslator.translate(singletonList(pojo), "ru_RU");
    Assertions.assertEquals(pojo.getName(), translateTo);
  }

  @org.junit.jupiter.api.Test
  void translateEmptyCollection() {
    String name = pojo.getName();
    pojoTranslator.translate(Collections.emptyList(), "ru_RU");
    Assertions.assertEquals(pojo.getName(), name);
  }

  @org.junit.jupiter.api.Test
  void translateStream() {
    pojoTranslator.translate(Stream.of(pojo), "ru_RU");
    Assertions.assertEquals(pojo.getName(), translateTo);
  }

  @org.junit.jupiter.api.Test
  void translateStreamWithLambda() {
    pojoTranslator.translate(Stream.of(pojo), "ru_RU",
        (obj, source) ->
            source.get("name", obj.getName()).ifPresent(obj::setName));
    Assertions.assertEquals(pojo.getName(), translateTo);
  }

  @org.junit.jupiter.api.Test
  void testTranslate2() {
    pojoTranslator.translate(singletonList(pojo), "ru_RU",
        (obj, source) ->
            source.get("name", obj.getName()).ifPresent(obj::setName));
    Assertions.assertEquals(pojo.getName(), translateTo);
  }

  private class SimplePojo implements Translatable {
    private String name;
    private String purpose;

    public SimplePojo(String name, String purpose) {
      this.name = name;
      this.purpose = purpose;
    }

    public String getName() {
      return name;
    }

    public String getPurpose() {
      return purpose;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setPurpose(String purpose) {
      this.purpose = purpose;
    }

    @Override
    public void translate(MapWrapper l) {
      l.get("name", name).ifPresent(this::setName);
    }
  }
}