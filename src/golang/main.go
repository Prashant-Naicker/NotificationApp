package main

import (
    "net/http"
)

func main() {
    http.HandleFunc("/notification", resp)
    http.ListenAndServe(":8080", nil)
}

func resp(w http.ResponseWriter, req *http.Request) {
    SetGeneralHeaders(w)
    if req.Method == "OPTIONS" {
        w.WriteHeader(http.StatusOK)
        w.Write([]byte(""))
        return
    }

}

func SetGeneralHeaders(w http.ResponseWriter) {
    w.Header().Set("Cache-Control", "no-store")
    w.Header().Set("Access-Control-Allow-Origin", "*")
    w.Header().Set("Access-Control-Allow-Methods", "POST, OPTIONS")
    w.Header().Set("Access-Control-Allow-Headers", "Content-Type, Accept, Origin")
}
