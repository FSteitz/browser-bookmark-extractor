# Browser Bookmark Extractor
A small Kotlin-based CLI application for extracting bookmark URLs from a Netscape bookmark file (the default file format of bookmarks exported by major web browsers). It currently only supports the extraction of bookmark URLs stored inside a bookmark folder. But the structure is flexible enough to support additional use cases easily.

## Prerequisites
This application depends on the library [jsoup-extensions](https://github.com/FSteitz/jsoup-extensions), which currently isnt available in Maven Central or any other online artifact repository. Hence its source must be pulled and built before the application can be built or run.

## Building the application
`./gradlew build`

## Running the application
Before the application can be run, a bookmark file must be added to the resource folder. By default this file should be named `bookmarks.html`. In case a different file name is preferred, the value of the variable `BOOKMARK_FILE_NAME` in `Main.kt` must be changed to the preferred name.

The application can then be run with the following command:
`./gradlew run`