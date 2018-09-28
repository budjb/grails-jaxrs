package com.budjb

class Author {
    String firstName
    String lastName
    String email

    static hasMany = [books:Book]

    static constraints = {
        email nullable:true, email:true
    }
}
