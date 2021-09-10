package _utils

/**
 * Ignore normal procedure of extracting JavaDocs or Kotlin Docs
 * and instead use [value]
 */
annotation class Document(val value: String)

/**
 * Skip the file containing this annotation
 */
annotation class SkipDocumentation()
