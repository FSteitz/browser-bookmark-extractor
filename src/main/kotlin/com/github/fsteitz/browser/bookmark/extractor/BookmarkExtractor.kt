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

import com.github.fsteitz.jsoup.extension.firstChild
import org.jsoup.Jsoup
import java.io.File
import java.nio.charset.Charset

/**
 * @author Florian Steitz (florian@fsteitz.com)
 */
abstract class BookmarkExtractor(private val bookmarkFile: File, private val charset: Charset) {

  /**
   *
   */
  val body by lazy { Jsoup.parse(bookmarkFile, charset.name()).body() }

  /**
   *
   */
  val bookmarkRoot by lazy { body.firstChild(TAG_DL).orElseThrow { TagNotFoundException("Bookmark root not found") } }

  /**
   *
   */
  abstract fun extract(): Collection<String>
}