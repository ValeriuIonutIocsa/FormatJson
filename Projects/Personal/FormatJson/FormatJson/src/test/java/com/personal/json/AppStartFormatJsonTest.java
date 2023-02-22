package com.personal.json;

import org.junit.jupiter.api.Test;

import com.utils.io.PathUtils;

class AppStartFormatJsonTest {

	@Test
	void testMain() {

		final String inputFilePathString = PathUtils.computePath("test", "test.json");
		AppStartFormatJson.main(new String[] { inputFilePathString });
	}
}
