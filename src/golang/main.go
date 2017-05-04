package main

import (
    "net/http"
    //"io/ioutil"
    //"encoding/json"
    //"log"
)

type Message struct { Message string `json:"message"` }

func main() {
    http.HandleFunc("/send-message", message)
    http.HandleFunc("/send-notification", notification)
    http.ListenAndServe(":8080", nil)
}

func message(w http.ResponseWriter, r *http.Request) {
    SetGeneralHeaders(w)
    if r.Method == "OPTIONS" {
        w.WriteHeader(http.StatusOK)
        w.Write([]byte(""))
        return
    }
    w.WriteHeader(0)
    //message := Message{"Hello"}

    //b, err := json.Marshal(message)
    //if err != nil { return }

/*
    message := Message{}

    defer r.Body.Close()
    body, err := ioutil.ReadAll(r.Body)
    if err != nil { return }

    err = json.Unmarshal(body, &message)
    if err != nil { return }
*/
    return

}

func notification(w http.ResponseWriter, r *http.Request) {
    SetGeneralHeaders(w)

}

func SetGeneralHeaders(w http.ResponseWriter) {
    w.Header().Set("Cache-Control", "no-store")
    w.Header().Set("Access-Control-Allow-Origin", "*")
    w.Header().Set("Access-Control-Allow-Methods", "POST, OPTIONS")
    w.Header().Set("Access-Control-Allow-Headers", "Content-Type, Accept, Origin")
}
