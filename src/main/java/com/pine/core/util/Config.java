package com.pine.core.util;

import lombok.NonNull;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by chenbupang on 2017-11-23 16:40
 */
public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String PREFIX_CLASSPATH = "classpath:";
    private static final String PREFIX_FILE = "file:";
    private static final String PREFIX_URL = "url:";
    private static final String SALT = "/";
    private Properties props = new Properties();

    public static Config empty() {
        return new Config();
    }

    public static Config of(@NonNull Properties props) {
        if (props == null) {
            throw new NullPointerException("props");
        } else {
            Config Config = new Config();
            Config.props = props;
            return Config;
        }
    }

    public static Config of(@NonNull Map<String, String> map) {
        if (map == null) {
            throw new NullPointerException("map");
        } else {
            Config Config = new Config();
            map.forEach((key, value) -> {
                Config.props.setProperty(key, value);
            });
            return Config;
        }
    }

    public static Config of(@NonNull URL url) {
        if (url == null) {
            throw new NullPointerException("url");
        } else {
            try {
                return of(url.openStream());
            } catch (UnsupportedEncodingException var2) {
                return null;
            } catch (IOException var3) {
                throw new IllegalStateException(var3);
            }
        }
    }

    public static Config of(@NonNull File file) {
        if (file == null) {
            throw new NullPointerException("file");
        } else {
            try {
                return of(Files.newInputStream(Paths.get(file.getPath())));
            } catch (IOException var2) {
                throw new IllegalStateException(var2);
            }
        }
    }

    public static Config of(@NonNull String location) {
        if (location == null) {
            throw new NullPointerException("location");
        } else if (location.startsWith("classpath:")) {
            location = location.substring("classpath:".length());
            return loadClasspath(location);
        } else if (location.startsWith("file:")) {
            location = location.substring("file:".length());
            return of(new File(location));
        } else if (location.startsWith("url:")) {
            location = location.substring("url:".length());

            try {
                return of(new URL(location));
            } catch (MalformedURLException var2) {
                log.error("", var2);
                return null;
            }
        } else {
            return loadClasspath(location);
        }
    }

    private static Config loadClasspath(@NonNull String classpath) {
        if (classpath == null) {
            throw new NullPointerException("classpath");
        } else {
            if (classpath.startsWith("/")) {
                classpath = classpath.substring(1);
            }

            InputStream is = getDefault().getResourceAsStream(classpath);
            return null == is ? new Config() : of(is);
        }
    }

    private static Config of(@NonNull InputStream is) {
        if (is == null) {
            throw new NullPointerException("is");
        } else {
            Config var2;
            try {
                Config Config = new Config();
                Config.props.load(new InputStreamReader(is, "UTF-8"));
                var2 = Config;
            } catch (IOException var6) {
                throw new IllegalStateException(var6);
            } finally {
                PineUtils.closeQuietly(is);
            }

            return var2;
        }
    }

    public static ClassLoader getDefault() {
        ClassLoader loader = null;

        try {
            loader = Thread.currentThread().getContextClassLoader();
        } catch (Exception var3) {
            ;
        }

        if (loader == null) {
            loader = Config.class.getClassLoader();
            if (loader == null) {
                try {
                    loader = ClassLoader.getSystemClassLoader();
                } catch (Exception var2) {
                    ;
                }
            }
        }

        return loader;
    }

    public Config set(@NonNull String key, @NonNull Object value) {
        if (key == null) {
            throw new NullPointerException("key");
        } else if (value == null) {
            throw new NullPointerException("value");
        } else {
            String val = value.toString();
            this.props.put(key, val);
            return this;
        }
    }

    public Config add(@NonNull String key, @NonNull Object value) {
        if (key == null) {
            throw new NullPointerException("key");
        } else if (value == null) {
            throw new NullPointerException("value");
        } else {
            return this.set(key, value);
        }
    }

    public Config addAll(@NonNull Map<String, String> map) {
        if (map == null) {
            return this;
        } else {
            map.forEach((key, value) -> {
                this.props.setProperty(key, value);
            });
            return this;
        }
    }

    public Config addAll(@NonNull Properties props) {
        if (props == null) {
            throw new NullPointerException("props");
        } else {
            props.forEach((key, value) -> {
                this.props.setProperty(key.toString(), value.toString());
            });
            return this;
        }
    }

    public Optional<String> get(String key) {
        return null == key ? Optional.empty() : Optional.ofNullable(this.props.getProperty(key));
    }

    public String getOrNull(String key) {
        return (String)this.get(key).orElse(null);
    }

    public String get(String key, String defaultValue) {
        Optional<String> e = this.get(key);
        return (String)this.get(key).orElse(defaultValue);
    }

    public Optional<Object> getObject(String key) {
        return Optional.ofNullable(this.props.get(key));
    }

    public Optional<Integer> getInt(String key) {
        return null != key && this.getObject(key).isPresent() ? Optional.of(Integer.parseInt(this.getObject(key).get().toString())) : Optional.empty();
    }

    public Integer getIntOrNull(String key) {
        Optional<Integer> intVal = this.getInt(key);
        return (Integer)intVal.orElse(null);
    }

    public Integer getInt(String key, int defaultValue) {
        return this.getInt(key).isPresent() ? (Integer)this.getInt(key).get() : defaultValue;
    }

    public Optional<Long> getLong(String key) {
        return null != key && this.getObject(key).isPresent() ? Optional.of(Long.parseLong(this.getObject(key).get().toString())) : Optional.empty();
    }

    public Long getLongOrNull(String key) {
        Optional<Long> longVal = this.getLong(key);
        return (Long)longVal.orElse(null);
    }

    public Long getLong(String key, long defaultValue) {
        return (Long)this.getLong(key).orElse(defaultValue);
    }

    public Optional<Boolean> getBoolean(String key) {
        return null != key && this.getObject(key).isPresent() ? Optional.of(Boolean.parseBoolean(this.getObject(key).get().toString())) : Optional.empty();
    }

    public Boolean getBooleanOrNull(String key) {
        Optional<Boolean> boolVal = this.getBoolean(key);
        return (Boolean)boolVal.orElse(null);
    }

    public Boolean getBoolean(String key, boolean defaultValue) {
        return (Boolean)this.getBoolean(key).orElse(defaultValue);
    }

    public Optional<Double> getDouble(String key) {
        return null != key && this.getObject(key).isPresent() ? Optional.of(Double.parseDouble(this.getObject(key).get().toString())) : Optional.empty();
    }

    public Double getDoubleOrNull(String key) {
        Optional<Double> doubleVal = this.getDouble(key);
        return (Double)doubleVal.orElse(null);
    }

    public Double getDouble(String key, double defaultValue) {
        return (Double)this.getDouble(key).orElse(defaultValue);
    }

    public Optional<Date> getDate(String key) throws Exception {
        if (null != key && this.getObject(key).isPresent()) {
            String value = this.getObject(key).get().toString();
            Date date = DateUtils.parseDate(value);
            return Optional.ofNullable(date);
        } else {
            return Optional.empty();
        }
    }

    public Date getDateOrNull(String key) throws Exception{
        Optional<Date> dateVal = this.getDate(key);
        return (Date)dateVal.orElse(null);
    }

    public Map<String, Object> getPrefix(String key) {
        Map<String, Object> map = new HashMap();
        if (null != key) {
            this.props.forEach((key_, value) -> {
                if (key_.toString().startsWith(key)) {
                    map.put(key_.toString().substring(key.length() + 1), value);
                }

            });
        }

        return map;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap(this.props.size());
        this.props.forEach((k, v) -> {
            String var10000 = (String)map.put(k.toString(), v.toString());
        });
        return map;
    }

    public boolean hasKey(String key) {
        return null == key ? false : this.props.containsKey(key);
    }

    public boolean hasValue(String value) {
        return this.props.containsValue(value);
    }

    public Properties props() {
        return this.props;
    }

    private Config() {
    }
}
