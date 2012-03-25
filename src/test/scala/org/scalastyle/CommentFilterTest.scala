// Copyright (C) 2011-2012 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.scalastyle

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import _root_.scalariform.lexer.HiddenTokenInfo
import _root_.scalariform.lexer.Tokens._

class CommentHelperTest extends AssertionsForJUnit {
  @Test def testOffOn(): Unit = {
    assertCommentFilter(List(CommentFilter(Some(LineColumn(2, 0)),Some(LineColumn(3, 0)))), """
// scalastyle:off
// scalastyle:on""")
  }

  @Test def testOffOnVariousWhitespace(): Unit = {
    assertCommentFilter(List(CommentFilter(Some(LineColumn(2, 0)),Some(LineColumn(3, 1)))), """
//  scalastyle:off
 //  scalastyle:on """)
  }

  private[this] def assertCommentFilter(expected: List[CommentFilter], text: String) = {
    val hiddenTokenInfo = Checker.parseScalariform(text).get.hiddenTokenInfo
    val lines = Checker.parseLines(text)
    assertEquals(expected, CommentFilter.findCommentFilters(hiddenTokenInfo, lines))
  }
}