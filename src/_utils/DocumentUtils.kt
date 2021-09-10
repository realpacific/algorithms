package _utils

/**
 * Ignore normal procedure of extracting JavaDocs or Kotlin Docs
 * and instead use [value]
 */
annotation class Document(val value: String)

@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION
)
annotation class UseCommentAsDocumentation()

/**
 * Skip the file containing this annotation
 */
annotation class SkipDocumentation()
