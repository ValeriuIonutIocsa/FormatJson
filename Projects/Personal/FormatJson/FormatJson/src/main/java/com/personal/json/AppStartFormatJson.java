package com.personal.json;

import java.io.Reader;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.utils.io.PathUtils;
import com.utils.io.ReaderUtils;
import com.utils.io.WriterUtils;
import com.utils.log.Logger;

final class AppStartFormatJson {

	private AppStartFormatJson() {
	}

	public static void main(
			final String[] args) {

		Logger.setDebugMode(true);
		try {
			if (args.length == 0) {

				final String helpMessage = createHelpMessage();
				Logger.printError("insufficient arguments" + System.lineSeparator() + helpMessage);
				System.exit(-1);
			}

			if ("--help".equals(args[0])) {

				final String helpMessage = createHelpMessage();
				Logger.printLine(helpMessage);
				System.exit(0);
			}

			final String inputFilePathString = args[0];
			final String outputFilePathString = computeOutputFilePathString(inputFilePathString);

			try (Reader reader = ReaderUtils.openBufferedReader(inputFilePathString);
					Writer writer = WriterUtils.openBufferedWriter(outputFilePathString)) {

				final Gson gson = new GsonBuilder().setPrettyPrinting().create();
				final JsonElement jsonElement = JsonParser.parseReader(reader);
				gson.toJson(jsonElement, gson.newJsonWriter(writer));
			}

		} catch (final Exception exc) {
			Logger.printError("failed to format JSON file");
			Logger.printException(exc);
		}
	}

	private static String createHelpMessage() {
		return "usage: format_json JSON_FILE_PATH";
	}

	private static String computeOutputFilePathString(
			final String inputFilePathString) {

		final String inputFilePathStringWoExt = PathUtils.computePathWoExt(inputFilePathString);
		final String extension = PathUtils.computeExtension(inputFilePathString);
		return inputFilePathStringWoExt + "_FORMATTED." + extension;
	}
}
