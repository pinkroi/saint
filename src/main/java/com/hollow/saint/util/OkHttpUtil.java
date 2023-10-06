package com.hollow.saint.util;

import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {
	private OkHttpUtil() {
	}

	public static OkHttpClient getInstance() {
		return Singleton.INSTANCE.getSingleton();
	}

	private enum Singleton {
		INSTANCE;
		private final OkHttpClient singleton;
		HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

		Singleton() {
			singleton = new OkHttpClient().newBuilder()
					.connectionPool(new ConnectionPool(10, 60, TimeUnit.SECONDS))
					.cookieJar(new CookieJar() {
						@Override
						public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
							cookieStore.put(httpUrl.host(), list);
						}

						@Override
						public List<Cookie> loadForRequest(HttpUrl httpUrl) {
							List<Cookie> cookies = cookieStore.get(httpUrl.host());
							return cookies != null ? cookies : new ArrayList<Cookie>();
						}
					}).build();
		}

		private OkHttpClient getSingleton() {
			return singleton;
		}
	}

	public static boolean doPost(String url, Map<String, String> map) throws IOException {
		FormBody.Builder builder = new FormBody.Builder();
		map.forEach(builder::add);
		RequestBody body = builder.build();
		Request request = new Request.Builder()
				.url(url)
				.method("POST", body)
				.build();
		Response response = getInstance().newCall(request).execute();
		return response.code() == 200;
	}
}
