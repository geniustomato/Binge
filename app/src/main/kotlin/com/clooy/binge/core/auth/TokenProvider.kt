package com.clooy.binge.core.auth

interface TokenProvider  {
    fun getToken(): String
}
