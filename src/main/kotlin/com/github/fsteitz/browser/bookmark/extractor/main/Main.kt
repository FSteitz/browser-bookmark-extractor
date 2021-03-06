/*
 * Copyright 2018 Florian Steitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.fsteitz.browser.bookmark.extractor.BookmarkDiskPersister
import com.github.fsteitz.browser.bookmark.extractor.FolderBookmarkExtractor
import java.io.File
import java.io.FileNotFoundException
import java.net.URL
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * @author Florian Steitz (florian@fsteitz.com)
 */
const val BOOKMARK_FOLDER = "Vocabs"
const val BOOKMARK_FILE_NAME = "bookmarks.html"
const val RESULT_FILE_NAME = "bookmark.json"

val charset = StandardCharsets.UTF_8 as Charset

/**
 *
 */
fun main(args: Array<String>) {
  val bookmarkFile = File(getClasspathResource(BOOKMARK_FILE_NAME).file)

  println("Parsing file '${bookmarkFile.absolutePath}'")
  writeToDisk(FolderBookmarkExtractor(BOOKMARK_FOLDER, bookmarkFile, charset).extract(), bookmarkFile.parent)
}

/**
 *
 */
fun getClasspathResource(fileName: String): URL {
  return Thread.currentThread().contextClassLoader?.getResource(fileName)
          ?: throw FileNotFoundException("File '$fileName' in classpath not found")
}

/**
 *
 */
fun writeToDisk(bookmarkUrls: Collection<String>, folderPath: String) {
  println("Found ${bookmarkUrls.size} bookmarks in bookmark folder '$BOOKMARK_FOLDER'")
  BookmarkDiskPersister.persist(bookmarkUrls, folderPath + File.separator + RESULT_FILE_NAME, charset)
}