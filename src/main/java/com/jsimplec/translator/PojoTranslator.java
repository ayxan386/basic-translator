package com.jsimplec.translator;

import com.jsimplec.translator.model.MapWrapper;
import com.jsimplec.translator.source.TranslationSource;

import java.util.Collection;
import java.util.stream.Stream;

public class PojoTranslator {
  private final TranslationSource source;

  public PojoTranslator(TranslationSource source) {
    this.source = source;
  }

  public void translate(Translatable obj, String lang) {
    String simpleName = obj.getClass().getSimpleName();
    MapWrapper mapWrapper = MapWrapper
        .buildInstance(
            source
                .loadByClassName(simpleName),
            lang);
    obj.translate(mapWrapper);
  }

  public void translate(Stream<? extends Translatable> objs, String lang) {
    objs
        .findFirst()
        .map(c -> c.getClass().getSimpleName())
        .map(source::loadByClassName)
        .map(tl -> MapWrapper.buildInstance(tl, lang))
        .ifPresent(mp -> objs.forEach(obj -> obj.translate(mp)));
  }

  public void translate(Collection<? extends Translatable> objs, String lang) {
    translate(objs.stream(), lang);
  }

  public <T> void translate(T obj, String lang, Translator<T> f) {
    String simpleName = obj.getClass().getSimpleName();
    MapWrapper mapWrapper = MapWrapper
        .buildInstance(
            source
                .loadByClassName(simpleName),
            lang);
    f.translate(obj, mapWrapper);
  }

  public <T> void translate(Stream<T> objs, String lang, Translator<T> f) {
    objs
        .findFirst()
        .map(c -> c.getClass().getSimpleName())
        .map(source::loadByClassName)
        .map(tl -> MapWrapper.buildInstance(tl, lang))
        .ifPresent(mp -> objs.forEach(obj -> f.translate(obj, mp)));
  }

  public <T> void translate(Collection<T> objs, String lang, Translator<T> f) {
    translate(objs.stream(), lang, f);
  }
}
