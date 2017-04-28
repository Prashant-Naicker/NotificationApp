package main

import (
    "net/http"
)

func main() {
    http.HandleFunc("/", resp)
    http.ListenAndServe(":8080", nil)
}

func resp(w http.ResponseWriter, req *http.Request) {
    w.Write([]byte("HelloWorld"))
}
