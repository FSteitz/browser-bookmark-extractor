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

package com.github.fsteitz.browser.bookmark.extractor

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JSON
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

/**
 * @author Florian Steitz (florian@fsteitz.com)
 */
@Serializable
private data class BookmarkData(val bookmarks: Collection<String>)

/**
 * @author Florian Steitz (florian@fsteitz.com)
 */
object BookmarkDiskPersister {

  /**
   *
   */
  fun persist(bookmarkUrls: Collection<String>, pathName: String, charset: Charset) {
    val fileContent = JSON.stringify(BookmarkData(bookmarkUrls))

    println("Writing extracted bookmark URLs to '$pathName'")
    Files.write(Paths.get(pathName), fileContent.toByteArray(charset))
  }
}