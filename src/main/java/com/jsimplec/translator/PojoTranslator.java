package com.jsimplec.translator;

import com.jsimplec.translator.model.MapWrapper;
import com.jsimplec.translator.source.TranslationSource;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PojoTranslator {
  private final TranslationSource source;

  public PojoTranslator(TranslationSource source) {
    this.source = source;
  }

  public void translate(Collection<? extends Translatable> objs, String lang) {
    if (objs.isEmpty()) return;
    objs
        .stream()
        .findFirst()
        .map(c -> c.getClass().getSimpleName())
        .map(source::loadByClassName)
        .map(tl -> MapWrapper.buildInstance(tl, lang))
        .ifPresent(mp -> objs.forEach(obj -> obj.translate(mp)));
  }

  public void translate(Stream<? extends Translatable> objs, String lang) {
    translate(objs.collect(Collectors.toList()), lang);
  }

  public <T> void translate(Collection<T> objs, String lang, Translator<T> f) {
    objs
        .stream()
        .findFirst()
        .map(c -> c.getClass().getSimpleName())
        .map(source::loadByClassName)
        .map(tl -> MapWrapper.buildInstance(tl, lang))
        .ifPresent(mp -> objs.forEach(obj -> f.translate(obj, mp)));
  }

  public <T> void translate(Stream<T> objs, String lang, Translator<T> f) {
    translate(objs.collect(Collectors.toList()), lang, f);
  }
}
