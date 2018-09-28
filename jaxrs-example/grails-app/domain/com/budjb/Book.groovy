package com.budjb

class Book {
    String title
    String isbn
    Date published
    static belongsTo = [author: Author]

    static constraints = {
    }
}
