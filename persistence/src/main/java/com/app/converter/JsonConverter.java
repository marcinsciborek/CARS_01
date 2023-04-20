package com.app.converter;

import com.app.converter.exception.JsonConverterException;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public abstract class JsonConverter<T> {
    private final Gson gson;
    private final Type type
            = ((ParameterizedType)getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    protected JsonConverter(Gson gson) {
        this.gson = gson;
    }

    public void toJson(String filename, final T element){
        try(var writer = new FileWriter(filename)){
            if (element == null){
                throw new NullPointerException("Element is null");
            }
            gson.toJson(element,writer);
        } catch (Exception e){
            throw new JsonConverterException(e.getMessage());
        }
    }

    public Optional<T> fromJson(String filename) {
        try (var reader = new FileReader(filename)){
            return Optional.of(gson.fromJson(reader,type));
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
