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
import com.github.fsteitz.jsoup.extension.selectFirst
import org.jsoup.nodes.Element
import java.io.File
import java.nio.charset.Charset

/**
 * @author Florian Steitz (florian@fsteitz.com)
 */
class FolderBookmarkExtractor(private val folderName: String, bookmarksFile: File, charset: Charset) : BookmarkExtractor(bookmarksFile, charset) {

  /**
   *
   */
  override fun extract(): Collection<String> {
    val bookmarkParent = findBookmarkParent()

    if (bookmarkParent.tagName() != TAG_DT) {
      throw UnexpectedTagException("Element '$TAG_DT' as folder parent expected - Bookmark file might be invalid")
    }

    return bookmarkParent.select(TAG_A).map { it.attr(ATTR_HREF) }
  }

  /**
   *
   */
  private fun findBookmarkParent(): Element {
    return bookmarkRoot.firstChild(::isBookmarkParent).orElseThrow {
      TagNotFoundException("Element '$TAG_DL' containing the desired bookmark folder not found")
    }
  }

  /**
   *
   */
  private fun isBookmarkParent(element: Element): Boolean {
    return element.selectFirst(TAG_H3).map { it.text() == folderName }.orElse(false)
  }
}