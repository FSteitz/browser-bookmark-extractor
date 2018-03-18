# Browser Bookmark Extractor
A small Kotlin-based CLI application for extracting bookmark URLs from a Netscape bookmark file (the default file format of bookmarks exported by major web browsers). It currently only supports the extraction of bookmark URLs stored inside a bookmark folder. But the structure is flexible enough to support additional use cases easily.

## Prerequisites
This application depends on the library [jsoup-extensions](https://github.com/FSteitz/jsoup-extensions), which currently isnt available in Maven Central or any other online artifact repository. Hence its source must be pulled and built before the application can be built or run.

## Building the library
`./gradlew build`